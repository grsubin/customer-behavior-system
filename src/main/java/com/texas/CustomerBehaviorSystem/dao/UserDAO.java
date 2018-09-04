package com.texas.CustomerBehaviorSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
