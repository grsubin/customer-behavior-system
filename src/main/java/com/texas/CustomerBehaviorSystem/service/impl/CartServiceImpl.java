package com.texas.CustomerBehaviorSystem.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.config.SwaggerUrlFilter;
import com.texas.CustomerBehaviorSystem.dao.CartDAO;
import com.texas.CustomerBehaviorSystem.dao.CartItemDAO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.CartItem;
import com.texas.CustomerBehaviorSystem.service.CartService;
import com.texas.CustomerBehaviorSystem.service.TransactionService;

@Service
public class CartServiceImpl implements CartService{

	private static final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public Cart findById(Long id) {

		return cartDAO.findById(id).get();
	}

	@Override
	public Cart saveCart(Cart cart) {
		Cart cartSave = cartDAO.save(cart);
		return cartSave;
	}

	@Override
	public void emptyCart(Cart cart) {
		for(CartItem cartItem : cart.getCartItems()){
			cartItemDAO.delete(cartItem);
		}
		cart.getCartItems().clear();
		cart.setAmount(0);
		cartDAO.save(cart);
	}
	

	@Override
	public Cart update(Cart cart) {
		
		double amount=0;
//		System.out.println(cart.getCartItems().size()+cart.getCartItems().toString());
	for(CartItem cartItem : cart.getCartItems())
			amount = amount + cartItem.getTotalPrice();
		 
        Double truncatedDouble = new BigDecimal(amount)
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        cart.setAmount(truncatedDouble);

        Cart cartSave = cartDAO.save(cart);		
        return cartSave;
	}

}
