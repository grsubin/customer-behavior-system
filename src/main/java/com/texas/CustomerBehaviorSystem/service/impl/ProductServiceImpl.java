package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CategoryDAO;
import com.texas.CustomerBehaviorSystem.dao.ProductDAO;
import com.texas.CustomerBehaviorSystem.dto.ProductDTO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.ProductService;

import io.jsonwebtoken.RequiredTypeException;
import javassist.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	public Product setProductdtoToProduct(Product newProduct, ProductDTO product) {
		newProduct.setName(product.getName());
		newProduct.setDescription(product.getDescription());
		newProduct.setStock(product.getStock());
		newProduct.setPrice(product.getPrice());
		newProduct.setCategory(categoryDAO.findByName(product.getCategoryName()));
		
		return newProduct;
	}
	@Override
	public Product save(ProductDTO product) {
		Product newProduct = new Product();
		newProduct.setName(product.getName());
		newProduct.setDescription(product.getDescription());
		newProduct.setStock(product.getStock());
		newProduct.setPrice(product.getPrice());
		newProduct.setCategory(categoryDAO.findByName(product.getCategoryName()));
		
		return productDAO.save(newProduct);
	}

	@Override
	public List<Product> findAll() {

		List<Product> list = new ArrayList<>();
		productDAO.findAll().iterator().forEachRemaining(list::add);
		
		return list;
	}

	@Override
	public void delete(Long id) {

		productDAO.deleteById(id);
	}

	@Override
	public Product findOne(String name) {

		return productDAO.findByName(name);
	}

	@Override
	public Product findById(Long id) {

		return productDAO.findById(id).get();
	}

	@Override
	public List<Product> getProductsByCategory(Category category) {

		
		return productDAO.findByCategory(category.getName());
	}

	@Override
	public Product updateProduct(Long id, ProductDTO productUpdate) {

		if(id == null)
			throw new RequiredTypeException("Product id is needed");
		Product product = productDAO.findById(id).get();
		if(productUpdate == null)
			try {
				throw new NotFoundException("Product not found");
				} catch (NotFoundException e) {
				e.printStackTrace();
			}
		
		if(productUpdate.getName() != null)
			product.setName(productUpdate.getName());
		if(productUpdate.getDescription() != null)
			product.setDescription(productUpdate.getDescription());
		if(productUpdate.getStock() != null)
			product.setStock(productUpdate.getStock());			
		if(productUpdate.getPrice() != 0)
			product.setPrice(productUpdate.getPrice());
		if(productUpdate.getStock() != null)
			product.setStock(productUpdate.getStock());	
		if(productUpdate.getCategoryName() != null)
			product.getCategory().setName(productUpdate.getCategoryName());			
		
		
		
		Product productSave = productDAO.save(product);
		return productSave;
	}

	//
//	@Override
//	public Optional<Product> findById(Long id) {
//		Optional<Product> product = productDAO.findById(id);
//		return product;
//	}
//	
//	
//
//	@Override
//	public Product findByName(String name) {
//		Product product = productDAO.findByName(name);
//		return product;
//	}
//
//	@Override
//	public List<Product> findAll() {
//		List<Product> products = productDAO.findAll();
//		return products;
//	}
//
//	@Override
//	public void save(Product product) {
////		if(categoryDAO.findByName(product.getCategory().getName()) != null)
//		product.setCategory(categoryDAO.findByName(product.getCategory().getName()));
//		System.out.println(product.getCategory().getName());
//		productDAO.save(product);
//	}
//
//	@Override
//	public void delete(Long id) {
//		productDAO.deleteById(id);		
//	}
//
//
//
//	@Override
//	public List<Product> getProductsByCategory(Category category) {
//		return productDAO.findByCategory(category);
//	}





}