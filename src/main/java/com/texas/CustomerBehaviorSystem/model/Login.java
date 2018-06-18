package com.texas.CustomerBehaviorSystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.texas.CustomerBehaviorSystem.util.LoginStatus;

@Entity
public class Login {
	
    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3, message = "*Your username must have at least 3 characters")
    @NotEmpty(message = "*Please provide your username")
	private String username;
    
    @Column(name = "password", nullable = false)
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide your password")
	private String password;
    
	private Long deviceId;
	
	@Enumerated(EnumType.STRING)
	private LoginStatus loginStatus;
	
	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
	private User user;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
