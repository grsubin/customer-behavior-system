package com.texas.CustomerBehaviorSystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany
	@JoinTable(name = "cart_cart_items",joinColumns = @JoinColumn(name = "cart_id"),inverseJoinColumns = @JoinColumn(name="cart_item_id"))
	private List<CartItem> cartItems;
	
	@CreationTimestamp
	private Date creationDate;
	
	@UpdateTimestamp
	private Date updateCartDate;


}
