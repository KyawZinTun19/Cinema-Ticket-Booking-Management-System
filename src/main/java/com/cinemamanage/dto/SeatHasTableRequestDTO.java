package com.cinemamanage.dto;

public class SeatHasTableRequestDTO {
	private int id;
	private int seatID;
	private int timetableID;
	private int paymentID;
	private String seatName;
	private String Status;
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public int getTimetableID() {
		return timetableID;
	}
	public void setTimetableID(int timetableID) {
		this.timetableID = timetableID;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
}

