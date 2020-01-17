package com.webcourse.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webcourse.curso.entities.Product;
import com.webcourse.curso.repositories.ProductRepositories;


@Service
public class ProductService {

	@Autowired
	private ProductRepositories repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product finfById(Long id) {
		
		Optional<Product> obj = repository.findById(id);
		
		return obj.get();
	}
	
}
