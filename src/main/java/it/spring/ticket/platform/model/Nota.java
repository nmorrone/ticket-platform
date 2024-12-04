package it.spring.ticket.platform.model;

import java.time.LocalDate;

public class Nota {

	private Integer id;
	private String testo;
	private LocalDate dataInserimento;
	
	//entity relationship
	private User autore;
	
	//entity relationship 1 to 1
	private Ticket ticket;
}
