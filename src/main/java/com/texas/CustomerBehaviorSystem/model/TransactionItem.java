package com.texas.CustomerBehaviorSystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

@Entity
public class TransactionItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_item_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "*Quantity has to be non negative number")
	private Integer quantity;
    
	@ManyToMany(mappedBy = "transactionItems")
	private List<Transaction> transactions;

}
