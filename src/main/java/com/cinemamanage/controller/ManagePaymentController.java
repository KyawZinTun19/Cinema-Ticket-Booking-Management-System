package com.cinemamanage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dao.PaymentDAO;
import com.cinemamanage.dao.PaymentMethodDAO;
import com.cinemamanage.dao.SeatHasTimetableDAO;
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.dto.MovieAndTimetableResponseDTO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.dto.PaymentMethodRequestDTO;
import com.cinemamanage.dto.PaymentMethodResponseDTO;
import com.cinemamanage.dto.PaymentRequestDTO;
import com.cinemamanage.dto.PaymentResponseDTO;
import com.cinemamanage.dto.SeatHasTableRequestDTO;
import com.cinemamanage.model.PaymentBean;

@Controller
public class ManagePaymentController {

	@RequestMapping(value = "/managePayment", method = RequestMethod.GET)
	public ModelAndView managePayment(HttpSession session) {
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return  new ModelAndView("errorPage","paymentBean",new PaymentBean());
		 }
		//
		PaymentDAO dao =  new PaymentDAO();
		ArrayList<PaymentResponseDTO> datalist = dao.selectAllPendingPayment();
		return new ModelAndView("managePayment","paymentBean",datalist);
	}
	
	@RequestMapping (value = "/userPaymentTable",method = RequestMethod.GET)
	public String userPaymentTable(ModelMap model,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return   "errorPage";
				 }
				//
		PaymentDAO dao = new PaymentDAO();
		//pending list
		ArrayList<PaymentResponseDTO> datalist = dao.selectAllPendingPayment();
		model.addAttribute("datalist",datalist);
		//confirmed list
		ArrayList<PaymentResponseDTO> confirmlist = dao.selectAllConfirmedPayment();
		model.addAttribute("confirmlist",confirmlist);
		//rejected list
		ArrayList<PaymentResponseDTO> rejectlist = dao.selectAllRejectedPayment();
		model.addAttribute("rejectlist",rejectlist);
		return "userPaymentTable";
	}
	
	@RequestMapping(value = "/userPaymentDetails/{paymentId}",method = RequestMethod.GET)
	public ModelAndView  paymentDetails(@PathVariable String paymentId,ModelMap model,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentAdmin")==null) {
					return  new ModelAndView("errorPage","paymentBean",new PaymentBean());
				 }
				//
				PaymentDAO dao = new PaymentDAO();
				//PaymentRequestDTO dto = new PaymentRequestDTO();
				//dto.setAccountID(Integer.valueOf(accountId));
				PaymentResponseDTO data = dao.selectOnePayment(Integer.valueOf(paymentId));
				
				PaymentMethodDAO pdao = new PaymentMethodDAO();
				PaymentMethodRequestDTO pdto = new PaymentMethodRequestDTO();  
				pdto.setPaymentMethodID(data.getPaymentMethodID());
				PaymentMethodResponseDTO paydata = pdao.selectOne(pdto);
				
				AccountDAO adao = new AccountDAO();
				AccountRequestDTO adto = new AccountRequestDTO();
				adto.setAccountID(data.getAccountID());
				AccountResponseDTO accdto = adao.selectOne(adto);
				
				MovieDAO mdao = new MovieDAO();
				MovieRequestDTO mdto = new MovieRequestDTO();
				mdto.setMovieID(data.getMovieID());
				MovieAndTimetableResponseDTO moviedto =mdao.selectOneMovieAndTime(mdto);
				
				model.addAttribute("userName",accdto.getAccountName());
				model.addAttribute("paymentMethod",paydata.getPaymentMethodName()+" Pay - "+paydata.getPaymentMethodPhone());
				model.addAttribute("movieName",moviedto.getMovieName());
				return new ModelAndView("paymentManagement","data",data);
			}
	
	@RequestMapping (value = "/managePayment/{paymentStatus}/{paymentId}",method = RequestMethod.GET)
	public String managePayment(@PathVariable String paymentStatus,@PathVariable String paymentId,HttpSession session) {
		//------check session-----
		if(session.getAttribute("currentAdmin")==null) {
			return   "errorPage";
		 }
		SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
		PaymentDAO dao = new PaymentDAO();
		PaymentRequestDTO dto = new PaymentRequestDTO();
		dto.setPaymentStatus(paymentStatus);
		dto.setPaymentID(Integer.valueOf(paymentId));
		int result = dao.updatePaymentStatus(dto);
		if(result == 0) {
			return "redirect:/userPaymentTable";
		}
		if(paymentStatus.equals("rejected")) {
			System.out.print("rejected ");
			
		}
		else {
			System.out.print("confirmed ");
			shtdto.setStatus("occupied");
			shtdto.setPaymentID(Integer.valueOf(paymentId));
			SeatHasTimetableDAO shtDAO = new SeatHasTimetableDAO();
			shtDAO.updateData(shtdto);
		}
		return "redirect:/userPaymentTable";
	 
	}
}
