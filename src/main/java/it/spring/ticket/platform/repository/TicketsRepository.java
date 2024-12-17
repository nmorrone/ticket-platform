package it.spring.ticket.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import it.spring.ticket.platform.model.Ticket;

public interface TicketsRepository extends JpaRepository<Ticket, Integer>{
	public List <Ticket> findByTitoloContaining(String titolo);
	public List<Ticket> findByCategoriaId(Integer id);
	public List<Ticket> findByStatoId(Integer id); 
}
