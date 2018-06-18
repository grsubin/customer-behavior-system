package com.texas.CustomerBehaviorSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long>{

	
}
