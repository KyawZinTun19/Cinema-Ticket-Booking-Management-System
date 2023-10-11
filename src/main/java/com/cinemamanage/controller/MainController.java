package com.cinemamanage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.model.MovieBean;

@Controller
public class MainController {

	
	@RequestMapping (value = "/seat", method = RequestMethod.GET)
	public String choseSeat(HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				}
				//
		return "userSeat";
	}
	
	@RequestMapping (value = "/usertable" , method = RequestMethod.GET)
	public String usertable (ModelMap model,HttpSession session) {

		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				}
				//
		AccountDAO dao = new AccountDAO();
		 
		ArrayList<AccountResponseDTO> userList = dao.selectAllNomalUsers();
		ArrayList<AccountResponseDTO> banList =  dao.selectAllBannedUsers();
		model.addAttribute("userlist",userList);
		model.addAttribute("banlist",banList);
		return "userTable";
	}
	
	 
	
	@RequestMapping(value = "/adminTable",method = RequestMethod.GET) 
	public String adminTable(ModelMap model ,HttpSession session) {
		
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return  "errorPage";
		 }
		//
		
		AccountDAO dao = new AccountDAO();
		ArrayList<AccountResponseDTO> datalist = dao.selectAllAdmins();
		model.addAttribute("adminlist",datalist);
		return "adminTable";
	}
	
	 
}
