package it.spring.ticket.platform.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Nota {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message="Inserisci il testo della nota")
	@NotBlank(message="Inserisci il testo della ntoa")
	private String testo;
	@NotNull(message = "Inserisci la data di inizio dell'offerta")
	@FutureOrPresent(message = "La data di inizio non può essere precedente ad oggi")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInserimento;
	//entity relationship ManytoOne
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User autore;
	//entity relationship Many to 1
	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	@JsonBackReference
	private Ticket ticket;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public User getAutore() {
		return autore;
	}
	public void setAutore(User autore) {
		this.autore = autore;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
