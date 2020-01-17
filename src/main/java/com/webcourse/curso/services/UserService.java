package com.webcourse.curso.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.webcourse.curso.entities.User;
import com.webcourse.curso.repositories.UserRepositories;
import com.webcourse.curso.services.exception.DataBaseException;
import com.webcourse.curso.services.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepositories repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User finfById(Long id) {

		Optional<User> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		// em vez de dar return obj.get(); usa-se o return obj.orElseThrow() para lançar
		// a exception personalizada
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}

	public User update(Long id, User obj) {
		try {
			// repository.getOne(id) instancia um obj monitorado pelo jpa para depois
			// ATUALIZAR o obj e leva-lo ao banco de dados
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {

		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

	}
}
