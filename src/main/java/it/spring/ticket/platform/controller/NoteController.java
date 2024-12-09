package it.spring.ticket.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.spring.ticket.platform.model.Nota;
import it.spring.ticket.platform.repository.NoteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class NoteController {
	
	@Autowired
	private NoteRepository noteRepo;
	
	@PostMapping("/aggiungi-nota")
	public String storeNota(@Valid @ModelAttribute("notaForm") Nota notaForm,BindingResult bindingResults, Model model) {
		if(bindingResults.hasErrors()) {
			return"tickets/info-ticket";
		}

		noteRepo.save(notaForm);
		return"redirect:/tickets/" + notaForm.getTicket().getId();		
	}
	

}
