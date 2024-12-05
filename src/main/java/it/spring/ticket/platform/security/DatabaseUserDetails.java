package it.spring.ticket.platform.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import it.spring.ticket.platform.model.Roles;
import it.spring.ticket.platform.model.User;

public class DatabaseUserDetails implements UserDetails{
	
private final Integer id;
private final String email;
private final String password;
private final HashSet<GrantedAuthority> authorities;


public DatabaseUserDetails(User user) {
	this.id = user.getId();
	this.email = user.getEmail();
	this.password = user.getPassword();
	authorities = new HashSet<GrantedAuthority>();
	for (Roles role : user.getRoles()) {
		authorities.add(new SimpleGrantedAuthority(role.getName()));
	}
	
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return this.authorities;
}


@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return this.password;
}


@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return this.email;
}


@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}


@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}


@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}


@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}




}
