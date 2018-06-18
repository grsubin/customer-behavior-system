package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Transaction transaction){
		transactionService.save(transaction);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/transaction_list", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAll(){
		List<Transaction> transactionList = transactionService.findAll();
		if(transactionList == null || transactionList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactionList,HttpStatus.FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> findByUsername(@RequestParam String username){
		List<Transaction> transactionList = transactionService.findTransactionByUserUsername(username);
		if(transactionList == null || transactionList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactionList, HttpStatus.FOUND);
		
	}

}
