package com.management.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Dashboard {
	
	@ModelAttribute
	public void addcommondata(Model model , Principal principal) {
		String username =principal.getName(); 
		System.out.println("username -----.>>>" + username);
		
		
	}
}
