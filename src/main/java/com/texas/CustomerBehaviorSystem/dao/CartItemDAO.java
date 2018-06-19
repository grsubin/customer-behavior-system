package com.texas.CustomerBehaviorSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.CartItem;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Long>{

}
