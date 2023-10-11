package com.cinemamanage.model;

import javax.validation.constraints.NotEmpty;

public class AccountBean {
  
  
  private String accountID;
  @NotEmpty
  private String accountName;
  @NotEmpty 
  private String accountEmail;
  @NotEmpty 
  private String accountPassword;
  
  private String accountRole;
  @NotEmpty 
  private String confirmPassword;
  
  
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
  public String getAccountEmail() {
    return accountEmail;
  }
  public void setAccountEmail(String accountEmail) {
    this.accountEmail = accountEmail;
  }
  public String getAccountPassword() {
    return  accountPassword;
  }
  public void setAccountPassword(String accountPassowrd) {
    this.accountPassword = accountPassowrd;
  }
  public String getAccountRole() {
    return accountRole;
  }
  public void setAccountRole(String accountRole) {
    this.accountRole = accountRole;
  }
  public String getAccountID() {
    return accountID;
  }
  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }
  public String getConfirmPassword() {
    return confirmPassword;
  }
  public void setConfirmPassword(String confirmPassowrd) {
    this.confirmPassword = confirmPassowrd;
  }
  
  
}