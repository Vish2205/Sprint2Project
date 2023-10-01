package com.customer.services;

import java.util.List;

import com.customer.payloads.OrderDto;

public interface OrderService {

	public OrderDto createOrder(OrderDto orderDto, Integer cid, Integer pid);
	
	public OrderDto updateOrder(OrderDto orderDto, Integer orderId);
	
	public OrderDto getOrderByOrderId(Integer orderId);
	
	public List<OrderDto> getAllOrder();
	
	public void deleteOrderById(Integer orderId);
}
