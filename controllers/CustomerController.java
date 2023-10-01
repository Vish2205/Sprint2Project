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
import com.customer.payloads.CustomerDto;
import com.customer.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/createCustomer")
	public ResponseEntity<APIResponse> createCustomer(@Valid @RequestBody CustomerDto customerDto)
	{
		CustomerDto createdCustomer = new CustomerDto();
		APIResponse response = new APIResponse();
		try{
			createdCustomer = customerService.createCustomer(customerDto);
			response.setStatus(true);
			response.setMessage("Customer created successfuly!");
			response.setData(createdCustomer);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(createdCustomer);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCustomer/{cid}")
	public ResponseEntity<APIResponse> updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable("cid") Integer cid)
	{
		CustomerDto updatedCustomer = new CustomerDto();
		APIResponse response = new APIResponse();
		try{
			updatedCustomer = customerService.updateCustomer(customerDto,cid);
			response.setStatus(true);
			response.setMessage("Customer updated successfuly!");
			response.setData(updatedCustomer);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(updatedCustomer);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCustomerById/{cid}")
	public ResponseEntity<APIResponse> getCustomerById(@PathVariable("cid") Integer cid)
	{
		CustomerDto customer = new CustomerDto();
		APIResponse response = new APIResponse();
		try{
			customer = customerService.getCustomerById(cid);
			response.setStatus(true);
			response.setMessage("Customer found!");
			response.setData(customer);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(customer);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomer")
	public ResponseEntity<APIResponse> getAllCustomer()
	{
		List<CustomerDto> customers = new ArrayList<>();
		APIResponse response = new APIResponse();
		try{
			customers = customerService.getAllCustomer();
			response.setStatus(true);
			response.setMessage("Customers found!");
			response.setData(customers);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(customers);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{cid}")
	public ResponseEntity<APIResponse> deleteCustomer(@PathVariable("cid") Integer cid)
	{
		APIResponse response = new APIResponse();
		try{
			customerService.deleteCustomer(cid);
			response.setStatus(true);
			response.setMessage("Customer deleted successfuly!");
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
