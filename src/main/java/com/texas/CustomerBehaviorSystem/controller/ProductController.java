package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;
import java.util.Optional;

import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.service.CategoryService;
import com.texas.CustomerBehaviorSystem.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	

	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product product = productService.findById(id);
		if(product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(product, HttpStatus.FOUND);
	}
	
//	@RequestMapping(value="/{name}", method = RequestMethod.GET)
//	public ResponseEntity<Product> findByName (@PathVariable String name){
//		
//		Product product = productService.findByName(name);
//		if(product == null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(product,HttpStatus.FOUND);
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = productService.findAll();
		if(products == null || products.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/c",method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findByCategory(@RequestParam(value = "category",required =false) String categoryName){
		Category category = categoryService.findByName(categoryName);
		List<Product> products = productService.getProductsByCategory(category);
		if(products == null || products.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	

	

}
