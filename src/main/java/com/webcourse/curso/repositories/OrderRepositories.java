package com.webcourse.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcourse.curso.entities.Order;

public interface OrderRepositories extends JpaRepository<Order, Long> {
	
	

}
