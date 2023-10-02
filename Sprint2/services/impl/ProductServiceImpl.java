package com.customer.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entities.Product;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.payloads.ProductDto;
import com.customer.repositories.ProductRepo;
import com.customer.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo ProductRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product = ProductRepo.save(product);
		productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer pid) {
		Product product = ProductRepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", pid));
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductQuantity(productDto.getProductQuantity());
		product = ProductRepo.save(product);
		ProductDto updatedProductDto = modelMapper.map(product, ProductDto.class);
		return updatedProductDto;
	}

	@Override
	public ProductDto getProductById(Integer pid) {
		Product product = ProductRepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", pid));
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> products = ProductRepo.findAll();
		List<ProductDto> productDtos = products.stream().map(Product -> modelMapper.map(Product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public void deleteProduct(Integer pid) {
		Product product = ProductRepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", pid));
		ProductRepo.delete(product);
	}
}
