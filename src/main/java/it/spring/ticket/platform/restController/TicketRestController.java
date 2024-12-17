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
import org.springframework.web.server.ResponseStatusException;

import it.spring.ticket.platform.model.Ticket;
import it.spring.ticket.platform.repository.TicketsRepository;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketRestController {

	@Autowired
	private TicketsRepository ticketsRepo;

	// api method READ lista tickets con Keyword Opzionale
	@GetMapping
	public ResponseEntity<List<Ticket>> index(@RequestParam(name = "keyword", required = false) String keyword) {

		if (keyword != null && !keyword.isBlank()) {

			return new ResponseEntity<List<Ticket>>(ticketsRepo.findByTitoloContaining(keyword), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Ticket>>(ticketsRepo.findAll(), HttpStatus.OK);
		}
	}

	// api method READ singolo ticket
	@GetMapping("{id}")
	public ResponseEntity<Ticket> infoTicket(@PathVariable("id") Integer id) {
		Optional<Ticket> ticketById = ticketsRepo.findById(id);
		if (ticketById.isPresent()) {
			return new ResponseEntity<Ticket>(ticketById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<Ticket>> ticketsByCategoryId(@PathVariable(name = "id") Integer id) {

		if (id != null) {
			List<Ticket> listaByCategoriaId = ticketsRepo.findByCategoriaId(id);
			if (!listaByCategoriaId.isEmpty()) {
				return new ResponseEntity<List<Ticket>>(listaByCategoriaId, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/stato/{id}")
	public ResponseEntity<List<Ticket>> ticketsByStatoId(@PathVariable(name = "id") Integer id) {

		if (id != null) {
			List<Ticket> listaByStatoId = ticketsRepo.findByStatoId(id);
			if (!listaByStatoId.isEmpty()) {
				return new ResponseEntity<List<Ticket>>(listaByStatoId, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);

		}
	}

}
