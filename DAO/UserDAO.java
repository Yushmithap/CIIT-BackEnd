package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.User;

public interface UserDAO {
	
	public List<User> list();
	
	public boolean saveOrUpdate(User user);
	
	public boolean delete(String id);
	
	public User get(String id);
	
	public User isValidUser(String id, String password);
		
}
