package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Blog;

public interface BlogDAO {

	public Blog getBlog(Integer id);
	
	public List<Blog> getAllBlogs();
	
	public List<Blog> getAllBlogs(String userID);
	
	public boolean save(Blog blog);
	
	public boolean update(Blog blog);
}
