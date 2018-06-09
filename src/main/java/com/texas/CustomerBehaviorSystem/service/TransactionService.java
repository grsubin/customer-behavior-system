package com.texas.CustomerBehaviorSystem.service;

import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.Transaction;

public interface TransactionService {

	void save(Transaction transaction);
	Optional<Transaction> findById(Long id);
	List<Transaction> findAll();
	List<Transaction> findTransactionByUserUsername(String username);
	
}
