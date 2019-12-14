//package com.texas.CustomerBehaviorSystem.config;
//
//import apriori4j.*;
//import java.util.List;
//class ApriorConfig{
//	
//	
//	List<Transaction> transactions = ApriorTransactionConfig.prepareTransaction();
//	Double minSupport = 0.15;
//	Double minConfidence = 0.6;
//	
//	AprioriAlgorithm apriori = new AprioriAlgorithm(minSupport, minConfidence);
//	AnalysisResult result = apriori.analyze(transactions);
//
//}