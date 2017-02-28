package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Chat;

public interface ChatDAO {
	public Chat getChat(Integer id);
	
	public List<Chat> getAllChats();
	
	public List<Chat> getAllChats(String userId);
	
	public boolean save(Chat chat);
	
	public boolean delete(Integer id);
}
