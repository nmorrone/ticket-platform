package it.spring.ticket.platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Roles {

	@Id
	private Integer id;
	@NotBlank (message = "Il campo non può essere vuoto")
	@NotNull (message = "Il campo non può essere nullo")
	private String name;
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
