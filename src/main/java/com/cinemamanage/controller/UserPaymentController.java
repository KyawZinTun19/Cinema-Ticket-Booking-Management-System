package com.cinemamanage.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileCountLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.AccountDAO;
import com.cinemamanage.dao.HomePageDAO;
import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dao.PaymentDAO;
import com.cinemamanage.dao.PaymentMethodDAO;
import com.cinemamanage.dao.SeatHasTimetableDAO;
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.AccountResponseDTO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.dto.PaymentMethodRequestDTO;
import com.cinemamanage.dto.PaymentMethodResponseDTO;
import com.cinemamanage.dto.PaymentRequestDTO;
import com.cinemamanage.dto.PaymentResponseDTO;
import com.cinemamanage.dto.SeatHasTableRequestDTO;
import com.cinemamanage.dto.SeatRequestDTO;
import com.cinemamanage.dto.SeatResponseDTO;
import com.cinemamanage.model.AccountBean;
import com.cinemamanage.model.PaymentBean;

@Controller
public class UserPaymentController {
     
	@RequestMapping(value = "/userPayment",method = RequestMethod.GET)
	public ModelAndView userPayment(ModelMap model,HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentUser")==null) {
					new ModelAndView("errorPage","accountBean",new AccountBean());
				 }
				// 
		PaymentMethodDAO dao = new PaymentMethodDAO();
		ArrayList<PaymentMethodResponseDTO>data = dao.selectAll();
		Iterator<PaymentMethodResponseDTO> it = data.iterator();
		HashMap<Integer,String> datalist = new HashMap<Integer,String>();
		while(it.hasNext()) {
			PaymentMethodResponseDTO dto= it.next();
			datalist.put(dto.getPaymentMethodID(),dto.getPaymentMethodName()+" Pay-"+dto.getPaymentMethodPhone());
			 
		}
	
		model.addAttribute("paymentlist",datalist);
		model.addAttribute("error",session.getAttribute("paymenterror"));
		return new ModelAndView("userPayment","paymentbean",new PaymentBean());
	}
	
	@RequestMapping(value = "/continuePayment" , method = RequestMethod.POST)
	public String continuePayment(@ModelAttribute("bean")@Validated PaymentBean bean ,BindingResult rs,
			ModelMap model,HttpSession session) throws FileSizeLimitExceededException {
		//------check session-----
				if(session.getAttribute("currentUser")==null) {
					return "errorPage";
				 }
				// 
		if(rs.hasErrors()) {
			session.setAttribute("paymenterror","Data can't be blank!!");
			return "redirect:/userPayment";
		}
		 
		if(bean.getAmount()==null||bean.getAmount().equalsIgnoreCase("0")) {
			session.setAttribute("paymenterror","You Havent Buy Any Seat!!");
			return "redirect:/userPayment";
		}
		
		
		try {
		      MultipartFile image = bean.getTransactionImage();
		      byte[] bytes = image.getBytes();
		      
		      if(bytes.length < 1) {
		        session.setAttribute("paymenterror","Data can't be blank!");
		        return "redirect:/userPayment"; 
		      }
		      String filePatha = session.getServletContext().getRealPath("/") + "WEB-INF" 
		            + File.separator + "images" + File.separator + image.getOriginalFilename();
		          
		            String filePath = "C:\\Users\\HP\\eclipse-workspace\\CinemaTicketBooking\\webapp\\WEB-INF\\images";
		           
		              File imageFile = new File(filePath,image.getOriginalFilename());
		              image.transferTo(imageFile);
		          
		          BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(filePatha));  
		          bout.write(bytes);
		          bout.close();
		      }
		        
		      catch (IOException e) {
		        System.out.print(e);
		      }
		boolean error = false;
		char[] phoneNumber = bean.getPhone().toCharArray();
		if(phoneNumber.length < 7 || phoneNumber.length > 12) {
			session.setAttribute("paymenterror","Invaild Phone Number!");
			error = true;
		}
		if(phoneNumber[0]!='0' || phoneNumber[1]!='9') {
			session.setAttribute("paymenterror","Invaild Phone Number!");
			error = true;
		}
		 
		
				
		if(error==false) {
			PaymentRequestDTO dto = new PaymentRequestDTO();
			PaymentDAO dao = new PaymentDAO();
			try {
			dto.setAccountID(Integer.parseInt(session.getAttribute("userId").toString()));
			}
			catch(NullPointerException e) {
				session.setAttribute("paymenterror","Payment Failed!,Something went wrong!!");
				return "redirect:/userPayment"; 
			}
			Integer movieIDInteger = (Integer) session.getAttribute("movieID");
			int movieID = movieIDInteger.intValue();
			dto.setPhone(bean.getPhone());
			dto.setTransctionImage(bean.getTransactionImage().getOriginalFilename());
			dto.setPaymentTime(LocalDateTime.now());
			dto.setPaymentStatus("pending");
			dto.setPaymentMethodID(Integer.parseInt(bean.getPay()));
			dto.setMovieID(movieID);
			dto.setAmount(Integer.parseInt(bean.getAmount()));
			
			HomePageDAO homepageDAO = new HomePageDAO();
			SeatHasTimetableDAO shtDAO = new SeatHasTimetableDAO();
			
			ArrayList<String> seatAs = bean.getSeatANames();
			ArrayList<String> seatBs = bean.getSeatBNames();
			ArrayList<String> seatCs = bean.getSeatCNames();
			ArrayList<String> seatDs = bean.getSeatDNames();
			ArrayList<String> seatEs = bean.getSeatENames();
			ArrayList<String> seatFs = bean.getSeatFNames();
			
			ArrayList<SeatResponseDTO> arraylistofseatAdto = new ArrayList<SeatResponseDTO>();
			ArrayList<SeatResponseDTO> arraylistofseatBdto = new ArrayList<SeatResponseDTO>();
			ArrayList<SeatResponseDTO> arraylistofseatCdto = new ArrayList<SeatResponseDTO>();
			ArrayList<SeatResponseDTO> arraylistofseatDdto = new ArrayList<SeatResponseDTO>();
			ArrayList<SeatResponseDTO> arraylistofseatEdto = new ArrayList<SeatResponseDTO>();
			ArrayList<SeatResponseDTO> arraylistofseatFdto = new ArrayList<SeatResponseDTO>();

			
			if (seatAs.size()>0) {
			for(String s:seatAs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatAdto.add(seatres);			
			}}
			if (seatBs.size()>0) {
			for(String s:seatBs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatBdto.add(seatres);			
			}}
			if (seatCs.size()>0) {
			for(String s:seatCs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatCdto.add(seatres);			
			}}
			if (seatDs.size()>0) {
			for(String s:seatDs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatDdto.add(seatres);			
			}}
			if (seatEs.size()>0) {
			for(String s:seatEs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatEdto.add(seatres);			
			}}
			if (seatFs.size()>0) {
			for(String s:seatFs) {
				SeatRequestDTO seatdto = new SeatRequestDTO();
				SeatResponseDTO seatres = new SeatResponseDTO();
				seatdto.setSeatName(s);
				seatres = homepageDAO.selectAllseatIDS(seatdto);
				arraylistofseatFdto.add(seatres);			
			}}

			Integer timetableID = (Integer) session.getAttribute("timetableID");
			

			int result = dao.insertData(dto);
			if(result==0) {
				session.setAttribute("paymenterror","Payment Failed!,Something went wrong!!!");
				return "redirect:/userPayment";
			}
			PaymentResponseDTO lastid = new PaymentResponseDTO();
			lastid = dao.selectOneLast();
			if(arraylistofseatAdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatAdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			if(arraylistofseatBdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatBdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			if(arraylistofseatCdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatCdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			if(arraylistofseatDdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatDdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			if(arraylistofseatEdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatEdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			if(arraylistofseatFdto.size()>0) {
			for(SeatResponseDTO s:arraylistofseatFdto) {
				SeatHasTableRequestDTO shtdto = new SeatHasTableRequestDTO();
				shtdto.setSeatID(s.getSeatID());
				shtdto.setTimetableID(timetableID);
				shtdto.setPaymentID(lastid.getPaymentID());
				shtdto.setStatus("");
				shtDAO.insert(shtdto);
			}}
			
		return "paymentPending";
		}
		else {
			return "redirect:/userPayment";
		}
	}
	
	
	
	
}
