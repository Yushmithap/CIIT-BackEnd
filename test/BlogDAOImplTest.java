package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.BlogDAO;
import com.ciit.collaborativebackend.model.Blog;

import junit.framework.Assert;

public class BlogDAOImplTest {

	@Autowired
	static BlogDAO blogDAO;
	
	@Autowired
	static Blog blog;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		blog = (Blog) context.getBean("blog");
		blogDAO = (BlogDAO) context.getBean("blogDAOImpl");
	}
	
	@Test
	public void getBlogTestCase()
	{
		blog = blogDAO.getBlog(01);
		//Assert Statements
		Assert.assertNotNull("get Blog Test Case", blog);
	}
	
	@Test
	public void getAllBlogsTestCase(){
		int size = blogDAO.getAllBlogs().size();  //gives number of elements in list
		Assert.assertEquals("get all users", 21, size);
	}
	
	@Test
	public void getBlogByUserTest(){
		int size = blogDAO.getAllBlogs("niit").size();
		Assert.assertEquals("get all blogs", 21, size);
	}
	
	@Test
	public void saveTest(){
		blog.setId(02);
		blog.setTitle("Job in Noida post description");
		blog. setDescription("Hello java in the world...please dont tell me thi ");
		blog.setStatus("A");
		blog.setDateTime(new Date());
		blog.setUserId("cppt");	
		Assert.assertEquals("Save or update user", true, blogDAO.save(blog));
	}
	
	@Test
	public void updateTest(){
		blog.setId(02);
		blog.setTitle("Same Post");
		Assert.assertEquals("Save or update user", true, blogDAO.update(blog));
	}
	
	
}
