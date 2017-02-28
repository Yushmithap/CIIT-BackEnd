package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.ForumCommentDAO;
import com.ciit.collaborativebackend.dao.ForumDAO;
import com.ciit.collaborativebackend.model.Forum;
import com.ciit.collaborativebackend.model.ForumComment;

import junit.framework.Assert;

public class ForumCommentDAOImplTest {

	@Autowired
	static ForumCommentDAO forumCommentDAO;
	
	@Autowired
	static ForumComment forumComment;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		forumComment = (ForumComment) context.getBean("forumComment");
		forumCommentDAO = (ForumCommentDAO) context.getBean("forumCommentDAOImpl");
	}
	
	@Test
	public void getForumById(){
		forumComment = forumCommentDAO.getForumComment(02);
		Assert.assertNotNull("get forum by id",forumComment);
	}
	
	@Test
	public void getAllForums(){
		int size = forumCommentDAO.getAllForumComments().size();
		Assert.assertEquals("get all forums comments",2,size);
	}
	
	@Test
	public void getAllForumsUser(){
		int size  =forumCommentDAO.getAllForumComments("yushmitha6a1a").size();
		Assert.assertEquals("get all forums by user",2,size);
	}
	
	@Test
	public void saveForum(){
		forumComment.setId(01);
		forumComment.setUserId("yushmitha6a1a");
		forumComment.setForumId("forum01");
		forumComment.setComment("This is not to be solved");
		forumComment.setCommentedDate(new Date());
		
		Assert.assertEquals("save the forumr",true,forumCommentDAO.save(forumComment));
	}
	
	@Test
	public void updateForum(){
		forumComment.setId(01);
		forumComment.setComment("It is about my self topic can be solved easily");
		Assert.assertEquals("get all forums by user",true,forumCommentDAO.update(forumComment));
	}
	
	@Test
	public void deleteForum(){
		Assert.assertEquals("get all forums by user",true,forumCommentDAO.delete(02));
	}

	
}
