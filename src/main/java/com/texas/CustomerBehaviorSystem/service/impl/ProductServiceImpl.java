package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.ProductDAO;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

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
		productDAO.save(product);
	}

}