package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepo;

@Service
public class CustomerServ {
	
	@Autowired
	CustomerRepo cr;
	
	void validatePhoneNumber(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits and contain only numbers.");
        }
    }

    void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email must contain '@' and a valid domain extension.");
        }
    }

    public Customer createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        validateEmail(customer.getEmail());
        if (customer.getPhone() == null || customer.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone is required.");
        }
        validatePhoneNumber(customer.getPhone());

        if (customer.getBalance() == null) {
            throw new IllegalArgumentException("Initial balance must be initialized.");
        }
        if (customer.getBalance().isNaN() || customer.getBalance().isInfinite()) {
            throw new IllegalArgumentException("Balance must be a valid number.");
        }
        if (customer.getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        if (customer.getStatus() == null || !"Active".equalsIgnoreCase(customer.getStatus())) {
            throw new IllegalArgumentException("Status should be active while creating account.");
        }
        customer.setStatus("Active");
        customer.setLocalTime(LocalDateTime.now());

        List<Customer> existing = ((Collection<Customer>) cr.findAll()).stream()
            .filter(c -> c.getEmail().equals(customer.getEmail()) || c.getPhone().equals(customer.getPhone()))
            .toList();

        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("Account details already exist.");
        }

        return cr.save(customer);
    }

    public Optional<Customer> getCustomer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        Optional<Customer> optionalCustomer = cr.findById(id);
        if (optionalCustomer.isEmpty() || "Closed".equalsIgnoreCase(optionalCustomer.get().getStatus())) {
            throw new IllegalArgumentException("Account not found or is closed.");
        }
        return optionalCustomer;
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        Optional<Customer> optionalCustomer = cr.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        Customer customer = optionalCustomer.get();
        if ("Closed".equalsIgnoreCase(customer.getStatus())) {
            throw new IllegalArgumentException("Cannot update a closed account.");
        }
        if (updatedCustomer.getBalance() != null && !updatedCustomer.getBalance().equals(customer.getBalance())) {
            throw new IllegalArgumentException("Balance cannot be updated via this endpoint.");
        }
        if (updatedCustomer.getStatus() != null && !updatedCustomer.getStatus().equalsIgnoreCase(customer.getStatus())) {
            throw new IllegalArgumentException("Status cannot be updated via this endpoint.");
        }
        if (updatedCustomer.getName() == null || updatedCustomer.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        validateEmail(updatedCustomer.getEmail());
        if (updatedCustomer.getPhone() == null || updatedCustomer.getPhone().isBlank()) {
            throw new IllegalArgumentException("Phone cannot be empty.");
        }
        validatePhoneNumber(updatedCustomer.getPhone());

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAccountType(updatedCustomer.getAccountType());
        customer.setLocalTime(LocalDateTime.now());

        return cr.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        Optional<Customer> optionalCustomer = cr.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new IllegalArgumentException("Account not found.");
        }
        Customer customer = optionalCustomer.get();
        if ("Closed".equalsIgnoreCase(customer.getStatus())) {
            throw new IllegalArgumentException("Account already deleted.");
        }
        customer.setStatus("Closed");
        customer.setLocalTime(LocalDateTime.now());
        cr.save(customer);
        return true;
    }

    public Double getBalance(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        Optional<Customer> optionalCustomer = cr.findById(id);
        if (optionalCustomer.isEmpty() || "Closed".equalsIgnoreCase(optionalCustomer.get().getStatus())) {
            throw new IllegalArgumentException("Account not found or is closed.");
        }
        return optionalCustomer.get().getBalance();
    }

    public void updateBalance(Long id, Double newBalance) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        if (newBalance == null) {
            throw new IllegalArgumentException("Balance must be initialized.");
        }
        if (newBalance.isNaN() || newBalance.isInfinite()) {
            throw new IllegalArgumentException("Balance must be a valid number.");
        }
        if (newBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        Optional<Customer> optionalCustomer = cr.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setBalance(newBalance);
            customer.setLocalTime(LocalDateTime.now());
            cr.save(customer);
        }
    }
}
