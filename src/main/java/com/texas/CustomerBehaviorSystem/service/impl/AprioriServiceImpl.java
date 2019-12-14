package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texas.CustomerBehaviorSystem.model.Product;
import com.texas.CustomerBehaviorSystem.model.TransactionItem;
import com.texas.CustomerBehaviorSystem.service.AprioriService;
import com.texas.CustomerBehaviorSystem.service.ProductService;
import com.texas.CustomerBehaviorSystem.service.TransactionService;

import apriori4j.AnalysisResult;
import apriori4j.AprioriAlgorithm;
import apriori4j.AprioriTimeoutException;
import apriori4j.AssociationRule;
import apriori4j.ItemSet;
import apriori4j.Transaction;


@Service
public class AprioriServiceImpl implements AprioriService {

	
	List<Transaction> transactions;
	Double minSupport = 0.5;
	Double minConfidence = 0.8;
	
	 List<apriori4j.Transaction> transactionsForApriori = new ArrayList<>();
	
	 Set<String> transactionProductIds = new HashSet<>();
	
	AnalysisResult result;
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	ProductService productService;
	
	@Override
	public AnalysisResult getResult() {

		return result;
	}

	@Override
	public void runAlgorithm()throws AprioriTimeoutException {
		
		transactions = prepareTransaction();
		AprioriAlgorithm apriori = new AprioriAlgorithm(minSupport, minConfidence);
		result = apriori.analyze(transactions);		
	}

	
	 @Override
	 public List<apriori4j.Transaction> prepareTransaction(){
		
		
		List<com.texas.CustomerBehaviorSystem.model.Transaction> transactionList = transactionService.findAll();

		if(transactionList == null || transactionList.isEmpty()) {
           throw new IllegalArgumentException("Transaction List is empty");
			}else {
			for(com.texas.CustomerBehaviorSystem.model.Transaction transaction: transactionList) {
				
				for(TransactionItem transactionItem: transaction.getTransactionItems()) {
					transactionProductIds.add(transactionItem.getProductId().toString());
				}
				
				apriori4j.Transaction transactionProductIdsForApriori  = new apriori4j.Transaction(transactionProductIds);
				transactionsForApriori.add(transactionProductIdsForApriori);
			}
			return transactionsForApriori;
		}

	 }

	@Override
	public List<Product> getRecommendationByProductId(Long productId) {

		Product product = productService.findById(productId);
		List<Product> recommendedList = new ArrayList<>();
		if(product == null) {
			throw new IllegalArgumentException("Product does not exist.");
		}else {
			for(AssociationRule rule: result.getAssociationRules()) {
				if(recommendedList.size() >=10 )
					return recommendedList;
				if(rule.getLeftHandSide().size() == 1 && rule.getLeftHandSide().contains(productId.toString())) {
					for(String item: rule.getRightHandSide()) {
						recommendedList.add(productService.findById(Long.parseLong(item)));				
					}
				
				}
			}
			return recommendedList;
		}
	}
}
