package it.spring.ticket.platform.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.model.User;
import it.spring.ticket.platform.repository.TicketsRepository;

@Controller
@RequestMapping("/")
public class OperatoreController {
	
	@Autowired
	private TicketsRepository ticketsRepo;
	
	@PostMapping("/assegna-operatore/ticket/{id}")
	public String assegnaOperatore(@PathVariable(name="id") Integer id,User operatoreForm,  Model model) {
		Optional <Ticket> ticketVerifica = ticketsRepo.findById(id);
		if(ticketVerifica.isPresent() && operatoreForm != null) {
			Ticket ticketOperatore = ticketVerifica.get();
			ticketOperatore.setUser(operatoreForm);
			ticketsRepo.save(ticketOperatore);
			return "redirect:/dashboard-admin";
		}
		
		else {
			return "tickets/dashboard-admin";
		}
		
	}

}
