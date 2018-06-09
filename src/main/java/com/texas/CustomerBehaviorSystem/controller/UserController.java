package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public 	ResponseEntity<?> save(@RequestBody User user){
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
		Optional<User> user = userService.findById(id);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> getUser (@RequestParam(required = false) String username,@RequestParam(required = false) String phoneNumber,@RequestParam(required = false) String email){
		
		User user = userService.findByUsernameOrPhoneNumberOrEmail(username, phoneNumber, email);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user,HttpStatus.FOUND);
	}
	@RequestMapping(value="user_list", method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		if(users == null || users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(users, HttpStatus.FOUND);
	}
}
