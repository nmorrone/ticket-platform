package it.spring.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spring.ticket.platform.model.Nota;

public interface NoteRepository extends JpaRepository<Nota, Integer>{

}
