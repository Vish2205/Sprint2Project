package com.customer.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entities.Customer;
import com.customer.entities.Order;
import com.customer.entities.Product;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.payloads.OrderDto;
import com.customer.repositories.CustomerRepo;
import com.customer.repositories.OrderRepo;
import com.customer.repositories.ProductRepo;
import com.customer.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ProductRepo productRepo;
	
	
	
	@Override
	public OrderDto createOrder(OrderDto orderDto, Integer cid, Integer pid) {
		Customer customer = customerRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Customer", "ID", cid));
		Product product = productRepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Product", "ID", pid));
		Order order = mapper.map(orderDto, Order.class);
		order.setOrderDate(new Date());
		order.setOrderActive("Y");
		order.setCustomer(customer);
		order.setProduct(product);
		Order savedOrder = orderRepo.save(order);
		OrderDto savedOrderDto = mapper.map(savedOrder, OrderDto.class);
		return savedOrderDto;
	}

	@Override
	public OrderDto updateOrder(OrderDto orderDto, Integer orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order", "Id", orderId));
		order.setOrderStatus(orderDto.getOrderStatus());
		order.setOrderQuantity(orderDto.getOrderQuantity());
		order.setOrderAmount(orderDto.getOrderAmount());
		order.setOrderPayMethod(orderDto.getOrderPayMethod());
		Order savedOrder = orderRepo.save(order);
		OrderDto savedOrderMap = mapper.map(savedOrder, OrderDto.class);
		return savedOrderMap;
	}

	@Override
	public OrderDto getOrderByOrderId(Integer orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order", "Id", orderId));
		OrderDto orderDto = mapper.map(order, OrderDto.class);
		return orderDto;
	}

	@Override
	public List<OrderDto> getAllOrder() {
		List<Order> orders = orderRepo.findAll();
		List<OrderDto> ordersDto = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
		return ordersDto;
	}

	@Override
	public void deleteOrderById(Integer orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order", "Id", orderId));
		orderRepo.delete(order);
	}

}