package com.ciit.collaborativebackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="FRIEND")
@Component
public class Friend extends BaseDomain {
	
	@Column(name="USER_ID")
	private String userId;
	@Column(name="FRIEND_ID")
	private String friendId;
	@Column(name="ID")
	private Integer id;
	@Column(name="STATUS")
	private char status;
	@Column(name="IS_ONLINE")
	private char isOnline;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	
}
