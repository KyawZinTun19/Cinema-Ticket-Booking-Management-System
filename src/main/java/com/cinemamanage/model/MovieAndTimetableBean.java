package com.cinemamanage.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MovieAndTimetableBean {
	private String movieID;
	@NotEmpty(message="")
	private String movieName;
	private LocalDate  movieCreateTime;
	private LocalDate  movieUpdateTime;
	private LocalDate  movieDeleteTime;
	@NotEmpty(message="")
	private String movieDuration;
	@NotEmpty(message="")
	private String movieDescription;
	@NotEmpty(message="")
	private String movieGenre;
	
	private String createdAdmin;
	private String updatedAdmin;
	private String deletedAdmin;
	
	private CommonsMultipartFile moviePoster;
	
	public String getCreatedAdmin() {
		return createdAdmin;
	}
	public void setCreatedAdmin(String createdAdmin) {
		this.createdAdmin = createdAdmin;
	}
	public String getUpdatedAdmin() {
		return updatedAdmin;
	}
	public void setUpdatedAdmin(String updatedAdmin) {
		this.updatedAdmin = updatedAdmin;
	}
	public String getDeletedAdmin() {
		return deletedAdmin;
	}
	public void setDeletedAdmin(String deletedAdmin) {
		this.deletedAdmin = deletedAdmin;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public LocalDate  getMovieCreateTime() {
		return movieCreateTime;
	}
	public void setMovieCreateTime(LocalDate  movieCreateTime) {
		this.movieCreateTime = movieCreateTime;
	}
	public LocalDate  getMovieUpdateTime() {
		return movieUpdateTime;
	}
	public void setMovieUpdateTime(LocalDate  movieUpdateTime) {
		this.movieUpdateTime = movieUpdateTime;
	}
	public LocalDate  getMovieDeleteTime() {
		return movieDeleteTime;
	}
	public void setMovieDeleteTime(LocalDate  movieDeleteTime) {
		this.movieDeleteTime = movieDeleteTime;
	}
	public String getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(String movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	
	private int timetableMovieId;
	@NotEmpty
	private String startDate;

	private String endDate;
	@NotEmpty
	private String startTime;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getTimetableMovieId() {
		return timetableMovieId;
	}
	public void setTimetableMovieId(int timetableMovieId) {
		this.timetableMovieId = timetableMovieId;
	}
	public CommonsMultipartFile getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(CommonsMultipartFile moviePoster) {
		this.moviePoster = moviePoster;
	}
}
