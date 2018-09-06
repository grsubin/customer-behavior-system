package com.texas.CustomerBehaviorSystem.controller.admin;

import static com.texas.CustomerBehaviorSystem.model.Constants.TOKEN_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.service.TransactionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/admin/transactions")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminTransactionController {

	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAll(@RequestHeader String authorization,@RequestParam(required = false) String username){
		
		if(username == null) {
			
			List<Transaction> transactionList = transactionService.findAll();
			if(transactionList == null || transactionList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(transactionList,HttpStatus.FOUND);
		}else {
			List<Transaction> transactionList = transactionService.findTransactionByUserUsername(username);
			if(transactionList == null || transactionList.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(transactionList,HttpStatus.FOUND);
		}
	}
	
//	@RequestMapping(value = "/u",method = RequestMethod.GET)
//	public ResponseEntity<List<Transaction>> getTransactionByUsername(@RequestHeader String authorization,@RequestParam(required = false) String username){
//		List<Transaction> transactionList = transactionService.findTransactionByUserUsername(username);
//		if(transactionList == null || transactionList.isEmpty()){
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(transactionList,HttpStatus.FOUND);
//	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transaction> getTransactionById(@RequestHeader String authorization, @PathVariable("id") Long transactionId){
		Transaction transaction = transactionService.findById(transactionId);
		if(transaction == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(transaction,HttpStatus.FOUND);
	}
	
}
