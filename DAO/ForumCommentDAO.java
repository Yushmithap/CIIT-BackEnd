package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.ForumComment;

public interface ForumCommentDAO {

	public ForumComment getForumComment(Integer id);
	
	public List<ForumComment> getAllForumComments();
	
	public List<ForumComment> getAllForumComments(String userId);
	
	public boolean save(ForumComment forumComment);
	
	public boolean update(ForumComment forumComment);
	
	public boolean delete(Integer id);
}
