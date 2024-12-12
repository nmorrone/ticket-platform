package it.spring.ticket.platform.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.repository.TicketsRepository;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketRestController {
	
	@Autowired
	private TicketsRepository ticketsRepo;
	
	//api method READ lista tickets con Keyword Opzionale
	@GetMapping
	public ResponseEntity <List<Ticket>> index(@RequestParam(name="keyword", required = false) String keyword){
		
		if(keyword != null && !keyword.isBlank()) {
			
			return new ResponseEntity<List<Ticket>>(ticketsRepo.findByTitoloContaining(keyword), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Ticket>>(ticketsRepo.findAll(), HttpStatus.OK);
		}
	}
	
	//api method READ singolo ticket
	@GetMapping("{id}")
	public ResponseEntity <Ticket> infoTicket(@PathVariable("id") Integer id){
		Optional <Ticket> ticketById = ticketsRepo.findById(id);
		if (ticketById.isPresent()) {
			return new ResponseEntity<Ticket>(ticketById.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ticket> aggiornaTitoloTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
	    
		try {
	        Optional<Ticket> ticketById = ticketsRepo.findById(id);
	            Ticket modificheTicket = ticketById.get();
	            modificheTicket.setDescrizione(ticket.getDescrizione());
	            ticketsRepo.save(modificheTicket);
	            return ResponseEntity.ok(modificheTicket);
	    } catch (Exception e) {
	        e.printStackTrace(); // Stampa l'eccezione per debug
	    }
		
		return ResponseEntity.notFound().build();		
		

}
	
}
