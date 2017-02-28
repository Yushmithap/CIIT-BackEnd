package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.JobDAO;
import com.ciit.collaborativebackend.model.Job;

import junit.framework.Assert;

public class JobDAOImplTest {

	@Autowired
	static JobDAO jobDAO;
	
	@Autowired
	static Job job;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		job = (Job) context.getBean("job");
		jobDAO = (JobDAO) context.getBean("jobDAOImpl");
	}
	
	@Test
	public void getForumById(){
		job = jobDAO.getJob(02);
		Assert.assertNotNull("get job by id",job);
	}
	
	@Test
	public void getAllForums(){
		int size = jobDAO.getAllJobs().size();
		Assert.assertEquals("get all jobs",2,size);
	}
	
	@Test
	public void getAllForumsUser(){
		int size  =jobDAO.getAllStatusJobs().size();
		Assert.assertEquals("get all jobs by user",2,size);
	}
	
	@Test
	public void saveForum(){
		job.setId(02);
		job.setTitle("Assisteant Manager");
		job.setStatus('N');
		job.setQualification("btech.msc");
		job.setDescription("It is required deep undersatnfin go c lang");
		job.setDateTime(new Date());
		Assert.assertEquals("save the jobr",true,jobDAO.save(job));
	}
	
	@Test
	public void updateForum(){
		job.setId(02);
		job.setStatus('V');
		Assert.assertEquals("get all jobs by user",true,jobDAO.update(job));
	}
	
	@Test
	public void deleteForum(){
		Assert.assertEquals("get all jobs by user",true,jobDAO.delete(02));
	}
}

