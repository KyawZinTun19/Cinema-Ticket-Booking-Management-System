package com.cinemamanage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.TimetableDAO;
import com.cinemamanage.dto.TimeTableRequestDTO;
import com.cinemamanage.model.TimeTableBean;

@Controller
public class TimeTabelController {

	@RequestMapping(value = "/manageMovieStartTime/{movieId}/{movieName}", method = RequestMethod.GET ) 
	public ModelAndView manageMovie(@PathVariable String movieId,@PathVariable String movieName,ModelMap model,HttpSession session) {
		 
		TimeTableBean bean = new TimeTableBean();
		bean.setMovieId(Integer.valueOf(movieId));
	    model.addAttribute("movieName",movieName);
	    
		return new ModelAndView("addTimeTable","timeTableBean",bean);
		
	}
	
	@RequestMapping (value = "/addMovieStartTime" , method = RequestMethod.POST)
	public String addMovieStartTime (@ModelAttribute("timeTableBean")@Validated TimeTableBean bean,BindingResult rs,ModelMap model) {
		if(rs.hasErrors()) {
			model.addAttribute("error","Data can't be blank!");
			return "addTimeTable";
		}
		
		TimetableDAO dao = new TimetableDAO();
		TimeTableRequestDTO dto = new TimeTableRequestDTO();
		dto.setMovie_id(Integer.valueOf(bean.getMovieId()));
		dto.setStart_date(bean.getStartDate());
		dto.setEnd_date(bean.getEndDate());
		dto.setStart_time(bean.getStartTime());
		
		int result = dao.insertData(dto);
		if(result == 0) {
			model.addAttribute("error","Add Failed!, Something went wrong!");
			return "addTimeTable";
		}
		return "redirect:/movieTable";
		
	}
}
