package it.spring.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spring.ticket.platform.model.Stato;

public interface StatiRepository extends JpaRepository <Stato, Integer>{

}
