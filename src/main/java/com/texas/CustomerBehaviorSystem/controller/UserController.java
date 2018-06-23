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
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public 	ResponseEntity<?> save(@RequestBody User user){
		if(userService.findByUsernameOrPhoneNumberOrEmail(user.getUsername(), null, null) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> findById(@PathVariable("id") Long id){
		Optional<User> user = userService.findById(id);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}
	
		@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();
		if(users == null || users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(users, HttpStatus.FOUND);
	}
		
		@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
		public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long id){
			if(userService.findById(id)== null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			userService.updateUser(id, user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
//	@RequestMapping(value="/{username:[0-9]*[a-zA-Z]*[0-9]*}", method = RequestMethod.GET)
//	public ResponseEntity<User> findByName(@PathVariable("username") String username){
//		User user = userService.findByUsernameOrPhoneNumberOrEmail(username, null, null);
//		if(user == null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(user, HttpStatus.FOUND);
//	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<User> getUser (@RequestParam(required = false) String username,@RequestParam(required = false) String phoneNumber,@RequestParam(required = false) String email){
//		
//		User user = userService.findByUsernameOrPhoneNumberOrEmail(username, phoneNumber, email);
//		if(user == null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(user,HttpStatus.FOUND);
//	}
	

}
