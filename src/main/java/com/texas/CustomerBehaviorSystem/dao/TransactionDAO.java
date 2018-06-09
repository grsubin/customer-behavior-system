package com.texas.CustomerBehaviorSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.Transaction;
import com.texas.CustomerBehaviorSystem.model.User;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long> {

	Optional<Transaction> findById(Long id);
	List<Transaction> findByUser(User user);
	
}
