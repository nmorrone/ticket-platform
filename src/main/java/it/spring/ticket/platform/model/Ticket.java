package it.spring.ticket.platform.model;

import java.time.LocalDate;
import java.util.List;

public class Ticket {

	private Integer id;
	private LocalDate dataCreazione;
	private String titolo;
	
	//entity relationship 1 to 1
	private Categoria categoria;
	
	//entity relationship 1to1
	private User operatore;
	
	//entity relationship 1 to Many
	private List<Nota> note;
	
	
}
