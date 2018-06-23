package com.texas.CustomerBehaviorSystem.service;

import com.texas.CustomerBehaviorSystem.model.Cart;

public interface CartService {

	Cart findById(Long id);
	void saveCart(Cart cart);
	void emptyCart(Cart cart);
	void update(Cart cart);
	
	
}
