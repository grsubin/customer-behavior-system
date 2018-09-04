package com.texas.CustomerBehaviorSystem.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CartDAO;
import com.texas.CustomerBehaviorSystem.dao.CartItemDAO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.CartItem;
import com.texas.CustomerBehaviorSystem.service.CartService;
import com.texas.CustomerBehaviorSystem.service.TransactionService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public Optional<Cart> findById(Long id) {

		return cartDAO.findById(id);
	}

	@Override
	public void saveCart(Cart cart) {

		 cartDAO.save(cart);
	}

	@Override
	public void emptyCart(Cart cart) {
		for(CartItem cartItem : cart.getCartItems()){
			cartItemDAO.delete(cartItem);
		}
		cart.setAmount(0);
		cartDAO.save(cart);
	}
	

	@Override
	public void update(Cart cart) {
        double amount = transactionService.getAmountByCart(cart);
        Double truncatedDouble = new BigDecimal(amount)
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        cart.setAmount(truncatedDouble);

        cartDAO.save(cart);		
	}

}
