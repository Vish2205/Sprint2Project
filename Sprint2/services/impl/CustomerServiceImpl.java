package com.customer.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entities.Customer;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.payloads.CustomerDto;
import com.customer.repositories.CustomerRepo;
import com.customer.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo CustomerRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customer = CustomerRepo.save(customer);
		customerDto = modelMapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, Integer cid) {
		Customer customer = CustomerRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Customer", "ID", cid));
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setAddress(customerDto.getAddress());
		customer.setMobile(customerDto.getMobile());
		customer.setAge(customerDto.getAge());
		customer = CustomerRepo.save(customer);
		CustomerDto updatedCustomerDto = modelMapper.map(customer, CustomerDto.class);
		return updatedCustomerDto;
	}

	@Override
	public CustomerDto getCustomerById(Integer cid) {
		Customer customer = CustomerRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Customer", "ID", cid));
		CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		List<Customer> customers = CustomerRepo.findAll();
		List<CustomerDto> customerDtos = customers.stream().map(Customer -> modelMapper.map(Customer, CustomerDto.class)).collect(Collectors.toList());
		return customerDtos;
	}

	@Override
	public void deleteCustomer(Integer cid) {
		Customer customer = CustomerRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Customer", "ID", cid));
		CustomerRepo.delete(customer);
	}
}
