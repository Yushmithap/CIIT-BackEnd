package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.FriendDAO;
import com.ciit.collaborativebackend.dao.FriendDAO;
import com.ciit.collaborativebackend.model.Friend;
import com.ciit.collaborativebackend.model.Friend;

import junit.framework.Assert;

public class FriendDAOImplTest {

	@Autowired
	static FriendDAO friendDAO;

	@Autowired
	static Friend friend;

	@Autowired
	static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();

		friend = (Friend) context.getBean("friend");
		friendDAO = (FriendDAO) context.getBean("friendDAOImpl");
	}

	@Test
	public void getfriendById() {
		friend = friendDAO.getFriend(02);
		Assert.assertNotNull("get friend by id", friend);
	}

	@Test
	public void getAllfriends() {
		int size = friendDAO.getAllFriends().size();
		Assert.assertEquals("get all friends", 2, size);
	}

	@Test
	public void getAllfriendsUser() {
		int size = friendDAO.getAllFriends("yushmitha6a1a").size();
		Assert.assertEquals("get all friends by user", 2, size);
	}

	@Test
	public void savefriend() {
		friend.setId(02);
		friend.setUserId("yushmtiha6a1a"); //references
		friend.setFriendId("saru06"); //references
		friend.setIsOnline('Y');
		friend.setStatus('N');
		Assert.assertEquals("save the friendr", true, friendDAO.save(friend));
	}

	@Test
	public void updatefriend() {
		friend.setId(02);
		friend.setStatus('O');
		Assert.assertEquals("get all friends by user", true, friendDAO.update(friend));
	}

	@Test
	public void deletefriend() {
		Assert.assertEquals("get all friends by user", true, friendDAO.delete(02));
	}

}
