package com.cinemamanage.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MovieBean {
	
	private String movieID;
	@NotEmpty
	private String movieName;
	
	private LocalDateTime movieCreatTime;
	@NotEmpty
	private String movieDuration;
	@NotEmpty
	private String movieDescription;
	private LocalDateTime movieUpdateTime;
	private LocalDateTime movieDeleteTime;
	@NotEmpty
	private String movieGenre;
	
	private CommonsMultipartFile image;
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public LocalDateTime getMovieCreatTime() {
		return movieCreatTime;
	}
	public void setMovieCreatTime(LocalDateTime movieCreatTime) {
		this.movieCreatTime = movieCreatTime;
	}
	 
	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	public LocalDateTime getMovieUpdateTime() {
		return movieUpdateTime;
	}
	public void setMovieUpdateTime(LocalDateTime movieUpdateTime) {
		this.movieUpdateTime = movieUpdateTime;
	}
	public LocalDateTime getMovieDeleteTime() {
		return movieDeleteTime;
	}
	public void setMovieDeleteTime(LocalDateTime movieDeleteTime) {
		this.movieDeleteTime = movieDeleteTime;
	}
	 
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(String movieDuration) {
		this.movieDuration = movieDuration;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
}
