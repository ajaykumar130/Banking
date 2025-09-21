package com.example.demo.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerServ;

@RestController
@RequestMapping("/accounts")
public class CustomerContr {
	
	@Autowired
	CustomerServ cs;
	
	@PostMapping
	  public Customer createAccount(@RequestBody Customer customer) {
	    return cs.createCustomer(customer);
	  }

	  @GetMapping("/{id}")
	  public Optional<Customer> getAccount(@PathVariable Long id) {
	    return cs.getCustomer(id);
	  }

	  @PutMapping("/{id}")
	  public Customer updateAccount(@PathVariable Long id, @RequestBody Customer customer) {
	    return cs.updateCustomer(id, customer);
	  }

	  @DeleteMapping("/{id}")
	  public String closeAccount(@PathVariable Long id) {
	    boolean deleted = cs.deleteCustomer(id);
	    return deleted ? "Account closed successfully" : "Account not found";
	  }

	  @GetMapping("/{id}/balance")
	  public Double getBalance(@PathVariable Long id) {
	    return cs.getBalance(id);
	  }
	
}
