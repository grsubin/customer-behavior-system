package com.texas.CustomerBehaviorSystem.service;

import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.dto.ProductDTO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.Product;

public interface ProductService {

	Product save(ProductDTO product);
	List<Product> findAll();
	void delete(Long id);
	Product findOne(String name);
	Product findById(Long id);
	Product updateProduct(Long id, ProductDTO productUpdate);
	List<Product> getProductsByCategory(Category category);
}
