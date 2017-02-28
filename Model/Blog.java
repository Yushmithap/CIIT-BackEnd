package com.ciit.collaborativebackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.CLOB;

@Entity
@Table(name= "Y_BLOG")
@Component //instance of the blog is created
public class Blog extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="DATE_TIME")
	private Date dateTime;
	
	private String Status;
	
	private String reason;
	
	private String description;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
}
