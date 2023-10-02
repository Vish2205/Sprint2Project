package com.customer.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private int pid;
	
	private String productName;
	
	private String productDescription;
	
	private int productPrice;
	
	private int productQuantity;
}
