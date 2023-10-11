package com.cinemamanage.controller;

import java.util.ArrayList;

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

import com.cinemamanage.dao.PaymentMethodDAO;
import com.cinemamanage.dto.PaymentMethodRequestDTO;
import com.cinemamanage.dto.PaymentMethodResponseDTO;
import com.cinemamanage.model.PaymentMethodBean;


@Controller
public class PaymentMethodController {
	@Autowired
	private PaymentMethodDAO paymentMethodDAO;
	@RequestMapping(value="/paymentMethodTable",method=RequestMethod.GET)
	public String paymentMehtodTable(ModelMap model) {
		ArrayList<PaymentMethodResponseDTO> list =paymentMethodDAO.selectAll();
		model.addAttribute("paymentmethodlist", list);
		return "paymentMethodTable";
	}
	
	@RequestMapping(value="/setupAddPaymentMethod",method=RequestMethod.GET)
	public ModelAndView setupAddPaymentMethod(){
		return new ModelAndView("addPaymentMethod","paymentMethodBean",new PaymentMethodBean());
	}
	@RequestMapping(value="/addPaymentMethod", method= RequestMethod.POST)
	public String addPaymentMethod(@ModelAttribute("paymentMethodBean")@Validated PaymentMethodBean bean, 
			BindingResult bs,ModelMap model) {
		
		if(bs.hasErrors()) {
			model.addAttribute("error", "Data can't be blank!");
			return "addPaymentMethod";
		}
		boolean error = false,pmError = false;
		
		ArrayList<String> pmlist = new ArrayList<String>();
		pmlist.add("KBZ");
		pmlist.add("WAVE");
		pmlist.add("UAB");
		pmlist.add("AYA");
		pmlist.add("A+");
		pmlist.add("CITIZEN");
		pmlist.add("CB");
		
		for(String s:pmlist) {
			 
			if(s.equalsIgnoreCase(bean.getPaymentMethodName())) {
				pmError = false;
				break;
			}
			
			else{
				model.addAttribute("error","Invaild PaymentMethod!");
				pmError = true;
			}
			
		}
		
		char[] phoneNumber = bean.getPaymentMethodPhone().toCharArray();
		if(phoneNumber.length < 8 || phoneNumber.length > 12) {
			model.addAttribute("error","Invaild Phone Number!");
			error = true;
		}
		if(phoneNumber[0]!='0' || phoneNumber[1]!='9') {
			model.addAttribute("error","Invaild Phone Number!");
			error = true;
		}
		
		
	    if (paymentMethodDAO.isPhoneNoExists(bean.getPaymentMethodName(),bean.getPaymentMethodPhone())) {
	        bs.rejectValue("paymentMethodPhone", "error.paymentMethodPhone", "Payment Method with same phone number already exist!");
	        model.addAttribute("error", "Payment Method with same phone number already exist!");
	        return "addPaymentMethod";
	    }
	    
	    
		if(pmError == false && error == false) {
			PaymentMethodRequestDTO dto = new PaymentMethodRequestDTO();
			//dto.setPaymentMethodID(Integer.parseInt(bean.getPaymentMethodID()));
			dto.setPaymentMethodName(bean.getPaymentMethodName().toUpperCase());
			dto.setPaymentMethodPhone(bean.getPaymentMethodPhone());
			int rs = paymentMethodDAO.insertData(dto);
			if(rs==0) {
				model.addAttribute("error","Insert Failed");
				return "addPaymentMethod";
			}
		   return "redirect:/paymentMethodTable";
	        }
		else {
			return "addPaymentMethod";
	        }
	}
	
	@RequestMapping(value="/setupUpdatePaymentMethod/{paymentMethodCode}",method=RequestMethod.GET)
	public ModelAndView setupUpdatePaymentMethod(@PathVariable String paymentMethodCode){
		PaymentMethodRequestDTO req = new PaymentMethodRequestDTO();
		req.setPaymentMethodID(Integer.parseInt(paymentMethodCode));
		
		return new ModelAndView("paymentMethodUpdate","paymentMethodBean",paymentMethodDAO.selectOne(req));
	}
	
	@RequestMapping(value="/updatePaymentMethod", method= RequestMethod.POST)
	public String updatePaymentMethod(@ModelAttribute("paymentMethodBean") @Validated PaymentMethodBean bean, 
			BindingResult bs,ModelMap model) {
		if(bs.hasErrors()) {
			return "paymentMethodUpdate";
		} 
		
boolean error = false,pmError = false;
		
		ArrayList<String> pmlist = new ArrayList<String>();
		pmlist.add("KBZ");
		pmlist.add("WAVE");
		pmlist.add("UAB");
		pmlist.add("AYA");
		pmlist.add("A+");
		pmlist.add("CITIZEN");
		pmlist.add("CB");
		
		for(String s:pmlist) {
			 
			if(s.equalsIgnoreCase(bean.getPaymentMethodName())) {
				pmError = false;
				break;
			}
			
			else{
				model.addAttribute("error","Invaild PaymentMethod!");
				pmError = true;
			}
			
		}
		
		char[] phoneNumber = bean.getPaymentMethodPhone().toCharArray();
		if(phoneNumber.length < 7 || phoneNumber.length > 12) {
			model.addAttribute("error","Invaild Phone Number!");
			error = true;
		}
		if(phoneNumber[0]!='0' || phoneNumber[1]!='9') {
			model.addAttribute("error","Invaild Phone Number!");
			error = true;
		}
		
		
	    if (paymentMethodDAO.isPhoneNoExists(bean.getPaymentMethodName(),bean.getPaymentMethodPhone())) {
	        bs.rejectValue("paymentMethodPhone", "error.paymentMethodPhone", "Payment Method with same phone number already exist!");
	        model.addAttribute("error", "Payment Method with same phone number already exist!");
	        return "addPaymentMethod";
	    }
	    
	    
		if(pmError == false && error == false) {
			PaymentMethodRequestDTO dto = new PaymentMethodRequestDTO();
			dto.setPaymentMethodID(Integer.parseInt(bean.getPaymentMethodID()));
			dto.setPaymentMethodName(bean.getPaymentMethodName().toUpperCase());
			dto.setPaymentMethodPhone(bean.getPaymentMethodPhone());

			int rs = paymentMethodDAO.updateData(dto);
			if(rs==0) {
				model.addAttribute("error","Update Failed");
				return "addPaymentMethod";
			}
		   return "redirect:/paymentMethodTable";
	        }
		else {
			return "paymentMethodUpdate";
	        }
		 
		}
	@RequestMapping(value="/deletePaymentMethod/{paymentMethodID}", method= RequestMethod.GET)
	public String deletePaymentMethod(@PathVariable String paymentMethodID) {
		PaymentMethodRequestDTO dto = new PaymentMethodRequestDTO();
		dto.setPaymentMethodID(Integer.parseInt(paymentMethodID));
		paymentMethodDAO.deleteData(paymentMethodID);
		
		
		return "redirect:/paymentMethodTable";
		
	}
}


