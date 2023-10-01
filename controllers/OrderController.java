package com.customer.controllers;

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
import com.customer.payloads.OrderDto;
import com.customer.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/createOrder/user/{userId}/category/{categoryId}")
	public ResponseEntity<APIResponse> createOrder(@Valid @RequestBody OrderDto orderDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId)
	{
		APIResponse response = new APIResponse();
		try
		{
			OrderDto data = orderService.createOrder(orderDto,categoryId,userId);
			response.setStatus(true);
			response.setMessage("Order created successfuly!");
			response.setData(data);
		}
		catch(ResourceNotFoundException e){
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			response.setStatus(false);
			response.setMessage("Something went wrong, please try again!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllOrder")
	public ResponseEntity<APIResponse> getAllOrder()
	{
		APIResponse response = new APIResponse();
		try{
			List<OrderDto> data = orderService.getAllOrder();
			response.setStatus(true);
			response.setMessage("Orders found!");
			response.setData(data);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getOrder/{orderId}")
	public ResponseEntity<APIResponse> getOrder(@PathVariable("orderId") Integer orderId)
	{
		APIResponse response = new APIResponse();
		try{
			OrderDto data = orderService.getOrderByOrderId(orderId);
			response.setStatus(true);
			response.setMessage("Order found!");
			response.setData(data);
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<APIResponse> deleteOrder(@PathVariable("orderId") Integer orderId)
	{
		APIResponse response = new APIResponse();
		try{
			orderService.deleteOrderById(orderId);
			response.setStatus(true);
			response.setMessage("Order deleted successfuly!");
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<APIResponse> updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody OrderDto orderDto)
	{
		APIResponse response = new APIResponse();
		try{
			OrderDto data = orderService.updateOrder(orderDto,orderId);
			response.setStatus(true);
			response.setMessage("Order updated successfuly!");
			response.setData(data);
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
