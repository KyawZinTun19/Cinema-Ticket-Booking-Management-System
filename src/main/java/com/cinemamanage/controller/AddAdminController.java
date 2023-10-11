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
public class AddAdminController {

	@RequestMapping (value = "/toaddAdmin",method = RequestMethod.GET)
	public ModelAndView toaddAdmin(HttpSession session) {
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return new ModelAndView("errorPage","accountBean",new AccountBean());
		}
		//
		return new ModelAndView("addAdmin","accountBean",new AccountBean());
	}
	
	@RequestMapping (value = "/addAdmin",method = RequestMethod.POST)
	public String addAdmin(@ModelAttribute("accountBean")@Validated AccountBean bean,BindingResult result,ModelMap model,HttpSession session) {
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return  "errorPage";
		}
		//
		if(result.hasErrors()) {
			model.addAttribute("error","Name,Email and Password required!");
			return "addAdmin";
		}
		//name validation add
		char[] nameA = bean.getAccountName().toCharArray();
		if(nameA.length < 8) {
			model.addAttribute("error","Name can't contain special character!");
			return "userSignUp";
		}
		
		for(int i=0;i<nameA.length;i++) {
			int ascii=nameA[i];
			
			if(!(ascii > 64 && ascii < 91  || ascii > 96 && ascii < 123 || ascii == 32)) {
				model.addAttribute("error","Name can't contain special character or Number!");
				return "userSignUp";
			}
		}
		//
		String [] emails=bean.getAccountEmail().split("@");
		 
		if(!emails[1].equals("gmail.com")) {
			model.addAttribute("error","Invaild Email!");
			return "addAdmin";
		}
		boolean error = false;
		
		AccountRequestDTO rdto= new AccountRequestDTO();
		AccountDAO dao=new AccountDAO();
		ArrayList<AccountResponseDTO> datalist = dao.selectAllAdmins();
		Iterator<AccountResponseDTO> it=datalist.iterator();
		
		while(it.hasNext()) {
              AccountResponseDTO dto=it.next();
			 
			if(bean.getAccountEmail().equals(dto.getAccountEmail())) {
				model.addAttribute("error","This email is already registered!");
				error = true;
				return "addAdmin";
			}
		}
		
		if(! bean.getAccountPassword().equals(bean.getConfirmPassword())) {
			model.addAttribute("error","Password must be the same!");
			error=true;
		}
		 
		
		char[] password=bean.getConfirmPassword().toCharArray();
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
		if(password.length > 20){
		    error = true;
			model.addAttribute("error","Password must be 8-20!!");
		}
		 
		if(bean.getAccountPassword().equals("12345678")) {
			error = true;
			model.addAttribute("error","Password too weak!");
		}
		 
		 
		if(error == true) {
			return "addAdmin";
		}
		else {
			rdto.setAccountEmail(bean.getAccountEmail());
			rdto.setAccountName(bean.getAccountName());
			rdto.setAccountPassword(bean.getAccountPassword());
			rdto.setAccountRole("admin");
			dao.insertData(rdto);
			return "redirect:/adminTable";
		}
	}
	
}
