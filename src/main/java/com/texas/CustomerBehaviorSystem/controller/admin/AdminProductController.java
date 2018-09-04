package com.texas.CustomerBehaviorSystem.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.dto.ProductDTO;
import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.service.CategoryService;
import com.texas.CustomerBehaviorSystem.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/admin/products")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	

	@RequestMapping(method = RequestMethod.POST)
	public 	ResponseEntity<?> save(@RequestBody ProductDTO product){
		if(categoryService.findByName(product.getCategoryName()) == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		productService.save(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Product product = productService.findById(id);
		if(product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		productService.delete(id);
		if(product == null)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productUpdate){
	
		if(productService.findById(id) == null	)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Product product = productService.updateProduct(id, productUpdate);
		return new ResponseEntity<>(product, HttpStatus.OK);
			
	}
	
}
