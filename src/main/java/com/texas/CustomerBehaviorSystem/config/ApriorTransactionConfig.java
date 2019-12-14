//package com.texas.CustomerBehaviorSystem.config;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.texas.CustomerBehaviorSystem.model.Transaction;
//import com.texas.CustomerBehaviorSystem.model.TransactionItem;
//import com.texas.CustomerBehaviorSystem.service.TransactionService;
//
//
//public class ApriorTransactionConfig {
//
//	@Autowired
//	TransactionService transactionService;
//	
//	 List<apriori4j.Transaction> transactionsForApriori = new ArrayList<>();
//	
//	 Set<String> transactionProductIds = new HashSet<>();
//	
//	public List<apriori4j.Transaction> prepareTransaction(){
//		
//		
//		List <Transaction> transactionList = transactionService.findAll();
//
//		if(transactionList == null || transactionList.isEmpty()) {
//            throw new IllegalArgumentException("Transaction List is empty");
//			}else {
//			for(Transaction transaction: transactionList) {
//				
//				for(TransactionItem transactionItem: transaction.getTransactionItems()) {
//					transactionProductIds.add(transactionItem.getProductId().toString());
//				}
//				
//				apriori4j.Transaction transactionProductIdsForApriori  = new apriori4j.Transaction(transactionProductIds);
//				transactionsForApriori.add(transactionProductIdsForApriori);
//			}
//			return transactionsForApriori;
//		}
//
//	}
//}
