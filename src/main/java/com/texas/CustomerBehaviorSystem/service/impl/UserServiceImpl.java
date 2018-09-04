package com.texas.CustomerBehaviorSystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.texas.CustomerBehaviorSystem.dao.UserDAO;
import com.texas.CustomerBehaviorSystem.dto.UserDto;
import com.texas.CustomerBehaviorSystem.dto.UserUpdateDTO;
import com.texas.CustomerBehaviorSystem.model.Cart;
import com.texas.CustomerBehaviorSystem.model.Role;
import com.texas.CustomerBehaviorSystem.model.User;
import com.texas.CustomerBehaviorSystem.service.UserService;
import com.texas.CustomerBehaviorSystem.util.RoleAuthority;

import io.jsonwebtoken.RequiredTypeException;
import javassist.NotFoundException;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}




	@Override
	public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setEmail(user.getEmail());
	    newUser.setPhoneNumber(user.getPhoneNumber());
	    Set<Role> roles = new HashSet<>();
	    roles.add(new Role("USER", "User Role"));
	    newUser.setRoles(roles);
        return userDAO.save(newUser);
	}	

	@Override
	public void delete(long id) {
		userDAO.deleteById(id);
	}

	@Override
	public User findOne(String username) {

		return userDAO.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDAO.findById(id).get();
	}

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public User updateUser(Long id, UserUpdateDTO userUpdate){

		if(id == null)
			throw new RequiredTypeException("User id is needed");
		User user = userDAO.findById(id).get();
		if(userUpdate == null)
			try {
				throw new NotFoundException("User not found");
				} catch (NotFoundException e) {
				e.printStackTrace();
			}
		
		if(userUpdate.getFirstName() != null)
			user.setFirstName(userUpdate.getFirstName());
		if(userUpdate.getLastName() != null)
			user.setLastName(userUpdate.getLastName());
		if(userUpdate.getPhoneNumber() != null)
			user.setPhoneNumber(userUpdate.getPhoneNumber());			
		
		User userSave = userDAO.save(user);
		return userSave;
		

	}

//	@Override
//	public User updateUser(Long id, UserUpdateDTO user) {
//
//		if(userDAO.findById(id).get() !=null) {
//			User updateUser = userDAO.findById(id).get();
//			updateUser.
//		}
//	}
	
//	@Override
//	public void save(User user) {
//		if(userDAO.findByUsernameOrPhoneNumberOrEmail(user.getUsername(), null, null) == null)
//			if(user.getId() == null) {
//				Cart cart = new Cart();
//				
//				
//			}
//			userDAO.save(user);
//	}
//	
//	@Override
//	public Optional<User> findById(Long id) {
//		Optional<User> user = userDAO.findById(id);
//		return user;
//	}
//
//	@Override
//	public User findByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email) {
//		User user = userDAO.findByUsernameOrPhoneNumberOrEmail(username, phoneNumber, email);
//		return user;
//	}
//	
//	@Override
//	public List<User> findAll() {
//		List<User> users = userDAO.findAll();
//		return users;
//	}	
//	
//	@Override
//	public void updateUser(Long id, User user) {
//		Date creationDate = userDAO.findById(id).get().getCreationDate();
//		user.setCreationDate(creationDate);
//		userDAO.save(user);
//	}
//	@Override
//	public User findByUsername(String username) {
//		User user = userDAO.findByUsername(username);
//		return user;
//	}
//
//	@Override
//	public User findByPhoneNumber(String phoneNumber) {
//		User user = userDAO.findByPhoneNumber(phoneNumber);
//		return user;
//	}
//
//	@Override
//	public User findByEmail(String email) {
//		User user = userDAO.findByEmail(email);
//		return user;
//	}
}

