package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.CategoryDAO;
import com.texas.CustomerBehaviorSystem.dto.CategoryDTO;
import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.service.CategoryService;

import io.jsonwebtoken.RequiredTypeException;
import javassist.NotFoundException;

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


	@Override
	public Category findByName(String categoryName) {
		Category category = categoryDAO.findByName(categoryName);
		return category;
	}


	@Override
	public Category updateCategory(String categoryName, CategoryDTO categoryUpdate) {

		if(categoryName == null)
			throw new RequiredTypeException("Category name is needed");
		Category category = categoryDAO.findByName(categoryName);
		if(category == null)
			try {
				throw new NotFoundException("Category not found");
			}catch (NotFoundException e) {
				e.printStackTrace();
			}
		if(categoryUpdate.getName() != null)
			category.setName(categoryUpdate.getName());
		if(categoryUpdate.getDescription() != null)
			category.setDescription(categoryUpdate.getDescription());
		if(categoryUpdate.getImageURL() != null)
			category.setImageURL(categoryUpdate.getImageURL());
		
		Category categorySave = categoryDAO.save(category);
		
		return categorySave;
	}

}
