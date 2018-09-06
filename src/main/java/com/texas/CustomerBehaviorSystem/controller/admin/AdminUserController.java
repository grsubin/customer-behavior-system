package com.texas.CustomerBehaviorSystem.controller.admin;

import static com.texas.CustomerBehaviorSystem.model.Constants.TOKEN_PREFIX;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/admin/users")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(@RequestHeader String authorization){
		List<User> users = userService.findAll();
		if(users == null || users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(users, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> findByUsername(@RequestHeader String authorization,@PathVariable("username") String username){

		User user = userService.findOne(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user, HttpStatus.FOUND);

	}
		
	@RequestMapping(value = "/{username}",method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserById(@RequestHeader String authorization,@RequestBody UserUpdateDTO userUpdate, @PathVariable String username){

		if(userService.findOne(username)== null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		User user = userService.updateUser(username, userUpdate);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	

}
