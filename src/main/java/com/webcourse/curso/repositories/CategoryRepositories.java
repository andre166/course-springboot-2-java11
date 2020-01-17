package com.webcourse.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcourse.curso.entities.Category;

public interface CategoryRepositories extends JpaRepository<Category, Long> {
	
	

}
