package com.ciit.collaborativebackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Y_CHAT")
@Component
public class Chat extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="user_id")
	private String userId;
	@Column(name="friend_id")
	private String friendId;
	@Column(name="massage")
	private String message;
	@Column(name="date_time")
	private Date dateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id2) {
		this.id = id2;
	}
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
