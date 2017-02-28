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
@Table(name="FORUM_COMMENT")
@Component
public class ForumComment extends BaseDomain {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="FORUM_ID")
	private String forumId;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="FORUM_COMMENT")
	private String comment;
	@Column(name="COMMENTED_DATE")
	private Date commentedDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getForumId() {
		return forumId;
	}
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCommentedDate() {
		return commentedDate;
	}
	public void setCommentedDate(Date commentedDate) {
		this.commentedDate = commentedDate;
	}
}
