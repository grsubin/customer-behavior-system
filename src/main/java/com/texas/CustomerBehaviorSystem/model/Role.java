package com.texas.CustomerBehaviorSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.texas.CustomerBehaviorSystem.util.RoleAuthority;

@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;
	private RoleAuthority roleAuthority;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleAuthority getRoleAuthority() {
		return roleAuthority;
	}

	public void setRoleAuthority(RoleAuthority roleAuthority) {
		this.roleAuthority = roleAuthority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
