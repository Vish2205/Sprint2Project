package com.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}