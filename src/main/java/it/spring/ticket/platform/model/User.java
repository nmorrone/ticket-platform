package it.spring.ticket.platform.model;

import java.util.Set;

public class User {

	private Integer id;
	private String email;
	private String password;
	
	//valore true false, perennemente true per Admin
	private boolean disponibile;
	
	
	//entity relationship 1 to Many
	private Set<Roles> roles;
	
}
