package it.spring.ticket.platform.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.repository.TicketsRepository;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketsRepository ticketsRepo;
	
	@GetMapping("")
	public String listaTickets(Authentication authentication, @RequestParam(name="keyword", required = false) String keyword,Model model) {
		
		List <Ticket> listaTickets;
		if(keyword!=null && !keyword.isBlank()) {
			listaTickets = ticketsRepo.findByTitoloContaining(keyword);
		}
		else {
			listaTickets = ticketsRepo.findAll();
		}
		model.addAttribute("tickets", listaTickets);
		return "lista-tickets";
	}

}
