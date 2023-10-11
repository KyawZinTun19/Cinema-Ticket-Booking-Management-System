package com.cinemamanage.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.model.AccountBean;

@Controller
public class AccountController {

	@RequestMapping(value = "/setupAccountLogin", method = RequestMethod.GET)
	public ModelAndView setupAccountLogin() {
		return new ModelAndView("accountLogin","accountBean",new AccountBean());
	}
	
	@RequestMapping(value = "/accountLogin", method = RequestMethod.POST)
	public String accountLogin(@ModelAttribute("accountBean")@Validated AccountBean bean,BindingResult result,ModelMap model,HttpSession session) {
		if(result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
			model.addAttribute("error","Email and  Password required!");
			return "accountLogin";
		}
		
		int accountId = 0;
		String adminName = "";
		
		boolean error = false,email = false,password = false;
		// select all emails from banlist and validated with login email 
				AccountDAO dao=new AccountDAO();
				ArrayList<AccountResponseDTO> banlist = dao.selectAllBannedUsers();
				Iterator<AccountResponseDTO> data=banlist.iterator();
				
				while(data.hasNext()) {
					AccountResponseDTO dto=data.next();
					
					if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
						model.addAttribute("error","Your Email is temporarily BANNED from our website!");
						return "accountLogin";
						
					}
				}
				
	    // select all admin and validated with login email
				ArrayList<AccountResponseDTO> datalist = dao.selectAllAdmins();
				Iterator<AccountResponseDTO> it=datalist.iterator();
				
				while(it.hasNext()) {
					AccountResponseDTO dto=it.next();
					 
					if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
						adminName=dto.getAccountName();
						email = true;
					}
					else {
						email = false;
						model.addAttribute("error","Invaild Email or Password!");
					}
					if(bean.getAccountPassword().equals(dto.getAccountPassword())) {
						password = true;
					}	
					else {
						password = false;
						model.addAttribute("error","Invaild Email or Password!");
					}
					
					if(email == true && password == true) {
						accountId = dto.getAccountID();
						break;
					}
					if(email == true && password == false) {
						break;
					}
					
				}
				
				if(email == true && password == false) {
					model.addAttribute("error","Wrong Password. Try again!");
					return "accountLogin";
				}
				
				if(error==false && email == true && password == true) {
					session.setAttribute("adminId",accountId);
					session.setAttribute("currentAdmin",bean.getAccountEmail());
					session.setAttribute("currentAdminName",adminName);
					return "redirect:/adminTable";
				}
				 
				// select all emails from user and validated with login email
				
				ArrayList<AccountResponseDTO> userlist = dao.selectAllNomalUsers();
				Iterator<AccountResponseDTO> uit=userlist.iterator();
				
				while(uit.hasNext()) {
					AccountResponseDTO dto=uit.next();
					 
					if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
						email = true;
					}
					else {
						model.addAttribute("error","Invaild Email or Password!");
						email = false;
					}
					if(bean.getAccountPassword().equals(dto.getAccountPassword())) {
						password = true;
					}	
					else {
						model.addAttribute("error","Invaild Email or Password!");
						password = false;
					}
					
					if(email == true && password == true) {
						accountId = dto.getAccountID();
						break;
					}
					if(email == true && password == false) {
						break;
					}
					
				}
				
				if(email == true && password == false) {
					model.addAttribute("error","Wrong Password. Try again!");
					return "accountLogin";
				}
				
				if(error==false && email == true && password == true) {
				    session.setAttribute("userId",accountId);
					session.setAttribute("currentUser",bean.getAccountEmail());
					//return "homePage";
					return "redirect:/";
				}
				else {
					model.addAttribute("error","Invaild Email or Password!. Try again!");
					return "accountLogin";
				}
	}
	
}
