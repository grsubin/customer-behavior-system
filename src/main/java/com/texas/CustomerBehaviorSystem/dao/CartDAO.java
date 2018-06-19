package com.texas.CustomerBehaviorSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {

}
