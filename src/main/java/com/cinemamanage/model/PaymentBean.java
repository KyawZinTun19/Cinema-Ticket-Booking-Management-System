package com.cinemamanage.model;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

 
public class PaymentBean {
    @NotEmpty
	private String phone;
    
    private String amount;
    
	private MultipartFile transactionImage;
    @NotEmpty
	private String pay;
    
    public ArrayList<String> getSeatCNames() {
		return seatCNames;
	}
	public ArrayList<String> getSeatDNames() {
		return seatDNames;
	}
	public ArrayList<String> getSeatENames() {
		return seatENames;
	}
	public ArrayList<String> getSeatFNames() {
		return seatFNames;
	}
	private ArrayList<String> seatANames;
    private ArrayList<String> seatBNames; 
    private ArrayList<String> seatCNames;
    private ArrayList<String> seatDNames;
    private ArrayList<String> seatENames;
    private ArrayList<String> seatFNames;
	
	public void setSeatCNames(ArrayList<String> seatCNames) {
		this.seatCNames = seatCNames;
	}
	public void setSeatDNames(ArrayList<String> seatDNames) {
		this.seatDNames = seatDNames;
	}
	public void setSeatENames(ArrayList<String> seatENames) {
		this.seatENames = seatENames;
	}
	public void setSeatFNames(ArrayList<String> seatFNames) {
		this.seatFNames = seatFNames;
	}
	public ArrayList<String> getSeatANames() {
		return seatANames;
	}
	public void setSeatANames(ArrayList<String> seatANames) {
		this.seatANames = seatANames;
	}
	public ArrayList<String> getSeatBNames() {
		return seatBNames;
	}
	public void setSeatBNames(ArrayList<String> seatBNames) {
		this.seatBNames = seatBNames;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public MultipartFile getTransactionImage() {
		return transactionImage;
	}
	public void setTransactionImage(MultipartFile transactionImage) {
		this.transactionImage = transactionImage;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
//	public ArrayList<String> getSeatNames() {
//		return seatANames;
//	}
//	public void setSeatNames(ArrayList<String> seatANames) {
//		this.seatANames = seatANames;
//	}
	 	
	 
}
