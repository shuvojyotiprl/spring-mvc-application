package com.in28minutes.springboot.web.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.User;
import com.in28minutes.springboot.web.service.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user = userRepository.findByusername(username);
		
		user.orElseThrow(()->
			new UsernameNotFoundException("User not Found :: "+username)
		);
		
		
		return new CustomUserDetails(user.get());
		
		//return null;
	}

}
