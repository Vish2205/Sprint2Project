package com.customer.services;

import java.util.List;

import com.customer.payloads.ProductDto;

public interface ProductService {
	
	public ProductDto createProduct(ProductDto product);
	
	public ProductDto updateProduct(ProductDto product, Integer pid);
	
	public ProductDto getProductById(Integer pid);
	
	public List<ProductDto> getAllProduct();
	
	public void deleteProduct(Integer pid);
};