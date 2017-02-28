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
@Table(name="Y_JOB")
@Component
public class Job extends BaseDomain {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		private String title;
		private String qualification;
		private char status;
		private String description;
		@Column(name="DATE_TIME")
		private Date dateTime;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public char getStatus() {
			return status;
		}
		public void setStatus(char status) {
			this.status = status;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getDateTime() {
			return dateTime;
		}
		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}
		
		
}
