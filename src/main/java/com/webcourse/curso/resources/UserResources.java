package com.webcourse.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webcourse.curso.entities.User;
import com.webcourse.curso.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.finfById(id);
		return ResponseEntity.ok().body(obj);

	}

	@PostMapping // Anotação para inserção
	// @requestbody anotação para garantir a descerialização do obj em formato json
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		//Quando inserimos algo é necessário retornar o código 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//ResponseEntity.created() -> Espera uma URI, cabeçalho do loaction de onde foi inserido o obj
		return ResponseEntity.created(uri).body(obj);

	}
	
	@DeleteMapping(value = "/{id}") //deleta o obj(usuario) pelo Id
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		//Como a delete não retorna nada o método é void e o respondeEtity.noContent() é o 204
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}") // Padrão REST para atualização de dados
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}

}
