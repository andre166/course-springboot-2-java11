package com.webcourse.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webcourse.curso.entities.Category;
import com.webcourse.curso.repositories.CategoryRepositories;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepositories repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category finfById(Long id) {
		
		Optional<Category> obj = repository.findById(id);
		
		return obj.get();
	}
	
}
