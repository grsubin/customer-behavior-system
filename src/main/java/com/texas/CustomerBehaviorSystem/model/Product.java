package com.texas.CustomerBehaviorSystem.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
	private Long id;
	
    @Column(name = "name", nullable = false, unique = true)
    @Length(min = 3, message = "*Name must have at least 5 characters")
	private String name;
    
    @Column(name = "description")
	private String description;
	
    @Column(name = "stock", nullable = false)
    @Min(value = 0, message = "*Stock has to be non negative number")
	private Integer stock;
    
    
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
	private double price;
    
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItemLists;
	
    @OneToMany(mappedBy = "product")
    @JsonIgnore
	private List<TransactionItem> transactionItemLists;
	
	@CreationTimestamp
	private Date creationDate;
	
	@UpdateTimestamp
	private Date updateProductDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CartItem> getCartItemLists() {
		return cartItemLists;
	}

	public void setCartItemLists(List<CartItem> cartItemLists) {
		this.cartItemLists = cartItemLists;
	}



	public List<TransactionItem> getTransactionItemLists() {
		return transactionItemLists;
	}

	public void setTransactionItemLists(List<TransactionItem> transactionItemLists) {
		this.transactionItemLists = transactionItemLists;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateProductDate() {
		return updateProductDate;
	}

	public void setUpdateProductDate(Date updateProductDate) {
		this.updateProductDate = updateProductDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", stock=" + stock + ", price="
				+ price + ", category=" + category + ", cartItemLists=" + cartItemLists + ", transactionItemLists="
				+ transactionItemLists + ", creationDate=" + creationDate + ", updateProductDate=" + updateProductDate
				+ "]";
	}

	
}
