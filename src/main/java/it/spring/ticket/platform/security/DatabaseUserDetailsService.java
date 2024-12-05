package it.spring.ticket.platform.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.spring.ticket.platform.model.User;
import it.spring.ticket.platform.repository.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional <User> userByEmail = userRepo.findByEmail(username);
		
		if(userByEmail.isPresent()) {
			return new DatabaseUserDetails(userByEmail.get());
		}
		else {
			throw new UsernameNotFoundException("Non è registrato nessun utente con questa Email");
		}
	}
	


}
