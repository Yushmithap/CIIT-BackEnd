package com.ciit.collaborativebackend.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.ChatDAO;
import com.ciit.collaborativebackend.model.Chat;

import junit.framework.Assert;



public class ChatDAOImplTest {

	@Autowired
	static ChatDAO chatDAO;
	
	@Autowired
	static Chat chat;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		chat = (Chat) context.getBean("chat");
		chatDAO = (ChatDAO) context.getBean("chatDAOImpl");
	}
	
	@Test
	public void getChatTestCase(){
		chat = chatDAO.getChat(1);
		Assert.assertNotNull("getChatTestCase",chat);
		
	}
	
	@Test
	public void getAllChats(){
		int size = chatDAO.getAllChats().size();
		Assert.assertEquals("get all chats", 21, size);
	}
	
	@Test
	public void getAllUserChats(){
		int size = chatDAO.getAllChats().size();
		Assert.assertEquals("get all user chats", 1, size);
	}
	
	@Test
	public void saveChat(){
		chat.setId(01);
		chat.setUserId("yushmitha6a1a");
		chat.setFriendId("sarika22");
		chat.setMessage("Hello saru..how are you baby?");
		chat.setDateTime(new Date());
		Assert.assertEquals("save the chat",true,chatDAO.save(chat));
	}
	
	@Test
	public void deleteChat(){
		Assert.assertEquals("delete the chat", true, chatDAO.delete(02));
	}
	
}
