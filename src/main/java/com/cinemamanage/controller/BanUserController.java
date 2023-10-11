package com.cinemamanage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;

@Controller
public class BanUserController {

	@RequestMapping (value = "/banUser/{id}",method = RequestMethod.GET)
	public String banUser(@PathVariable String id,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				 }
				//
		AccountRequestDTO dto = new AccountRequestDTO();
		dto.setAccountID(Integer.valueOf(id));
		dto.setAccountStatus("banned");
		AccountDAO dao = new AccountDAO();
		dao.addBanlist(dto);
		
		return "redirect:/usertable";
	}
	
	@RequestMapping(value = "/disBanUser/{id}",method = RequestMethod.GET)
	public String disBanUser(@PathVariable String id,HttpSession session){
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				 }
				//
		AccountRequestDTO dto = new AccountRequestDTO();
		dto.setAccountID(Integer.valueOf(id));
		dto.setAccountStatus("unban");
		AccountDAO dao = new AccountDAO();
		dao.removeBanlist(dto);
		                         
		return "redirect:/usertable";
	}
	
}
