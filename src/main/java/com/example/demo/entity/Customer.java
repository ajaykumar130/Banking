package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String email;
	String phone;
	String accountType;
	Double balance;
	String status;
    LocalDateTime localTime;

	public Customer() {
	}
	
	public Customer(Long id, String name, String email, String phone, String accountType, Double balance,String status,LocalDateTime localTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
        this.localTime = localTime;

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getLocalTime() { return localTime; }
    public void setLocalTime(LocalDateTime localTime) { this.localTime = localTime; }
	
}
