package it.spring.ticket.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.repository.TicketsRepository;

@Controller
@RequestMapping("")
public class IndexController {
	
	@GetMapping
	public String index() {

		return "index";
	}

}
