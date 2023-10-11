package com.cinemamanage.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinemamanage.dao.MovieDAO;
import com.cinemamanage.dto.MovieRequestDTO;
import com.cinemamanage.dto.MovieResponseDTO;

@Controller
public class MovieSearchController {
	@Autowired
	private MovieDAO movieDAO;
	
	@RequestMapping(value="/searchByMovieID/{movieID}",method=RequestMethod.GET)
	public String searchByMovieID(@PathVariable String movieID,ModelMap model){
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieID(Integer.parseInt(movieID));
		ArrayList<MovieResponseDTO> list = movieDAO.searchByNonDeletedMovieId(req);
		model.addAttribute("movielist", list);
		ArrayList<MovieResponseDTO> deletedmovie =  movieDAO.searchByDeletedMovieId(req);
		model.addAttribute("deletedmovielist",deletedmovie);
		
		return "searchedMovieTable";
	}
	
	@RequestMapping(value="/searchByMovieName/{movieName}",method=RequestMethod.GET)
	public String searchByMovieName(@PathVariable String movieName,ModelMap model){
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieName(movieName);

		ArrayList<MovieResponseDTO> list = movieDAO.searchByNonDeletedMovieName(req);;
		ArrayList<MovieResponseDTO> deletedmovie =  movieDAO.selectByDeletedMovieName(req);
		model.addAttribute("deletedmovielist",deletedmovie);
		model.addAttribute("movielist", list);
		
		return "searchedMovieTable";
	}
	
	@RequestMapping(value="/searchByMovieGenre/{movieGenre}",method=RequestMethod.GET)
	public String searchByMovieGenre(@PathVariable String movieGenre,ModelMap model){
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieGenre(movieGenre);
		ArrayList<MovieResponseDTO> list = movieDAO.searchByNonDeletedMovieGenre(req);
		model.addAttribute("movielist", list);
		ArrayList<MovieResponseDTO> deletedmovie =  movieDAO.selectByDeletedMovieGenre(req);
		model.addAttribute("deletedmovielist",deletedmovie);
		
		return "searchedMovieTable";
	}
	
	@RequestMapping(value="/searchByMovieAll/{movieId}/{movieName}/{movieGenre}",method=RequestMethod.GET)
	public String searchByMovieAll(@PathVariable String movieId,@PathVariable String movieName,@PathVariable String movieGenre,ModelMap model){
		MovieRequestDTO req = new MovieRequestDTO();
		req.setMovieID(Integer.parseInt(movieId));
		req.setMovieName(movieName);
		req.setMovieGenre(movieGenre);
		ArrayList<MovieResponseDTO> list = movieDAO.searchByNonDeletedAllMovie(req);
		model.addAttribute("movielist", list);
		ArrayList<MovieResponseDTO> deletedmovie =  movieDAO.selectByAllDeletedMovie(req);
		model.addAttribute("deletedmovielist",deletedmovie);
		
		return "searchedUserTable";
	}
}
