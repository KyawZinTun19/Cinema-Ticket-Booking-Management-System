package com.cinemamanage.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

public class MovieAndTimetableResponseDTO {
	private int movieID;
	private String movieName;
	private String  movieCreateTime;
	private String  movieUpdateTime;
	private String  movieDeleteTime;
	private String movieDuration;
	private String movieDescription;
	private String movieGenre;
	
	private String createdAdmin;
	private String updatedAdmin;
	private String deletedAdmin;
	private String moviePoster;
	
	
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
	public String  getMovieCreateTime() {
		return movieCreateTime;
	}
	public void setMovieCreateTime(String  movieCreateTime) {
		this.movieCreateTime = movieCreateTime;
	}
	public String  getMovieUpdateTime() {
		return movieUpdateTime;
	}
	public void setMovieUpdateTime(String  movieUpdateTime) {
		this.movieUpdateTime = movieUpdateTime;
	}
	public String  getMovieDeleteTime() {
		return movieDeleteTime;
	}
	public void setMovieDeleteTime(String  movieDeleteTime) {
		this.movieDeleteTime = movieDeleteTime;
	}
	public String getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(String movieDuration) {
		this.movieDuration = movieDuration;
	}

	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
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
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

}
