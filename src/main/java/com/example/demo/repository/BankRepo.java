package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bank;
@Repository
public interface BankRepo extends CrudRepository<Bank, Long>{
	List<Bank> findByAccountId(Long accountId);

}
