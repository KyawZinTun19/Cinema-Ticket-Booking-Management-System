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
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.model.AccountBean;

@Controller
public class UserSignUpController {

	@RequestMapping (value = "/setupUserSignUp",method = RequestMethod.GET)
	public ModelAndView touserSignUp() {
		return new ModelAndView("userSignUp","accountBean",new AccountBean());
	}
	
	@RequestMapping (value = "/userSignUp",method = RequestMethod.POST)
	public String userSignUp(@ModelAttribute("accountBean")@Validated AccountBean bean,BindingResult result,ModelMap model,HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("error","Name,Email and Password required!");
			return "userSignUp";
		}
		//name validation add
		char[] nameA = bean.getAccountName().toCharArray();
		if(nameA.length <= 4) {
			model.addAttribute("error","Name should be more than 4 letter!");
			return "userSignUp";
		}
		
		for(int i=0;i<nameA.length;i++) {
			int ascii=nameA[i];
			
			if(!(ascii > 64 && ascii < 91  || ascii > 96 && ascii < 123 || ascii == 32)) {
				model.addAttribute("error","Name can't contain special character or Number!");
				return "userSignUp";
			}
		}
		
		boolean error = false;
		
		// select all emails from banlist and validated with login email 
		AccountDAO dao=new AccountDAO();
		ArrayList<AccountResponseDTO> banlist = dao.selectAllBannedUsers();
		Iterator<AccountResponseDTO> data=banlist.iterator();
				
		while(data.hasNext()) {
			AccountResponseDTO dto=data.next();
					
			if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
				model.addAttribute("error","Your Email is temporarily BANNED from our website!");
				error = true;
				return "userSignUp";
				}
		}
		//
		String [] emails=bean.getAccountEmail().split("@");
		 
		if(!emails[1].equals("gmail.com")) {
			model.addAttribute("error","Invaild Email!");
			return "userSignUp";
		}
		// select all emails from from and validated with login email
		AccountRequestDTO rdto= new AccountRequestDTO();
		
		ArrayList<AccountResponseDTO> datalist = dao.selectAllNomalUsers();
		Iterator<AccountResponseDTO> it=datalist.iterator();
		
		while(it.hasNext()) {
              AccountResponseDTO dto=it.next();
			
			if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
				error = true;
				model.addAttribute("error","This email is already registered!");
				return "userSignUp";
			}
		}
		
		if(bean.getAccountPassword().equals(bean.getConfirmPassword())) {
			error=false;
		}
		else  {
			error=true;
			model.addAttribute("error","Password must be the same!");
		}
		
		char[] password=bean.getAccountPassword().toCharArray();
		for(int i=0;i<password.length;i++) {
			int space=password[i];
			
			if(space == 32) {
				error = true;
				model.addAttribute("error","Password must not be contain space!");
			}
		}
		if(password.length < 7) {
			error = true;
			model.addAttribute("error","Password must be 8-20!!");
		}
		else if(password.length > 20){
		    error = true;
			model.addAttribute("error","Password must be 8-20!!");
		}
		
		if(bean.getAccountPassword().equals("12345678")) {
			error = true;
			model.addAttribute("error","Password  too weak!");
		}
		
		if(error== true) {
			return "userSignUp";
		}
		else {
		 
			
			bean.setAccountRole("user");
			rdto.setAccountName(bean.getAccountName());
			rdto.setAccountEmail(bean.getAccountEmail());
			rdto.setAccountPassword(bean.getAccountPassword());
			rdto.setAccountRole(bean.getAccountRole());
			rdto.setAccountStatus("unban");
			dao.insertData(rdto);
			
			System.out.print(dao.searchUserIdbyEmail(bean.getAccountEmail()));
			session.setAttribute("userId",dao.searchUserIdbyEmail(bean.getAccountEmail()));
			session.setAttribute("currentUser",bean.getAccountEmail());
			return "redirect:/";
		}
	}
}
