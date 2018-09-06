package com.texas.CustomerBehaviorSystem.service;

import java.util.List;
import java.util.Optional;

import com.texas.CustomerBehaviorSystem.dto.UserDto;
import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.User;

public interface UserService{
	
//	User save(User user);
//	Optional<User> findById(Long id);	
//	List<User> findAll();
	
    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);

    
	User updateUser(String username, UserUpdateDTO user);
//	User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email);

//	User findByUsername(String username);
//	User findByPhoneNumber(String phoneNumber);
//	User findByEmail(String email);
}
