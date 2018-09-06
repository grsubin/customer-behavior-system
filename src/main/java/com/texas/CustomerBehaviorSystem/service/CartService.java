package com.texas.CustomerBehaviorSystem.service;

import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.Cart;

public interface CartService {

	Cart findById(Long id);
	Cart saveCart(Cart cart);
	void emptyCart(Cart cart);
	Cart update(Cart cart);
	
	
}
