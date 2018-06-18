package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;




@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private Long id;
	
    @Column(name = "first_name")
    @NotEmpty(message = "*Please provide your name")
	private String firstName;
    
    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
	private String lastName;
    
    @Column(unique = true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String email;
    
    @Column(unique = true)
    @NotEmpty(message = "*Please provide a phone number")
	private String phoneNumber;
    
    @OneToOne
	private Login login;
    
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Transaction> transactions;    
    
    
	@CreationTimestamp
	private Date creationDate;
	
	@UpdateTimestamp
	private Date updateMemberShipDate;


	
}
