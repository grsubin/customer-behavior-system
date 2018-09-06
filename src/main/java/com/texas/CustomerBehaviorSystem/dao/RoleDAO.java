package com.texas.CustomerBehaviorSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.texas.CustomerBehaviorSystem.model.Role;
import com.texas.CustomerBehaviorSystem.util.RoleAuthority;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long>{

	Role findByName(RoleAuthority name);
}
