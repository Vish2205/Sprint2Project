package com.customer.payloads;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

	private int orderId;
	
	@NotEmpty(message = "orderStatus can not be blank!")
	private String orderStatus;
	
	@Min(value = 1, message = "orderQuantity can not be blank or less than 0!")
	private int orderQuantity;
	
	@Min(value = 1, message = "orderAmount can not be blank or less than 0!")
	private int orderAmount;
	
	private String orderActive;
	
	@NotEmpty(message = "orderPayMethod can not be blank!")
	private String orderPayMethod;
	
	private Date orderDate;
	
	private CustomerDto customer;
	
	private ProductDto product;
}
