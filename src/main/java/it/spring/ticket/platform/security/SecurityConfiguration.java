package it.spring.ticket.platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		
		.requestMatchers("/dashboard-admin","/modifica-profilo/**","/modifica-ticket/**","/crea-ticket").hasAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST, "/crea-ticket", "/modifica-profilo/**").hasAuthority("ADMIN")
		.requestMatchers("/tickets", "/tickets/**", "/il-mio-profilo").hasAnyAuthority("USER", "ADMIN")
		.requestMatchers(HttpMethod.POST, "/tickets", "/tickets/**").hasAnyAuthority("USER", "ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin().and().logout().and().exceptionHandling()
		.and().csrf().disable();
		
		return http.build();
	}
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}
