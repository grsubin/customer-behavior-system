package com.texas.CustomerBehaviorSystem.controller;

import static com.texas.CustomerBehaviorSystem.model.Constants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.config.TokenProvider;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.service.CartService;
import com.texas.CustomerBehaviorSystem.service.TransactionService;
import com.texas.CustomerBehaviorSystem.service.UserService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/transaction")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	UserService userService;
	
	 @Autowired
	 private TokenProvider jwtTokenUtil;
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Transaction> save(@RequestHeader String authorization) throws IOException{
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		Cart cart = userService.findOne(tokenUsername).getCart();
		if(cart.getCartItems() == null || cart.getCartItems().isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Transaction transaction = transactionService.addTransactionDumpCart(cart);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getTransactionByUsername(@RequestHeader String authorization){
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		List<Transaction> transactionList = transactionService.findTransactionByUserUsername(tokenUsername);
		if(transactionList == null || transactionList.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactionList,HttpStatus.FOUND);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transaction> getTransactionById(@RequestHeader String authorization, @PathVariable("id") Long transactionId){
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		Transaction transaction = transactionService.findById(transactionId);
		if(transaction == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if(userService.findOne(tokenUsername).getTransactions().contains(transaction))
			return new ResponseEntity<>(transaction,HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	
	}
	

}
