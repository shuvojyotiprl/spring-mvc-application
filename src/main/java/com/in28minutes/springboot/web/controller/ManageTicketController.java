package com.in28minutes.springboot.web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.web.model.HelplineReq;
import com.in28minutes.springboot.web.service.HelplineRepository;
import com.in28minutes.springboot.web.service.UserRepository;

@Controller
public class ManageTicketController {

	@Autowired
	HelplineRepository helplineRepo;

	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String showTickets(ModelMap model) {
		String name = getLoggedInUserName(model);

		String team = userRepo.findByusername(name).get().getTeam();

		List<HelplineReq> tickets = helplineRepo.findByCategory(team);
		
		tickets.stream().forEach(ticket->{
			if(ticket.getAssignee()==null)
				ticket.setAssignee("unassigned");
		});

		model.put("tasks", tickets);
		model.put("name", name);
		return "manage-ticket";
	}
	
	@RequestMapping(value = "/assign-ticket", method = RequestMethod.GET)
	public String assignTicket(
			ModelMap model,
			@RequestParam int id) {
		String name = getLoggedInUserName(model);
		HelplineReq ticket = helplineRepo.findById(id).get();
		
		ticket.setStatus("InPogress");
		ticket.setAssignee(name);
		ticket.setLastupdated(new Date());
		
		helplineRepo.save(ticket);
		return "redirect:/manage";
		
	}
	
	
	@RequestMapping(value = "/resolve-ticket", method = RequestMethod.GET)
	public String resolveTicketPage(
			ModelMap model,
			@RequestParam int id) {
		String name = getLoggedInUserName(model);
		HelplineReq ticket = helplineRepo.findById(id).get();
		model.put("helpline", ticket);
		return "resolve-ticket";
		//return "manage-ticket";
	}
	
	
	
	@RequestMapping(value = "/resolve-ticket", method = RequestMethod.POST)
	public String resolveTicket(
			ModelMap model,
			@RequestParam int id,
			HelplineReq helpline,
			 BindingResult result) {
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(System.out::println);
			return "redirect:/manage";
		}
		
		
		HelplineReq ticketold = helplineRepo.findById(id).get();
		
		ticketold.setCategory(helpline.getCategory());
		ticketold.setResolution(helpline.getResolution());
		ticketold.setStatus(helpline.getStatus());
		ticketold.setLastupdated(new Date());
		
		helplineRepo.save(ticketold);
		
		return "redirect:/manage";
		//return "manage-ticket";
	}
	
	
	

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}
}
