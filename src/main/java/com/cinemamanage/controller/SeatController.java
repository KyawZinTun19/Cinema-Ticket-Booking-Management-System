package com.cinemamanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.SeatDAO;
import com.cinemamanage.dto.SeatRequestDTO;
import com.cinemamanage.dto.SeatResponseDTO;
import com.cinemamanage.model.SeatBean;

@Controller
public class SeatController {
	@Autowired
	private SeatDAO seatDAO;	
	@RequestMapping(value="/seatcss",method=RequestMethod.GET)
	public String css() {
		return "redirect:/CSS/seat.css";
	}
	
	@RequestMapping(value="/seatjs",method=RequestMethod.GET)
	public String javascript() {
		return "redirect:/JavaScript/seat.js";}

	
	@RequestMapping(value="/seatTable",method=RequestMethod.GET)
	public String paymentMehtodTable(ModelMap model,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return "errorPage";
				 }
				//
		ArrayList<SeatResponseDTO> list =seatDAO.selectAll();
		model.addAttribute("seatlist", list);
		System.out.print(list.size());

		int seatAPrice =list.get(0).getSeatPrice();
		int seatBPrice =list.get(10).getSeatPrice();
		int seatCPrice =list.get(20).getSeatPrice();
		int seatDPrice =list.get(30).getSeatPrice();
		int seatEPrice =list.get(40).getSeatPrice();
		int seatFPrice =list.get(50).getSeatPrice();
		
		model.addAttribute("seatAPrice", seatAPrice);
		model.addAttribute("seatBPrice", seatBPrice);
		model.addAttribute("seatCPrice", seatCPrice);
		model.addAttribute("seatDPrice", seatDPrice);
		model.addAttribute("seatEPrice", seatEPrice);
		model.addAttribute("seatFPrice", seatFPrice);
		return "seatTable";
	}
	
	@RequestMapping(value="/setupUpdateseat/{seatId}",method=RequestMethod.GET)
	public ModelAndView setupUpdatePaymentMethod(@PathVariable String seatId,HttpSession session){
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return new ModelAndView("errorPage","seatBean",new SeatBean());
				 }
				//
		SeatRequestDTO req = new SeatRequestDTO();
		req.setSeatID(Integer.parseInt(seatId));
		
		
		return new ModelAndView("seatUpdate","seatBean",seatDAO.selectOne(req));
	}
	
	@RequestMapping(value="/updateSeat", method= RequestMethod.POST)
	public String updatePaymentMethod(@ModelAttribute("seatBean") @Validated SeatBean bean, 
			BindingResult bs,ModelMap model,HttpSession session) {
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return "errorPage";
		 }
		//
		if(bs.hasErrors()) {
			model.addAttribute("error","Update Failed");
			return "seatUpdate";
		}
		else if(Integer.parseInt(bean.getSeatPrice())<999) {
			model.addAttribute("error","Update Failed! Seat Price cannot be lower than 1000MMK");
			return "seatUpdate";
		}
		
		else {
			 
			SeatRequestDTO dto = new SeatRequestDTO();
			dto.setSeatID(Integer.parseInt(bean.getSeatID()));
			dto.setSeatName(bean.getSeatRow());
			dto.setSeatPrice(Integer.parseInt(bean.getSeatPrice()));

			int rs = seatDAO.updateData(dto);
			if(rs==0) {
				model.addAttribute("error","Update Failed");
				return"seatUpdate";
			}else {
				return "redirect:/seatTable";
			}
			 
		}

		}
	



}
