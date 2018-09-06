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

import com.texas.CustomerBehaviorSystem.config.TokenProvider;
import com.texas.CustomerBehaviorSystem.dto.UserDto;
import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;
import static com.texas.CustomerBehaviorSystem.model.Constants.TOKEN_PREFIX;


@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;
	

	
//	@RequestMapping(value="/{id}", method = RequestMethod.GET)
//	public ResponseEntity<User> findById(@RequestHeader String authorization,@PathVariable("id") Long id){
//		User user = userService.findById(id);
//		if(user == null)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(user, HttpStatus.FOUND);
//	}
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> findByUsername(@RequestHeader String authorization,@PathVariable("username") String username){
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		if(tokenUsername.equals(username)) {
			User user = userService.findOne(username);
			if(user == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	

	
		
	@RequestMapping(value = "/{username}",method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserById(@RequestHeader String authorization,@RequestBody UserUpdateDTO userUpdate, @PathVariable String username){
		authorization = authorization.replace(TOKEN_PREFIX, "");
		String tokenUsername = jwtTokenUtil.getUsernameFromToken(authorization);
		if(tokenUsername.equals(username)) {
			if(userService.findOne(username)== null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			User user = userService.updateUser(username, userUpdate);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
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
