package com.customer.services;

import java.util.List;

import com.customer.payloads.CustomerDto;

public interface CustomerService {
	
	public CustomerDto createCustomer(CustomerDto customer);
	
	public CustomerDto updateCustomer(CustomerDto customer, Integer cid);
	
	public CustomerDto getCustomerById(Integer cid);
	
	public List<CustomerDto> getAllCustomer();
	
	public void deleteCustomer(Integer cid);
};