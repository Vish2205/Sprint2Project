package com.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}