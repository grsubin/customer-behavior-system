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
	

    
	private Long deviceId;
	
	@Enumerated(EnumType.STRING)
	private LoginStatus loginStatus;
	

	private User user;



	
}
