package com.texas.CustomerBehaviorSystem.service;

import java.util.List;

import com.texas.CustomerBehaviorSystem.model.Product;

import apriori4j.AnalysisResult;
import apriori4j.AprioriTimeoutException;

public interface AprioriService {

	AnalysisResult getResult();
	void runAlgorithm()throws AprioriTimeoutException;
	List<apriori4j.Transaction> prepareTransaction();
	
	List<Product>getRecommendationByProductId(Long productId);
}
