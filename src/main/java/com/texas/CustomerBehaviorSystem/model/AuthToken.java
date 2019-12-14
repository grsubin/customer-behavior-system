package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;
import java.util.Set;

public class AuthToken {

    private String token;
    private Date expiryDate;
    private Set<Role> roles;
    private String username;

    public AuthToken(){

    }




	public AuthToken(String token, Date expiryDate, Set<Role> roles, String username) {
		super();
		this.token = token;
		this.expiryDate = expiryDate;
		this.roles = roles;
		this.username = username;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
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
