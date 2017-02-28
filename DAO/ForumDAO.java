package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Forum;

public interface ForumDAO {
	public Forum getForum(Integer id);
	
	public List<Forum> getAllForums();
	
	public List<Forum> getAllForums(String userId);
	
	public boolean save(Forum forum);
	
	public boolean update(Forum forum);
	
	public boolean delete(Integer id);
}
