package com.cinemamanage.dto;



public class TimeTableResponseDTO {
	private int timeTableID;
	private String start_date;
	private String end_date;
	private String start_time;
	private MovieRequestDTO movie;
	private int movie_id;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public int getMovieID() {
		return movie.getMovieID();
	}
//	public void setMovieID(int movieID) {
//		this.movieID = movieID;
//	}
	public int getTimeTableID() {
		return timeTableID;
	}
	public void setTimeTableID(int timeTableID) {
		this.timeTableID = timeTableID;
	}
	public MovieRequestDTO getMovie() {
		return movie;
	}
	public void setMovie(MovieRequestDTO movie) {
		this.movie = movie;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
}
