package com.texas.CustomerBehaviorSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);
//	User findById(Long id);
	User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email);
//	User findByUsername(String username);
//	User findByPhoneNumber(String phoneNumber);
//	User findByEmail(String email);
}
