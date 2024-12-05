package it.spring.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spring.ticket.platform.model.Categoria;

public interface CategorieRepository extends JpaRepository<Categoria, Integer>{

}
