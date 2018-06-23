package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;



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
    
    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3, message = "*Your username must have at least 3 characters")
    @NotEmpty(message = "*Please provide your username")
	private String username;
    
    @Column(name = "password", nullable = false)
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide your password")
	private String password;
    
    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;
    
    @OneToMany(mappedBy = "user")
    private List<Role> roles;
 
	@CreationTimestamp
	private Date creationDate;
	
	@UpdateTimestamp
	private Date updateMemberShipDate;
	

	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Transaction> transactions;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


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


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getUpdateMemberShipDate() {
		return updateMemberShipDate;
	}


	public void setUpdateMemberShipDate(Date updateMemberShipDate) {
		this.updateMemberShipDate = updateMemberShipDate;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

}
