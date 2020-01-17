package com.webcourse.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcourse.curso.entities.Product;

public interface ProductRepositories extends JpaRepository<Product, Long> {
	
	

}
