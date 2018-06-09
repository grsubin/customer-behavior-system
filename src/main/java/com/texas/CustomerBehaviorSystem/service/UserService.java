package com.texas.CustomerBehaviorSystem.service;

import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.model.User;

public interface UserService{
	
	void save(User user);
	Optional<User> findById(Long id);
	User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email);
	List<User> findAll();
//	User findByUsername(String username);
//	User findByPhoneNumber(String phoneNumber);
//	User findByEmail(String email);
}
