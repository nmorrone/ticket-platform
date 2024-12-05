package it.spring.ticket.platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="categorie")
public class Categoria {

	@Id
	private Integer id;
	@NotNull (message="Inserisci il nome dello Categoria")
	@NotBlank (message="Inserisci il nome dello Categoria")
	private String nome;
	
	//getters and setters
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
