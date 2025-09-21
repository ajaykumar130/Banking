package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bank;
import com.example.demo.entity.Customer;
import com.example.demo.repository.BankRepo;
import com.example.demo.repository.CustomerRepo;

@Service
public class BankServ {
	
	@Autowired
	BankRepo br;
	@Autowired
	CustomerRepo cr;
	
	public Bank deposit(Long accountId, Double amount) {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        Optional<Customer> optionalCustomer = cr.findById(accountId);
        if (optionalCustomer.isEmpty() || "Closed".equalsIgnoreCase(optionalCustomer.get().getStatus())) {
            throw new IllegalArgumentException("Account not found or is closed.");
        }
        Customer customer = optionalCustomer.get();
        customer.setBalance(customer.getBalance() + amount);
        customer.setLocalTime(LocalDateTime.now());
        cr.save(customer);

        Bank bank = new Bank();
        bank.setAccountId(accountId);
        bank.setType("deposit");
        bank.setAmount(amount);
        bank.setStatus("completed");
        bank.setDestinationAccountId(null);
        bank.setLocalTime(LocalDateTime.now());
        return br.save(bank);
    }

    public Bank withdraw(Long accountId, Double amount) {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        Optional<Customer> optionalCustomer = cr.findById(accountId);
        if (optionalCustomer.isEmpty() || "Closed".equalsIgnoreCase(optionalCustomer.get().getStatus())) {
            throw new IllegalArgumentException("Account not found or is closed.");
        }
        Customer customer = optionalCustomer.get();
        if (customer.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        customer.setBalance(customer.getBalance() - amount);
        customer.setLocalTime(LocalDateTime.now());
        cr.save(customer);

        Bank bank = new Bank();
        bank.setAccountId(accountId);
        bank.setType("withdraw");
        bank.setAmount(amount);
        bank.setStatus("completed");
        bank.setDestinationAccountId(null);
        bank.setLocalTime(LocalDateTime.now());
        return br.save(bank);
    }

    public Bank transfer(Long sourceAccountId, Long destAccountId, Double amount) {
        if (sourceAccountId == null || sourceAccountId <= 0 || destAccountId == null || destAccountId <= 0) {
            throw new IllegalArgumentException("Invalid account IDs.");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (sourceAccountId.equals(destAccountId)) {
            throw new IllegalArgumentException("Source and destination accounts cannot be the same.");
        }
        Optional<Customer> optionalSource = cr.findById(sourceAccountId);
        Optional<Customer> optionalDest = cr.findById(destAccountId);
        if (optionalSource.isEmpty() || "Closed".equalsIgnoreCase(optionalSource.get().getStatus())) {
            throw new IllegalArgumentException("Source account not found or is closed.");
        }
        if (optionalDest.isEmpty() || "Closed".equalsIgnoreCase(optionalDest.get().getStatus())) {
            throw new IllegalArgumentException("Destination account not found or is closed.");
        }
        Customer source = optionalSource.get();
        Customer dest = optionalDest.get();
        if (source.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in source account.");
        }
        source.setBalance(source.getBalance() - amount);
        source.setLocalTime(LocalDateTime.now());
        dest.setBalance(dest.getBalance() + amount);
        dest.setLocalTime(LocalDateTime.now());
        cr.save(source);
        cr.save(dest);

        Bank bank = new Bank();
        bank.setAccountId(sourceAccountId);
        bank.setDestinationAccountId(destAccountId);
        bank.setType("transfer");
        bank.setAmount(amount);
        bank.setStatus("completed");
        bank.setLocalTime(LocalDateTime.now());
        return br.save(bank);
    }

    public List<Bank> getTransactionsByAccount(Long accountId) {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("Invalid account ID.");
        }
        Optional<Customer> optionalCustomer = cr.findById(accountId);
        if (optionalCustomer.isEmpty() || "Closed".equalsIgnoreCase(optionalCustomer.get().getStatus())) {
            throw new IllegalArgumentException("Account not found or is closed.");
        }
        return br.findByAccountId(accountId);
    }

    public Optional<Bank> getTransactionStatus(Long txnId) {
        if (txnId == null || txnId <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID.");
        }
        Optional<Bank> optionalBank = br.findById(txnId);
        if (optionalBank.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found.");
        }
        return optionalBank;
    }
}
