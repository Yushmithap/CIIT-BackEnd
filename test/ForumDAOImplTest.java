package com.ciit.collaborativebackend.test;


import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.ForumDAO;
import com.ciit.collaborativebackend.model.Forum;

import junit.framework.Assert;

public class ForumDAOImplTest {

	@Autowired
	static ForumDAO forumDAO;
	
	@Autowired
	static Forum forum;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		forum = (Forum) context.getBean("forum");
		forumDAO = (ForumDAO) context.getBean("forumDAOImpl");
	}
	
	@Test
	public void getForumById(){
		forum = forumDAO.getForum(02);
		Assert.assertNotNull("get forum by id",forum);
	}
	
	@Test
	public void getAllForums(){
		int size = forumDAO.getAllForums().size();
		Assert.assertEquals("get all forums",2,size);
	}
	
	@Test
	public void getAllForumsUser(){
		int size  =forumDAO.getAllForums("yushmitha6a1a").size();
		Assert.assertEquals("get all forums by user",2,size);
	}
	
	@Test
	public void saveForum(){
		forum.setId(02);
		forum.setUserId("yushmtiha6a1a");
		forum.setTopic("It is not the user that it is going to be ");
		forum.setCreatedDate(new Date());
		Assert.assertEquals("save the forumr",true,forumDAO.save(forum));
	}
	
	@Test
	public void updateForum(){
		forum.setId(02);
		forum.setTopic("It is about my self topic ");
		Assert.assertEquals("get all forums by user",true,forumDAO.update(forum));
	}
	
	@Test
	public void deleteForum(){
		Assert.assertEquals("get all forums by user",true,forumDAO.delete(02));
	}
}