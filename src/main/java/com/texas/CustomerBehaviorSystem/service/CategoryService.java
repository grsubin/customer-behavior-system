package com.texas.CustomerBehaviorSystem.service;

import java.util.List;

import com.texas.CustomerBehaviorSystem.dto.CategoryDTO;
import com.texas.CustomerBehaviorSystem.model.Category;

public interface CategoryService {
	
	List<Category> findAll();
	Category findByName(String categoryName);
	void save(CategoryDTO categoryDTO);

}
