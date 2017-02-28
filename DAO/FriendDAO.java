package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Friend;

public interface FriendDAO {
	
	public Friend getFriend(Integer id);
	
	public List<Friend> getAllFriends();
	
	public List<Friend> getAllFriends(String userId);
	
	public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public boolean delete(Integer id);
	
	
	
}
