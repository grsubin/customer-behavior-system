package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.TransactionDAO;
import com.texas.CustomerBehaviorSystem.dao.UserDAO;
import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	@Override
	public Optional<Transaction> findById(Long id) {
		Optional<Transaction> transaction = transactionDAO.findById(id);
		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		List<Transaction> transactions = transactionDAO.findAll();
		return transactions;
	}

	@Override
	public void save(Transaction transaction) {
		transactionDAO.save(transaction);
	}

	@Override
	public List<Transaction> findTransactionByUserUsername(String username) {
		User user = null;
		user = userDAO.findByUsernameOrPhoneNumberOrEmail(username, null, null);
		List<Transaction> transactions = null;
		transactions = transactionDAO.findByUser(user);
		return transactions;
	}
	
	
	

}
