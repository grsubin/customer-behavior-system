package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;

public class AuthToken {

    private String token;
    private Date expiryDate;

    public AuthToken(){

    }



    public AuthToken(String token, Date expiryDate) {
		super();
		this.token = token;
		this.expiryDate = expiryDate;
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
