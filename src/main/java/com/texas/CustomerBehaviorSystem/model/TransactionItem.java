package com.texas.CustomerBehaviorSystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TransactionItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	private double totalPrice;
	private int quantity;
		
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	@JsonIgnore
	private Transaction transaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}