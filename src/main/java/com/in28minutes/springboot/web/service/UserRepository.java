package com.in28minutes.springboot.web.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.web.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByusername(String username);
	
	

}
