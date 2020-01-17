package com.webcourse.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webcourse.curso.entities.Order;
import com.webcourse.curso.repositories.OrderRepositories;


@Service
public class OrderService {

	@Autowired
	private OrderRepositories repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order finfById(Long id) {
		
		Optional<Order> obj = repository.findById(id);
		
		return obj.get();
	}
	
}
