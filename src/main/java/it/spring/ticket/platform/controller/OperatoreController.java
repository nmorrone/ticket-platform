package it.spring.ticket.platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.model.User;
import it.spring.ticket.platform.repository.TicketsRepository;
import it.spring.ticket.platform.repository.UserRepository;

@Controller
@RequestMapping("/")
public class OperatoreController {
	
	@Autowired
	private TicketsRepository ticketsRepo;
	@Autowired
	private UserRepository userRepo;
	
	//metodo Assegna Operatore
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
	
	//metodo display Pagina Profilo USER/ADMIN
	@GetMapping("/il-mio-profilo")
	public String profiloOperatore(Authentication authentication, Model model) {
		Optional<User> loggedUser = userRepo.findByUsername(authentication.getName());
		User utente = loggedUser.get();
		int verifica = 0;
		for(Ticket ticket : utente.getTickets()) {
			if(ticket.getStato().getId() != 3) {
				verifica = verifica +1;
			}
		}
		if (verifica == 0) {
			model.addAttribute ("nonDisponibile", true);
		}
		else {
			model.addAttribute("nonDisponibile", false);
		}
		model.addAttribute("user", utente);
		return "utenti/il-mio-profilo";
	}
	
	//metodo POST Mapping
	@PostMapping("/aggiorna-stato/{id}")
	public String nonDisponibileOperatore(@PathVariable(name="id") Integer id) {
		User userStacca = userRepo.findById(id).get();
		userStacca.setDisponibile(false);
		userRepo.save(userStacca);
		return "redirect/il-mio-profilo";
	}
	
	@GetMapping("/modifica-profilo/{id}")
	public String modificaProfilo(@PathVariable(name="id") Integer id, Model model) {
		Optional <User> utente = userRepo.findById(id);
		model.addAttribute("user", utente.get());
		return "utenti/modifica-profilo";
	}

}
