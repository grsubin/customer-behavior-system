package com.texas.CustomerBehaviorSystem.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.texas.CustomerBehaviorSystem.config.TokenProvider;
import com.texas.CustomerBehaviorSystem.dto.LoginDTO;
import com.texas.CustomerBehaviorSystem.dto.UserDto;
import com.texas.CustomerBehaviorSystem.model.AuthToken;
import com.texas.CustomerBehaviorSystem.model.Role;
import com.texas.CustomerBehaviorSystem.service.UserService;

import io.swagger.annotations.ApiImplicitParam;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ApiImplicitParam(name = "token",  required = true, dataType = "String", paramType = "Header")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Set<Role> roles=userService.findOne(jwtTokenUtil.getUsernameFromToken(token)).getRoles();
        return ResponseEntity.ok(new AuthToken(token, jwtTokenUtil.getExpirationDateFromToken(token), roles));
    }
    
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public 	ResponseEntity<?> save(@RequestBody UserDto user){
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
    

}