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
@Table(name="FORUM")
@Component
public class Forum extends BaseDomain{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FORUM_ID")
	private Integer id;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="FORUM_TOPIC")
	private String topic;
	@Column(name="CREATED_DATE")
	private Date createdDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
