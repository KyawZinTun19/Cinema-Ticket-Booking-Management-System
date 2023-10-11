package com.cinemamanage.model;

public class SeatHasTimeTableBean {
	private int ID;
	private int seatID;
	private int timetableID;
	private int paymentID;
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
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}

