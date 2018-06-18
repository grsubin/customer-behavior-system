package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CategoryDAO;
import com.texas.CustomerBehaviorSystem.dto.CategoryDTO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> findAll() {
		List<Category> categories = categoryDAO.findAll();
		return categories;
	}


	@Override
	public void save(CategoryDTO categoryDTO) {
		
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(category.getDescription());
		category.setImageURL(categoryDTO.getImageURL());
		categoryDAO.save(category);
	}

}
