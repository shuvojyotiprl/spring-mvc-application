package com.in28minutes.springboot.web.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.in28minutes.springboot.web.model.User;

public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	private boolean active;

	private List<GrantedAuthority> authotities;
	
	
	
	CustomUserDetails(User user){
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.active = user.isActive();
		
		this.authotities = Stream.of(user.getRoles().split(",")).map(x->{
			return new SimpleGrantedAuthority(x);
		}).collect(Collectors.toList());
		
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authotities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
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
