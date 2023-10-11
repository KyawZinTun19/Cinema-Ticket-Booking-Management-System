package com.cinemamanage.dto;

public class UserMovieDetailResponseDTO {
private String movieName;
private String startDate;
private String endDate;
private String startTime;
private int timetableID;
public String getMovieName() {
	return movieName;
}
public void setMovieName(String movieName) {
	this.movieName = movieName;
}
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
public int getTimetableID() {
	return timetableID;
}
public void setTimetableID(int timetableID) {
	this.timetableID = timetableID;
}
	
}
