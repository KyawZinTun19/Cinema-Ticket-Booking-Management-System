package com.cinemamanage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinemamanage.dao.HomePageDAO;
import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dao.PaymentDAO;
import com.cinemamanage.dao.SeatDAO;
import com.cinemamanage.dto.DetailTicketRequestDTO;
import com.cinemamanage.dto.DetailTicketResponseDTO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.dto.PaymentResponseDTO;
import com.cinemamanage.dto.SeatHasTableResponseDTO;
import com.cinemamanage.dto.SeatResponseDTO;
import com.cinemamanage.dto.TimeTableRequestDTO;
import com.cinemamanage.dto.UserMovieDetailResponseDTO;
@Controller
public class HomePageController {
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private SeatDAO seatDAO;
    @Autowired
    private PaymentDAO paymentDAO;


	
	@Autowired
	private HomePageDAO homePageDAO;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String userHomePage(ModelMap model) {
		try {
			ArrayList<MovieResponseDTO> list =movieDAO.selectAllNonDeletedMovie();
			model.addAttribute("movielist", list);
		}catch(NullPointerException e) {
			return "errorPage";
		}
		return "homePage";
	}
	
	@RequestMapping(value = "/contactUs",method = RequestMethod.GET)
	public String toContantUs(HttpSession session) {
		//------check session-----
				if(session.getAttribute("currentUser")==null) {
					return  "errorPage";
				 }
				//
		return "contantus";
	}
	
	@RequestMapping(value="/setupUserDetailMovie/{movieCode}",method=RequestMethod.GET)
	public String setupUserDetailMovie(@PathVariable String movieCode,ModelMap model,HttpSession session){
		
		//------check session-----
		if(session.getAttribute("currentUser")==null) {
			return  "errorPage";
		 }
		//
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieID(Integer.parseInt(movieCode));
		movieDAO.selectOneUser(req);

		MovieResponseDTO list = movieDAO.selectOneUser(req);

		session.setAttribute("movieName", list.getMovieName());
		model.addAttribute("movieGenre", list.getMovieGenre());
		model.addAttribute("movieDuration", list.getMovieDuration());
		model.addAttribute("movieDescription", list.getMovieDescription());
		model.addAttribute("movieDetailImage", list.getImageData());
		System.out.print(list.getImageData());
		session.setAttribute("movieID", list.getMovieID());
		
		UserMovieDetailResponseDTO moviedetaillist = homePageDAO.selectDetails(req);
		session.setAttribute("timetableID", moviedetaillist.getTimetableID());
		model.addAttribute("startDate", moviedetaillist.getStartDate());
		model.addAttribute("startTime", moviedetaillist.getStartTime());

		return "userMovieDetails";
	}
	
	
	@RequestMapping(value="/ticketDetail/{userId}/{paymentId}/",method=RequestMethod.GET)
	public String ticketDetail(@PathVariable String userId,@PathVariable String paymentId,ModelMap model,HttpSession session){
	    if(session.getAttribute("currentUser")==null) {
	        return  "errorPage";
	       }
		DetailTicketRequestDTO req = new DetailTicketRequestDTO();
		req.setAccountId(Integer.parseInt(userId));
		req.setPayment_id(Integer.parseInt(paymentId));
		
		
		HomePageDAO dao = new HomePageDAO();
		DetailTicketResponseDTO dto = dao.forDetailTicket(req);
		String seats = dto.getSeatName();
		String[] seatsArray = seats.split(", ");
		int size = seatsArray.length;

		model.addAttribute("detailTicketMovieName", dto.getMovieName());
		model.addAttribute("TicketDetailTotalSeat", dto.getSeatName());
		model.addAttribute("ticketDetailAmount", dto.getAmount());
		model.addAttribute("ticketDetailShowDate", dto.getStartDate());
		model.addAttribute("ticketDetailShowTime", dto.getStartTime());
		model.addAttribute("ticketDetailSeatSize", size);


		return "ticket";
	}
	
	@RequestMapping(value="/myticket",method=RequestMethod.GET)
    public String detailTicket(ModelMap model,HttpSession session) {
      if(session.getAttribute("currentUser")==null) {
          return  "errorPage";
         }
        HomePageDAO homepageDAO = new HomePageDAO();
		Integer userId = (Integer) session.getAttribute("userId");
		 
		DetailTicketRequestDTO dto = new DetailTicketRequestDTO();
		dto.setAccountId(userId);
		ArrayList<DetailTicketResponseDTO> ticketlist = homepageDAO.userTicketTable(dto);
	  
		model.addAttribute("confirmedTicketList", ticketlist);
		
		ArrayList<DetailTicketResponseDTO> pendingticketlist = homepageDAO.userPendingTicketTable(dto);
		//......................
		System.out.print(pendingticketlist.size());
		
		model.addAttribute("pendingticketlist", pendingticketlist);
	    ArrayList<DetailTicketResponseDTO> rejectedticketlist = homepageDAO.userRejectedTicketTable(dto);
	    model.addAttribute("rejectedticketlist", rejectedticketlist);
		model.addAttribute("confirmedTicketList", ticketlist);
	
		 
      return "ticketTable";
      }
	
	@RequestMapping(value="/userseat",method=RequestMethod.GET)
	public String userSeat(HttpSession session,ModelMap model) {
		
		//------check session-----
		if(session.getAttribute("currentUser")==null) {
			return  "errorPage";
		 }
		//
		ArrayList<SeatResponseDTO> list =seatDAO.selectAll();
		//model.addAttribute("seatlist", list);
		TimeTableRequestDTO timetabledto = new TimeTableRequestDTO();
		timetabledto.setTimeTableID(Integer.parseInt(session.getAttribute("timetableID").toString()));

		ArrayList<SeatHasTableResponseDTO> checkingSeat = homePageDAO.selectForSeat(timetabledto);
		ArrayList<String> seatNameList = new ArrayList<String>();
		for(SeatHasTableResponseDTO sht:checkingSeat) {
			String name = sht.getSeatName();
			seatNameList.add(name);
		}
		session.setAttribute("seatNameList", seatNameList);
		
		int seatAPrice =list.get(0).getSeatPrice();
		int seatBPrice =list.get(10).getSeatPrice();
		int seatCPrice =list.get(20).getSeatPrice();
		int seatDPrice =list.get(30).getSeatPrice();
		int seatEPrice =list.get(40).getSeatPrice();
		int seatFPrice =list.get(50).getSeatPrice();
		
		session.setAttribute("seatAPrice", seatAPrice);
		session.setAttribute("seatBPrice", seatBPrice);
		session.setAttribute("seatCPrice", seatCPrice);
		session.setAttribute("seatDPrice", seatDPrice);
		session.setAttribute("seatEPrice", seatEPrice);
		session.setAttribute("seatFPrice", seatFPrice);
		
		return "userSeat";
	}
//	
//	@RequestMapping(value="/ticketDetail",method=RequestMethod.GET)
//	public String ticketDetail(ModelMap model) {
//		//ArrayList<MovieResponseDTO> list =movieDAO.selectAllNonDeletedMovie();
//		//model.addAttribute("movielist", list);
//		
//
//		return "ticket";
//	}
	
	
}
