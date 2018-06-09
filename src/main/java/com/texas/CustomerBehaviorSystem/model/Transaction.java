package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;

import java.util.List;

import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "transaction_id")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JsonBackReference
	private User user;
	
	@ManyToMany
	private List<Product> products;
	
	@CreationTimestamp
	private Date creationDate;

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
