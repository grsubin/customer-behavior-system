package com.texas.CustomerBehaviorSystem.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.Transaction;

public interface TransactionService {

	void save(Transaction transaction);
	Optional<Transaction> findById(Long id);
	List<Transaction> findAll();
	List<Transaction> findTransactionByUserUsername(String username);
	double getAmountByCart(Cart cart);
	void addTransactionDumpCart(Transaction transaction, Cart cart) throws IOException;
}
