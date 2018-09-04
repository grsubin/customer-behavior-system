package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.dto.CategoryDTO;
import com.texas.CustomerBehaviorSystem.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save (@RequestBody CategoryDTO categoryDTO){
		categoryService.save(categoryDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<Object>> findAll(){
//		
//	}
//	
//	public ResponseEntity<List<Object>> findByCategory(){
//		
//	}
}
