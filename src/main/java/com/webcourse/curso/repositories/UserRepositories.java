package com.webcourse.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcourse.curso.entities.User;

public interface UserRepositories extends JpaRepository<User, Long> {
	
	

}
