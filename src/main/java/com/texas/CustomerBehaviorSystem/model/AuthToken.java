package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;
import java.util.Set;

public class AuthToken {

    private String token;
    private Date expiryDate;
    private Set<Role> roles;

    public AuthToken(){

    }


	public AuthToken(String token, Date expiryDate, Set<Role> roles) {
		super();
		this.token = token;
		this.expiryDate = expiryDate;
		this.roles = roles;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

    
}
