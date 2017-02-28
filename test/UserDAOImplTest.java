package com.ciit.collaborativebackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.UserDAO;
import com.ciit.collaborativebackend.model.User;

import junit.framework.Assert;

public class UserDAOImplTest {

	@Autowired
	static UserDAO userDAO;
	
	@Autowired
	static User user;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	//Previously we written constructor
	//But in Junit we need to write a method
	//we can write @BeforeClasses only for static method.
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		user = (User) context.getBean("user");
		userDAO = (UserDAO) context.getBean( "userDAOImpl");
		
	}
	
	
	@Test
	public void getUserTestCase()
	{
		user = userDAO.get("niit");
		//Assert Statements
		Assert.assertNotNull("get User Test Case", user);
	}
	
	
	
	@Test
	public void validateTestCase()
	{
		user = userDAO.isValidUser("niit", "niit");
		Assert.assertNotNull("Valid credentials", user);
	}

	
	@Test
	public void invalidateTestCase()
	{
		user = userDAO.isValidUser("vibhav","vibhav");
		Assert.assertNull("Invalid Credentials", user);
		//or
		//Assert.assertEquals("inValidCredentials", null,user);
	}

	
	@Test
	public void getAllUsersTestCase()
	{
		int size = userDAO.list().size();  //gives number of elements in list
		Assert.assertEquals("get all users", 21, size);
	}

	
	@Test
	public void saveOrUpdateTestCase()
	{
		//you have to insert new row in db
		//provide the values for user
		
        user.setId("Bhavani6a1a");
		user.setName("Bhavani Yadav");
		user.setEmail("Bhavani00@yahoo.com");
		user.setPassword("Bhavani123");
		user.setRole("Customer");
		user.setAddress("H.no.5-246,Deepthi Sree Nagar");
		user.setIsOnline('Y');
		user.setStatus('N');
		user.setReason("You have no reasons to reject you");
		
		Assert.assertEquals("Save or update user", true, userDAO.saveOrUpdate(user));
	}
	
	
	@Test
	public void deleteUserTestCase()
	{
		Assert.assertEquals("delete user", true, userDAO.delete("Yushmithap"));
	}


}
