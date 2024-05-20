package com.epam.school.controller;

import com.epam.school.entities.EpamUser;
import com.epam.school.service.impl.SchoolUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SignUpController {
	
	@Autowired 
	SchoolUserDetailsService userDetailsService;
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String newUser(Model model, @ModelAttribute("newUser") EpamUser newUser) {
		if(userDetailsService.findByEmail(newUser.getEmail()) == null) {
			userDetailsService.save(newUser);
			return "login.html";
		}
		model.addAttribute("message", "The email that you are trying to use is already registered");
		return "signUp.html";
	}
	
	
}