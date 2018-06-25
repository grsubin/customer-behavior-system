package com.texas.CustomerBehaviorSystem.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CartDAO;
import com.texas.CustomerBehaviorSystem.dao.CartItemDAO;
import com.texas.CustomerBehaviorSystem.dao.TransactionDAO;
import com.texas.CustomerBehaviorSystem.dao.UserDAO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.CartItem;
import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.model.TransactionItem;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.CartService;
import com.texas.CustomerBehaviorSystem.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemDAO cartItemDAO;
	
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
	
	public double getAmountByCart(Cart cart) {
        double amount = 0;
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            amount += item.getTotalPrice();
        }
        return amount;
	}

	@Override
	public void addTransactionDumpCart(Transaction transaction, Cart cart) throws IOException{

		if(transaction == null || cart == null){
			throw new IOException();
		}
		transaction.setUser(cart.getUser());	
		transaction.setAmount(cart.getAmount());
		
		transactionDAO.save(transaction);
		cart.setAmount(0);
		cartService.saveCart(cart);
		// dump cartItem to orderItem, empty cart
		for(CartItem cartItem : cart.getCartItems()){
			TransactionItem transactionItem = new TransactionItem();

			transactionItem.setTransaction(transaction);
			transactionItem.setProduct(cartItem.getProduct());
			transactionItem.setQuantity(cartItem.getQuantity());
			transactionDAO.save(transaction);
			cartItemDAO.delete(cartItem);
		}
	}
	
	
	

}
