package it.spring.ticket.platform.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.spring.ticket.platform.model.Categoria;
import it.spring.ticket.platform.model.Nota;
import it.spring.ticket.platform.model.Stato;
import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.model.User;
import it.spring.ticket.platform.repository.CategorieRepository;
import it.spring.ticket.platform.repository.NoteRepository;
import it.spring.ticket.platform.repository.StatiRepository;
import it.spring.ticket.platform.repository.TicketsRepository;
import it.spring.ticket.platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class TicketController {
	
	@Autowired
	private TicketsRepository ticketsRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategorieRepository categorieRepo;
	@Autowired 
	private StatiRepository statiRepo;
	@Autowired 
	private NoteRepository noteRepo;
	
	//visualizzazione tickets ADMIN
	@GetMapping("/dashboard-admin")
	public String listaTickets(Authentication authentication, @RequestParam(name="keyword", required = false) String keyword,Model model) {

		List <Ticket> listaTickets;
		if(keyword!=null && !keyword.isBlank()) {
			listaTickets = ticketsRepo.findByTitoloContaining(keyword);
			model.addAttribute("keyword", keyword);
		}
		else {
			listaTickets = ticketsRepo.findAll();
		}
		model.addAttribute("tickets", listaTickets);
		return "tickets/dashboard-admin";
		
	}
	
	//rimozione Ticket dalla lista ADMIN
	@PostMapping("/rimuovi-ticket/{id}")
	public String rimozioneTicket(@PathVariable Integer id) {
		ticketsRepo.deleteById(id);
		return "redirect:/tickets";
	}
	//display pagina inserimento nuovo ticket ADMIN
	@GetMapping("/crea-ticket")
	public String creaTicket(Model model) {	
		List <User> operatori = new ArrayList<>();		
		for(User user : userRepo.findAll()) {
			if(user.isDisponibile() == true)  {
				operatori.add(user);
			}
		}
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("operatori", operatori);
		model.addAttribute("categorie", categorieRepo.findAll());
		return "tickets/crea-ticket";
		
	}
	//salvataggio nuovo Ticket ADMIN
	@PostMapping("/crea-ticket")
	public String storeTicket(@Valid @ModelAttribute("ticket") Ticket ticketForm, BindingResult bindingResults, Model model) {
			if(bindingResults.hasErrors()) {
				return "tickets/crea-ticket";
				}
			
			else if(ticketForm.getUser() == null) {
				ticketForm.setStato(statiRepo.findById(1).get());
			}
			else if (ticketForm.getUser() != null) {
			ticketForm.setStato(statiRepo.findById(2).get());
			}
			ticketsRepo.save(ticketForm);
			return "redirect:/dashboard-admin";
	}
	
	//visualizzazione tickets OPERATORE
	@GetMapping("/tickets")
	public String listaTicketsOperatore(Authentication authentication, Model model) {
		Optional<User> loggedUser = userRepo.findByUsername(authentication.getName());
		User operatore = loggedUser.get();
		List <Ticket> ticketsOperatore = operatore.getTickets();
		model.addAttribute("tickets", ticketsOperatore);
		model.addAttribute("operatore", operatore);
		
		return "tickets/lista-tickets";
	}
	
	//info pagina Ticket Singolo
	@GetMapping("/tickets/{id}")
	public String infoTicket(Authentication authentication,@PathVariable(name="id") Integer id, Model model) {
		Optional <Ticket> infoTicket = ticketsRepo.findById(id);
		Optional<User> loggedUser = userRepo.findByUsername(authentication.getName());
		if(infoTicket.isPresent()) {
			Nota nuovaNota = new Nota();
			nuovaNota.setTicket(infoTicket.get());
			nuovaNota.setUser(loggedUser.get());
			model.addAttribute("ticket", infoTicket.get());
			model.addAttribute("nuovaNota", nuovaNota);
		}
		else {}
		 return "tickets/info-ticket";
	}

	
	
}
