package com.texas.CustomerBehaviorSystem.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CartDAO;
import com.texas.CustomerBehaviorSystem.dao.CartItemDAO;
import com.texas.CustomerBehaviorSystem.dao.ProductDAO;
import com.texas.CustomerBehaviorSystem.dao.TransactionDAO;
import com.texas.CustomerBehaviorSystem.dao.TransactionItemDAO;
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
	TransactionItemDAO transactionItemDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemDAO cartItemDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public Transaction findById(Long id) {
		Transaction transaction = transactionDAO.findById(id).get();
		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		List<Transaction> transactions = transactionDAO.findAll();
		return transactions;
	}

	@Override
	public Transaction save(Transaction transaction) {
		
		Transaction transactionSave = transactionDAO.save(transaction);
		return transactionSave;
	}

	@Override
	public List<Transaction> findTransactionByUserUsername(String username) {
		User user = null;
		user = userDAO.findByUsername(username);
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
	public Transaction addTransactionDumpCart(Cart cart) throws IOException{

		if(cart == null){
			throw new IOException();
		}
		Transaction transaction = new Transaction();
		transaction.setUser(cart.getUser());	
		transaction.setAmount(cart.getAmount());
		
		cart.setAmount(0);
		cartService.saveCart(cart);
		// dump cartItem to orderItem, empty cart
		for(CartItem cartItem : cart.getCartItems()){
			TransactionItem transactionItem = new TransactionItem();

			transactionItem.setTransaction(transaction);
			transactionItem.setProductId(cartItem.getProduct().getId());
			transactionItem.setPrice(cartItem.getProduct().getPrice());
			transactionItem.setProductName(cartItem.getProduct().getName());
			transactionItem.setQuantity(cartItem.getQuantity());
			transactionItem.setTotalPrice(cartItem.getTotalPrice());
			transactionItemDAO.save(transactionItem);
			cartItem.getProduct().setStock(cartItem.getProduct().getStock()-cartItem.getQuantity());
			productDAO.save(cartItem.getProduct());
			cartItemDAO.delete(cartItem);
		}
		
		Transaction transactionSave = transactionDAO.save(transaction);
		
		return transactionSave;
	}
	
	
	

}
