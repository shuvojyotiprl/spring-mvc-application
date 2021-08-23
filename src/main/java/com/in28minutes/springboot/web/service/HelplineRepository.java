package com.in28minutes.springboot.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.web.model.HelplineReq;

public interface HelplineRepository extends JpaRepository<HelplineReq, Integer>{
	List<HelplineReq> findByUser(String user);
	
	List<HelplineReq> findByCategory(String category);
}
