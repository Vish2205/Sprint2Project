package com.customer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entities.APIResponse;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.payloads.ProductDto;
import com.customer.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/createProduct")
	public ResponseEntity<APIResponse> createProduct(@Valid @RequestBody ProductDto productDto)
	{
		ProductDto createdProduct = new ProductDto();
		APIResponse response = new APIResponse();
		try{
			createdProduct = productService.createProduct(productDto);
			response.setStatus(true);
			response.setMessage("Product created successfuly!");
			response.setData(createdProduct);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(createdProduct);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateProduct/{pid}")
	public ResponseEntity<APIResponse> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable("pid") Integer pid)
	{
		ProductDto updatedProduct = new ProductDto();
		APIResponse response = new APIResponse();
		try{
			updatedProduct = productService.updateProduct(productDto,pid);
			response.setStatus(true);
			response.setMessage("Product updated successfuly!");
			response.setData(updatedProduct);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(updatedProduct);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getProductById/{pid}")
	public ResponseEntity<APIResponse> getProductById(@PathVariable("pid") Integer pid)
	{
		ProductDto product = new ProductDto();
		APIResponse response = new APIResponse();
		try{
			product = productService.getProductById(pid);
			response.setStatus(true);
			response.setMessage("Product found!");
			response.setData(product);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(product);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<APIResponse> getAllProduct()
	{
		List<ProductDto> products = new ArrayList<>();
		APIResponse response = new APIResponse();
		try{
			products = productService.getAllProduct();
			response.setStatus(true);
			response.setMessage("Products found!");
			response.setData(products);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(products);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct/{pid}")
	public ResponseEntity<APIResponse> deleteProduct(@PathVariable("pid") Integer pid)
	{
		APIResponse response = new APIResponse();
		try{
			productService.deleteProduct(pid);
			response.setStatus(true);
			response.setMessage("Product deleted successfuly!");
		}
		catch(ResourceNotFoundException e){
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
