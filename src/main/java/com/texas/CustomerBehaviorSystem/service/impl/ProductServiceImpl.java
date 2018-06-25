package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CategoryDAO;
import com.texas.CustomerBehaviorSystem.dao.ProductDAO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public Optional<Product> findById(Long id) {
		Optional<Product> product = productDAO.findById(id);
		return product;
	}
	
	

	@Override
	public Product findByName(String name) {
		Product product = productDAO.findByName(name);
		return product;
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = productDAO.findAll();
		return products;
	}

	@Override
	public void save(Product product) {
//		if(categoryDAO.findByName(product.getCategory().getName()) != null)
		product.setCategory(categoryDAO.findByName(product.getCategory().getName()));
		System.out.println(product.getCategory().getName());
		productDAO.save(product);
	}

	@Override
	public void delete(Long id) {
		productDAO.deleteById(id);		
	}



	@Override
	public List<Product> getProductsByCategory(Category category) {
		return productDAO.findByCategory(category);
	}





}