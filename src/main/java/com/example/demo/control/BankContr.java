package com.example.demo.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Bank;
import com.example.demo.service.BankServ;

@RestController
@RequestMapping("/transactions")
public class BankContr {
	
	@Autowired
	BankServ bs;
	
	@PostMapping("/deposit")
	  public Bank deposit(@RequestParam Long accountId, @RequestParam Double amount) {
	    return bs.deposit(accountId, amount);
	  }

	  @PostMapping("/withdraw")
	  public Bank withdraw(@RequestParam Long accountId, @RequestParam Double amount) {
	    return bs.withdraw(accountId, amount);
	  }

	  @PostMapping("/transfer")
	  public Bank transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) {
	    return bs.transfer(fromAccountId, toAccountId, amount);
	  }

	  @GetMapping("/{accountId}")
	  public List<Bank> getTransactions(@PathVariable Long accountId) {
	    return bs.getTransactionsByAccount(accountId);
	  }

	  @GetMapping("/{txnId}/status")
	  public Optional<Bank> getTransactionStatus(@PathVariable Long txnId) {
	    return bs.getTransactionStatus(txnId);
	  }

}
