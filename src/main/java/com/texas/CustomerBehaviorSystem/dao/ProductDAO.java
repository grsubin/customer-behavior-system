package com.texas.CustomerBehaviorSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{
	
	Optional<Product> findById(Long id);
	Product findByName(String name);
	List<Product> findByCategory(Category category);
	
}
