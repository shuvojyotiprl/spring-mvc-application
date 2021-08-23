package com.in28minutes.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.web.model.HelplineReq;
import com.in28minutes.springboot.web.service.HelplineRepository;

@Controller
public class HelplineController {

	@Autowired
	HelplineRepository helplineRepo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-helpline", method = RequestMethod.GET)
	public String showHelplineReq(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("name", name);
		model.put("tasks", helplineRepo.findByUser(name));
		return "list-helpline";
	}

	@RequestMapping(value = "/create-ticket", method = RequestMethod.GET)
	public String showCreateTicketPage(ModelMap model) {
		HelplineReq helplinereq = new HelplineReq();
		model.addAttribute("helpline", helplinereq);
		return "helpline";
	}

	@RequestMapping(value = "/create-ticket", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid HelplineReq helpline, BindingResult result) {

		if (result.hasErrors()) {

			result.getAllErrors().stream().forEach(System.out::println);
			return "redirect:/create-ticket";
		}

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);

		helpline.setCreateddate(new Date(strDate));
		helpline.setLastupdated(new Date(strDate));
		helpline.setStatus("Created");
		helpline.setUser(getLoggedInUserName(model));

		HelplineReq saved = helplineRepo.save(helpline);

		return "redirect:/list-helpline";
	}

	@RequestMapping(value = "/update-ticket", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		HelplineReq ticket = helplineRepo.findById(id).get();
		// Todo todo = service.retrieveTodo(id);
		model.put("helpline", ticket);
		return "helpline";
	}

	@RequestMapping(value = "/update-ticket", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid HelplineReq ticket, @RequestParam int id, BindingResult result) {

		HelplineReq ticketold = helplineRepo.findById(id).get();

		if (result.hasErrors()) {
			return "helpline";
		}

		ticketold.setCategory(ticket.getCategory());
		ticketold.setDescription(ticket.getDescription());

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:s");
		String strDate = formatter.format(date);

		ticketold.setLastupdated(new Date(strDate));

		helplineRepo.save(ticketold);

		return "redirect:/list-helpline";
	}

	@RequestMapping(value = "/delete-ticket", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {

		// if(id==1)
		// throw new RuntimeException("Something went wrong");
		helplineRepo.deleteById(id);
		// service.deleteTodo(id);
		return "redirect:/list-helpline";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

}
