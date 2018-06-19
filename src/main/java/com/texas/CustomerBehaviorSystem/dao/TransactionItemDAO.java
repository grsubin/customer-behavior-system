package com.texas.CustomerBehaviorSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.TransactionItem;

@Repository
public interface TransactionItemDAO extends JpaRepository<TransactionItem, Long>{

}
