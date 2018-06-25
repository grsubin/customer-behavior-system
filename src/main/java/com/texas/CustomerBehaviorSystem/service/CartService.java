package com.texas.CustomerBehaviorSystem.service;

import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.Cart;

public interface CartService {

	Optional<Cart> findById(Long id);
	void saveCart(Cart cart);
	void emptyCart(Cart cart);
	void update(Cart cart);
	
	
}
