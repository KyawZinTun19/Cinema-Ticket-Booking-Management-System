package com.cinemamanage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dao.PaymentDAO;
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.dto.PaymentResponseDTO;
import com.cinemamanage.model.AccountBean;

@Controller
public class AdminController {

	@Autowired
	AccountDAO accountDAO;
	@RequestMapping(value="/setupUpdateAdmin/{adminCode}",method=RequestMethod.GET)
	public ModelAndView setupUpdateAdmin(@PathVariable String adminCode,HttpSession session){
		//------check session-----
	    if(session.getAttribute("currentAdmin")==null) {
		  return   new ModelAndView("errorPage","accountBean",new AccountBean());
		 }
		//
		AccountRequestDTO req = new AccountRequestDTO();
		req.setAccountID(Integer.parseInt(adminCode));
		
		return new ModelAndView("adminUpdate","accountBean",accountDAO.selectOne(req));
	}
	
	@RequestMapping(value="/updateAdmin", method= RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("accountBean") @Validated AccountBean bean, 
			BindingResult bs,ModelMap model,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				 }
		//
		if(bs.hasErrors()) {
			model.addAttribute("error","Fields must be filled out!");
			return "adminUpdate";
		}
		
		if(!bean.getAccountPassword().equals(bean.getConfirmPassword())) {
			 
			model.addAttribute("error","Error!!The Passwords should be the same!!");
			return"adminUpdate";
		}
		else {
			AccountRequestDTO dto = new AccountRequestDTO();
			dto.setAccountID(Integer.parseInt(bean.getAccountID()));
			dto.setAccountName(bean.getAccountName());
			dto.setAccountEmail(bean.getAccountEmail());
			dto.setAccountPassword(bean.getAccountPassword());
			dto.setAccountRole(bean.getAccountRole());
			int rs = accountDAO.updateData(dto);
			if(rs==0) {
				model.addAttribute("error","Update Failed");
				return"adminUpdate";
			}else {
				return "redirect:/adminTable";
			}
		}	
	}
	
	
	@RequestMapping(value="/deleteAdmin/{adminCode}", method= RequestMethod.GET)
	public String deleteAdmin(@PathVariable String adminCode,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  "errorPage";
				 }
				//
		AccountRequestDTO dto = new AccountRequestDTO();
		
		dto.setAccountID(Integer.parseInt(adminCode));
		accountDAO.deleteData(dto);
		
		
		return "redirect:/adminTable";
	}
	
	@RequestMapping(value="/income",method=RequestMethod.GET)
	public String income(ModelMap model,HttpSession session) {
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }
		PaymentDAO paymentDAO = new PaymentDAO();
		ArrayList<PaymentResponseDTO> list= paymentDAO.selectAllNonPending();
		int amount = 0;
		for(PaymentResponseDTO res:list) {
			amount += res.getAmount();
		}
		model.addAttribute("income", amount);

		return "totalAmount";
	}
	
	@RequestMapping (value = "/userTable" , method = RequestMethod.GET)
	  public String usertable (ModelMap model,HttpSession session) {
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }
	    AccountDAO dao = new AccountDAO();
	     
	    ArrayList<AccountResponseDTO> userList = dao.selectAllNomalUsers();
	    ArrayList<AccountResponseDTO> banList =  dao.selectAllBannedUsers();
	    model.addAttribute("userlist",userList);
	    model.addAttribute("banlist",banList);
	    return "userTable";
	  }
}
