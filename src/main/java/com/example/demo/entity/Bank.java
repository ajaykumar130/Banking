package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bank {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  Long id;
	  Long accountId;
	  String type; 
	  Double amount;
	  String status; 
	  Long destinationAccountId;
	  LocalDateTime localTime;

	  public Bank() {}
	  public Bank(Long id, Long accountId, String type, Double amount, String status, Long destinationAccountId,LocalDateTime localTime) {
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.destinationAccountId = destinationAccountId;
		this.localTime = localTime;
	  }
	  public Long getId() {
		  return id;
	  }
	  public void setId(Long id) {
		  this.id = id;
	  }
	  public Long getAccountId() {
		  return accountId;
	  }
	  public void setAccountId(Long accountId) {
		  this.accountId = accountId;
	  }
	  public String getType() {
		  return type;
	  }
	  public void setType(String type) {
		  this.type = type;
	  }
	  public Double getAmount() {
		  return amount;
	  }
	  public void setAmount(Double amount) {
		  this.amount = amount;
	  }
	  public String getStatus() {
		  return status;
	  }
	  public void setStatus(String status) {
		  this.status = status;
	  }
	  public Long getDestinationAccountId() {
		  return destinationAccountId;
	  }
	  public void setDestinationAccountId(Long destinationAccountId) {
		  this.destinationAccountId = destinationAccountId;
	  }
	  public LocalDateTime getLocalTime() { return localTime; }
	    public void setLocalTime(LocalDateTime localTime) { this.localTime = localTime; }
	  
	  

}
