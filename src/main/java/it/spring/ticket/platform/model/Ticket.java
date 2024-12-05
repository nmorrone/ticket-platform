package it.spring.ticket.platform.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Inserisci la data di inizio dell'offerta")
	@FutureOrPresent(message = "La data di inizio non può essere precedente ad oggi")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCreazione;
	@NotBlank(message = "Inserisci un titolo al Ticket")
	@NotNull(message = "Inserisci un titolo al Ticket")
	private String titolo;

	// entity relationship Many to 1
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	@JsonBackReference
	private Categoria categoria;

	// entity relationshop Many to 1
	@ManyToOne
	@JoinColumn(name = "stato_id", nullable = false)
	@JsonBackReference
	private Stato stato;

	// entity relationship 1to1
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User operatore;

	// entity relationship 1 to Many
	@OneToMany(mappedBy = "ticket")
	private List<Nota> note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public User getOperatore() {
		return operatore;
	}

	public void setOperatore(User operatore) {
		this.operatore = operatore;
	}

	public List<Nota> getNote() {
		return note;
	}

	public void setNote(List<Nota> note) {
		this.note = note;
	}

}
