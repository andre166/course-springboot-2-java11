package com.webcourse.curso.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.webcourse.curso.services.exception.DataBaseException;
import com.webcourse.curso.services.exception.ResourceNotFoundException;

@ControllerAdvice // Intercepta as exceptions que venham a acontecer para fazer o tratamento
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //Capacita o método para interceptar as exceções
	public ResponseEntity<StandartExceptions> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartExceptions err = new StandartExceptions(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class) //Capacita o método para interceptar as exceções
	public ResponseEntity<StandartExceptions> dataBase(DataBaseException e, HttpServletRequest request){
		String error = "DataBase Error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartExceptions err = new StandartExceptions(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
