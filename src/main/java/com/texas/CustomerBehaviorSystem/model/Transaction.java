package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;

	@ManyToMany
	@JoinTable(name = "transaction_transaction_items",joinColumns = @JoinColumn(name = "transaction_id"),inverseJoinColumns = @JoinColumn(name="transaction_item_id"))
	private List<TransactionItem> transactionItems;
	
	@CreationTimestamp
	private Date creationDate;

	
	
}
