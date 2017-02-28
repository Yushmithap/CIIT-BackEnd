package com.ciit.collaborativebackend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ciit.collaborativebackend.dao.UserDAO;
import com.ciit.collaborativebackend.model.User;

import oracle.net.aso.e;

@RestController
public class UserController {
		
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/getAllUsers/")
	public ResponseEntity< List<User> > getAllUsers(){
			List users = userDAO.list();
		if(users.isEmpty()){
			user.setErrorCode("404");
			user.setErrorMessage("No users are available");
			users.add(user);
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		}
		user.setErrorCode("200");
		user.setErrorMessage("Successfully fetched the user");
		
		return new ResponseEntity <List<User>>(users,HttpStatus.OK);
		
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id){
		user = userDAO.get(id);
		if(user==null){
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("No user found with this id:"+id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login/")
	public ResponseEntity<User> login(@RequestBody User user){
		user = userDAO.isValidUser(user.getId(),user.getPassword());
		if(user==null){
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials ... Please try again");
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("You are successfully logged in");
			
			session.setAttribute("loggedInUserId", user.getId());
			session.setAttribute("loggedInUserRole", user.getRole());
		}
		return new ResponseEntity<User> (user, HttpStatus.OK);
		
	}
	
	@PostMapping("/saveUser/")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		if(userDAO.get(user.getId())==null){
			userDAO.saveOrUpdate(user);
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully registered");
		}
		else{
			user.setErrorCode("404");
			user.setErrorMessage("User eists with this id"+user.getId());
		}
		return new ResponseEntity<User> (user, HttpStatus.OK);
	}
	
}
