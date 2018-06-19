package com.texas.CustomerBehaviorSystem.service;

import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.Product;

public interface ProductService {
	
	Optional<Product> findById(Long id);
	Product findByName(String name);
	List<Product> findAll();
	void save(Product product);
	void delete(Long id);
}
