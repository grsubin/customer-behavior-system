package com.texas.CustomerBehaviorSystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.dto.CategoryDTO;
import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.CategoryService;

@RestController
@RequestMapping("api/v1/admin/categories")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll(@RequestHeader String authorization){
		List<Category> categories = categoryService.findAll();
		if(categories == null || categories.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(categories, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<Category> findByCategoryName(@RequestHeader String authorization,@PathVariable("categoryName") String categoryName){

		Category category = categoryService.findByName(categoryName);
		if(category == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(category, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/{username}",method = RequestMethod.PUT)
	public ResponseEntity<Category> updateUserByCategoryName(@RequestHeader String authorization,@RequestBody CategoryDTO categoryUpdate, @PathVariable String categoryName){

		if(categoryService.findByName(categoryName)== null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Category category = categoryService.updateCategory(categoryName, categoryUpdate);
		return new ResponseEntity<>(category, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save (@RequestHeader String authorization, @RequestBody CategoryDTO categoryDTO){
		categoryService.save(categoryDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
