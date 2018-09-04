package com.texas.CustomerBehaviorSystem.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("api/v1/admin/users")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET)
public ResponseEntity<List<User>> findAll(){
	List<User> users = userService.findAll();
	if(users == null || users.isEmpty())
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	return new ResponseEntity<>(users, HttpStatus.FOUND);
}

}
