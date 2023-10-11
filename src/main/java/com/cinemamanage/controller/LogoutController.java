package com.cinemamanage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.model.AccountBean;

@Controller
public class LogoutController {

	@RequestMapping(value = "/userLogout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//------check session-----
		 
		//
		
		session.removeAttribute("currentUser");
		 
		return "redirect:/setupAccountLogin";
	}
	
	@RequestMapping(value = "/adminLogout",method = RequestMethod.GET)
	public String adminLogout(HttpSession session) {
		 
		session.removeAttribute("currentAdmin");
		session.removeAttribute("currentAdminName");
		return "redirect:/setupAccountLogin";
	}
}
