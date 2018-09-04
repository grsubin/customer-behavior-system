package com.texas.CustomerBehaviorSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.dto.UserDto;
import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;
	

	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@RequestHeader String authorization,@PathVariable("id") Long id){
		User user = userService.findById(id);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}
	

		
		@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
		public ResponseEntity<User> updateUserById(@RequestBody UserUpdateDTO userUpdate, @PathVariable Long id){
			if(userService.findById(id)== null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			User user = userService.updateUser(id, userUpdate);
			return new ResponseEntity<>(user, HttpStatus.OK);
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
