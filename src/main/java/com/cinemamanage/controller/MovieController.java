package com.cinemamanage.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dao.TimetableDAO;
import com.cinemamanage.dto.AccountRequestDTO;
import com.cinemamanage.dto.MovieAndTimetableRequestDTO;
import com.cinemamanage.dto.MovieAndTimetableResponseDTO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;
import com.cinemamanage.dto.TimeTableRequestDTO;
 
import com.cinemamanage.model.AccountBean;
import com.cinemamanage.model.MovieAndTimetableBean;
import com.cinemamanage.model.MovieBean;
 
@Controller
public class MovieController {
	@Autowired
	private MovieDAO movieDAO;
	
	
	@RequestMapping(value="/movieTable",method=RequestMethod.GET)
	public String movieTable(ModelMap model,HttpSession session) {
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }
		ArrayList<MovieResponseDTO> list =movieDAO.selectAllNonDeletedMovie();
		model.addAttribute("movielist", list);
		
		ArrayList<MovieResponseDTO> deletedmovielist =movieDAO.selectAllDeletedMovie();
		model.addAttribute("deletedmovielist", deletedmovielist);
		return "movieTable";
	}	

	

	
//	@RequestMapping(value="/deletedMovieTable",method=RequestMethod.GET)
//	public String deletedMovieTable(ModelMap model) {
//
//		return "movieTable";
//	}
	
	@RequestMapping(value="/setupDetailMovie/{movieCode}",method=RequestMethod.GET)
	public ModelAndView setupDetailMovie(@PathVariable String movieCode,HttpSession session){
	    if(session.getAttribute("currentAdmin")==null) {
	        return new ModelAndView("errorPage","bean",new MovieBean());
	       }
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieID(Integer.parseInt(movieCode));
		//test --
		System.out.print(movieDAO.selectOneMovieAndTime(req).getMovieName());
		return new ModelAndView("movieDetails","movieBean",movieDAO.selectOneMovieAndTime(req));
	}
	
//	Filt
	
	@RequestMapping(value="/setupAddMovie",method=RequestMethod.GET)
	public ModelAndView setupAddMovie(HttpSession session){
	    if(session.getAttribute("currentAdmin")==null) {
	        return  new ModelAndView("errorPage","bean",new MovieBean());
	       }
		return new ModelAndView("addMovie","movieAndTimetableBean",new MovieAndTimetableBean());
	}
	
	@RequestMapping(value="/addMovie", method= RequestMethod.POST)
	public String addMovie(@ModelAttribute("movieAndTimetableBean") @Validated MovieAndTimetableBean bean, 
	        BindingResult bs, ModelMap model,HttpSession session) {
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }

		
	    if (bs.hasFieldErrors("movieName") || bs.hasFieldErrors("movieDuration") 
	            || bs.hasFieldErrors("movieDescription") || bs.hasFieldErrors("movieGenre")|| bs.hasFieldErrors("startDate")||bs.hasFieldErrors("startTime")) {
	        model.addAttribute("error", "Field Cannot Be Empty");
	        return "addMovie";
	    }
	    
	    String[] parts = bean.getMovieDuration().split("Hr |min");
	    int hourFOrCheckingMorethan4 = Integer.parseInt(parts[0]);
	    //int minute = Integer.parseInt(parts[1]);
	    if(hourFOrCheckingMorethan4>4) {
	        model.addAttribute("error", "Hour Should't be more than 4!!");
	        return "AddMovie";
	    }
	    String[] startTImepart = bean.getStartTime().split(":");
	    int startTImepartInt = Integer.parseInt(startTImepart[0]);
	    if(startTImepartInt>25) {
	        model.addAttribute("error", "Hour Shouldn't be more than 24!!");
	        return "AddMovie";
	    }
	    
	    

	    // Check if the movie already exists in the database
	    if (movieDAO.isMovieExists(bean.getMovieName())) {
	        bs.rejectValue("movieName", "error.movieName", "Movie name already exists");
	        model.addAttribute("error", "Movie name already exists");
	        return "AddMovie";
	    }
		 String[] part = bean.getStartTime().split(":");
		 int hour = Integer.parseInt(part[0]);
		 System.out.print("Hour before loop"+hour);
		ArrayList<MovieAndTimetableResponseDTO> forhourlist = movieDAO.checkingTooClose(bean.getStartDate());
		 
		for(MovieAndTimetableResponseDTO s:forhourlist) {
			String[] gettimelist = s.getStartTime().split(":");
			int databasehour  = Integer.parseInt(gettimelist[0]);
			 
			if (hour > databasehour - 2) {
				System.out.print("this is in if loop +"+hour+","+ databasehour);
				model.addAttribute("error", "Time is too close to the previous movie. Movie should be 2 hour away from each other");
				return "AddMovie";
			}
			if (hour < databasehour + 2) {
				System.out.print("this is in if loop +"+hour+","+ databasehour);
				model.addAttribute("error", "Time is too close to the previous movie");
				return "AddMovie";
			}
		}
	    
	      try {
        int i=0;
        MultipartFile image = bean.getMoviePoster();
    byte[] bytes = image.getBytes();
       
      if(bytes.length < 1) {
	        model.addAttribute("error","Photo Required");
	        return "AddMovie"; 
	      }
      
     
      
      String filePatha = session.getServletContext().getRealPath("/") + "WEB-INF" 
      + File.separator + "images" + File.separator + bean.getMoviePoster().getOriginalFilename();
    
      String filePath = "C:\\Users\\HP\\eclipse-workspace\\CinemaTicketBooking\\WebContent\\WEB-INF\\images";
     
        File imageFile = new File(filePath,image.getOriginalFilename());
        image.transferTo(imageFile);
    
    BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(filePatha));  
    bout.write(bytes);
    bout.close();
  } catch (IOException e) {
    System.out.print(e);
  } 
	      
  
    String imageData = bean.getMoviePoster().getOriginalFilename();

	    MovieRequestDTO dto = new MovieRequestDTO();
	    dto.setMovieName(bean.getMovieName());
	    dto.setMovieGenre(bean.getMovieGenre());
	    dto.setMovieDescription(bean.getMovieDescription());
	    dto.setMovieDuration(bean.getMovieDuration());
	    dto.setMovieCreateTime(java.time.LocalDateTime.now().toString());
	    dto.setCreatedAdmin((session.getAttribute("currentAdminName")).toString());
	    MovieAndTimetableRequestDTO mtdto = new MovieAndTimetableRequestDTO();
	    mtdto.setStartDate(bean.getStartDate());

	    dto.setImageData(imageData);
	    
	    //----------------------------------


	    int rs = movieDAO.insertData(dto);
	    ArrayList<MovieResponseDTO> movieres = movieDAO.searchByNonDeletedMovieName(dto);
	    int movieIDForTimetable = movieres.get(0).getMovieID();
	    
	    
		TimetableDAO dao = new TimetableDAO();
		TimeTableRequestDTO timedto = new TimeTableRequestDTO();

		//dto.setMovieID(Integer.valueOf(bean.getMovieId()));
		timedto.setMovie_id(movieIDForTimetable);
		timedto.setStart_date(bean.getStartDate());
		timedto.setEnd_date(bean.getEndDate());
		timedto.setStart_time(bean.getStartTime());
	    
		int result = dao.insertData(timedto);
	    if (rs == 0 || result == 0) {
	        model.addAttribute("error", "Insert Failed");
	        return "AddMovie";
	    }
	    
	    return "redirect:/movieTable";
	}
	    
	

	
	@RequestMapping(value="/setupUpdateMovie/{movieCode}",method=RequestMethod.GET)
	public ModelAndView setupUpdateMovie(@PathVariable String movieCode,HttpSession session){
		
	    if(session.getAttribute("currentAdmin")==null) {
	        return  new ModelAndView("errorPage","bean",new MovieBean());
	       }
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieID(Integer.parseInt(movieCode));
		
		return new ModelAndView("movieUpdate","movieAndTimetableBean",movieDAO.selectOneMovieAndTime(req));
	}
	
	@RequestMapping(value="/updatemovie", method= RequestMethod.POST)
	public String updateMovie(@ModelAttribute("movieAndTimetableBean") @Validated MovieAndTimetableBean bean, 
			BindingResult bs,ModelMap model,HttpSession session) {
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }
		 if (bs.hasFieldErrors("movieName") || bs.hasFieldErrors("movieDuration") 
		            || bs.hasFieldErrors("movieDescription") || bs.hasFieldErrors("movieGenre")|| bs.hasFieldErrors("startDate")||bs.hasFieldErrors("startTime")) {
			model.addAttribute("error", "Field Cannot be Blank");
			return "movieUpdate";
		}
		 String[] parts = bean.getMovieDuration().split("Hr |min");
		    int hourFOrCheckingMorethan4 = Integer.parseInt(parts[0]);
		    //int minute = Integer.parseInt(parts[1]);
		    if(hourFOrCheckingMorethan4>4) {
		        model.addAttribute("error", "Hour Should't be more than 4!!");
		        return "movieUpdate";
		    }
		    String[] startTImepart = bean.getStartTime().split(":");
		    int startTImepartInt = Integer.parseInt(startTImepart[0]);
		    if(startTImepartInt>25) {
		        model.addAttribute("error", "Hour Shouldn't be more than 24!!");
		        return "movieUpdate";
		    }
		    
			 String[] part = bean.getStartTime().split(":");
			 int hour = Integer.parseInt(part[0]);
			  
			ArrayList<MovieAndTimetableResponseDTO> forhourlist = movieDAO.checkingTooClose(bean.getStartDate());
			 
			for(MovieAndTimetableResponseDTO s:forhourlist) {
				String[] gettimelist = s.getStartTime().split(":");
				int databasehour  = Integer.parseInt(gettimelist[0]);
				System.out.print(hour+","+databasehour);
				if (hour > databasehour - 2) {
					 
					model.addAttribute("error", "Time is too close to the previous movie. Movie should at least be 2 hour away from each other");
					return "movieUpdate";
				}
				if (hour < databasehour + 2) {
					 
					model.addAttribute("error", "Time is too close to the previous movie. Movie should at least be 2 hour away from each other");
					return "movieUpdate";
				}
			}
		 
		try {
	        int i=0;
	        MultipartFile image = bean.getMoviePoster();
	    byte[] bytes = image.getBytes();
	      System.out.print(bytes.length);
	      if(bytes.length < 1) {
		        model.addAttribute("error","Photo Required");
		        return "movieUpdate"; 
		      }
	      
	      
	     
	      
	      String filePatha = session.getServletContext().getRealPath("/") + "WEB-INF" 
	      + File.separator + "images" + File.separator + bean.getMoviePoster().getOriginalFilename();
	    
	      String filePath = "C:\\Users\\HP\\eclipse-workspace\\CinemaTicketBooking\\WebContent\\WEB-INF\\images";
	     
	        File imageFile = new File(filePath,image.getOriginalFilename());
	        image.transferTo(imageFile);
	    
	    BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(filePatha));  
	    bout.write(bytes);
	    bout.close();
	  } catch (IOException e) {
	    System.out.print(e);
	  } 
		
		
		
	  
	    String imageData = bean.getMoviePoster().getOriginalFilename();
		MovieAndTimetableRequestDTO dto = new MovieAndTimetableRequestDTO();
		dto.setMovieID(Integer.parseInt(bean.getMovieID()));
		dto.setMovieName(bean.getMovieName());
		dto.setMovieGenre(bean.getMovieGenre());
		dto.setMovieDescription(bean.getMovieDescription());
		dto.setMovieDuration(bean.getMovieDuration());
		dto.setMovieUpdateTime(java.time.LocalDateTime.now().toString());
		dto.setUpdatedAdmin((session.getAttribute("currentAdminName")).toString());
		dto.setStartDate(bean.getStartDate().toString());
		dto.setStartTime(bean.getStartTime());
		dto.setMoviePoster(imageData);
		
		
		
		
		int rs = movieDAO.updateData(dto);
		if(rs==0) {
			model.addAttribute("error","Update Failed rs");
			return "movieUpdate";
		}else {
			return "redirect:/movieTable";
			
		}	
	}
	
	@RequestMapping(value="/deleteMovie/{movieID}", method= RequestMethod.GET)
	public String deleteAdmin(@PathVariable String movieID,HttpSession session) {
		MovieRequestDTO dto = new MovieRequestDTO();
		dto.setMovieID(Integer.parseInt(movieID));
	    if(session.getAttribute("currentAdmin")==null) {
	        return  "errorPage";
	       }
		dto.setMovieDeleteTime(java.time.LocalDateTime.now().toString());
		dto.setDeletedAdmin((session.getAttribute("currentAdminName")).toString());
		movieDAO.deleteData(dto);
		
		
		return "redirect:/movieTable";
	}

}
