package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.dao.UserDAO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void save(User user) {
		if(userDAO.findByUsernameOrPhoneNumberOrEmail(user.getUsername(), null, null) == null)
			if(user.getId() == null) {
				Cart cart = new Cart();
				
				
			}
			userDAO.save(user);
	}
	
	@Override
	public Optional<User> findById(Long id) {
		Optional<User> user = userDAO.findById(id);
		return user;
	}

	@Override
	public User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email) {
		User user = userDAO.findByUsernameOrPhoneNumberOrEmail(username, phoneNumber, email);
		return user;
	}
	
	@Override
	public List<User> findAll() {
		List<User> users = userDAO.findAll();
		return users;
	}	
	
	@Override
	public void updateUser(Long id, User user) {
		Date creationDate = userDAO.findById(id).get().getCreationDate();
		user.setCreationDate(creationDate);
		userDAO.save(user);
	}
//	@Override
//	public User findByUsername(String username) {
//		User user = userDAO.findByUsername(username);
//		return user;
//	}
//
//	@Override
//	public User findByPhoneNumber(String phoneNumber) {
//		User user = userDAO.findByPhoneNumber(phoneNumber);
//		return user;
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		User user = userDAO.findByEmail(email);
//		return user;
//	}





}
