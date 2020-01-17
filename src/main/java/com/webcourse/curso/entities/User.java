package com.webcourse.curso.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Mapear com o jpa
@Table(name="tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id // Fazer o campo id ser o campo ID(primary key) no banco de dados relacional
	@GeneratedValue(strategy = GenerationType.IDENTITY) // fazer o campo id ser auto increment
	private Long id;
	private String nome;
	private String email;
	private String password;
	private String phone;
	
	@JsonIgnore // Evita o loop infinito pq o user chama o client e o client chama o user
	@OneToMany(mappedBy="client")
	private List<Order> orders = new ArrayList<>();
	
	
	//Constructor
	public User() {
	}
	public User(Long id, String nome, String email, String password, String phone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	
	//hashcode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	//getter e setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Order> getOrders() {
		return orders;
	}
	
	//To String
	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ "]";
	}
	
	
	
	
}
