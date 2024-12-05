package it.spring.ticket.platform.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	private Integer id;
	@Email(message = "Inserisci un'email valida")
	private String username;
	@NotBlank(message = "Inserisci una password valida")
	@NotNull(message = "Inserisci una password valida")
	private String password;
	// valore true false, perennemente true per Admin
	@NotNull(message = "Imposta il tuo stato di disponibilità")
	private boolean disponibile;
	// entity relationship Many to Many
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Roles> roles;
	// entity relationship 1 to Many
	@OneToMany(mappedBy = "user")
	private List<Ticket> tickets;
	// entity relationship 1 to Many
	@OneToMany(mappedBy = "user")
	private List<Nota> note;

	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Nota> getNote() {
		return note;
	}

	public void setNote(List<Nota> note) {
		this.note = note;
	}
	
	

	
}
