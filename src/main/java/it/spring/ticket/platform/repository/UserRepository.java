package it.spring.ticket.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.spring.ticket.platform.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
