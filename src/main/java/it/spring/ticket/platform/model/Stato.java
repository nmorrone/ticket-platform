package it.spring.ticket.platform.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Stato {
	
	@Id
	private Integer id;
	@NotNull (message="Inserisci il nome dello Stato")
	@NotBlank (message="Inserisci il nome dello Stato")
	private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
