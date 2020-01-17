package com.webcourse.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcourse.curso.entities.OrderItem;

public interface OrderItemRepositories extends JpaRepository<OrderItem, Long> {
	
	

}
