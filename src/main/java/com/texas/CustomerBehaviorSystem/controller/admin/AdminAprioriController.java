package com.texas.CustomerBehaviorSystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.Category;
import com.texas.CustomerBehaviorSystem.service.AprioriService;

import apriori4j.AnalysisResult;
import apriori4j.AprioriTimeoutException;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/admin/apriori")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminAprioriController {
	
	@Autowired
	private AprioriService aprioriService;
	
	@RequestMapping(value="/run",method=RequestMethod.GET)
	public ResponseEntity<?> runAlgorithm(@RequestHeader String authorization) throws AprioriTimeoutException{
		aprioriService.runAlgorithm();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/result",method=RequestMethod.GET)
	public ResponseEntity<AnalysisResult> getResult(@RequestHeader String authorization){

		AnalysisResult result = aprioriService.getResult();
		if(aprioriService.getResult() == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
