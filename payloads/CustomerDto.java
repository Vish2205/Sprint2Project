package com.customer.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private int cid;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private long mobile;
	
	private int age;
	
	private String password;
}
