package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.JobAppliedDAO;
import com.ciit.collaborativebackend.model.JobApplied;

import junit.framework.Assert;

public class JobAppliedDAOImplTest {

	@Autowired
	static JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	static JobApplied jobApplied;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		jobApplied = (JobApplied) context.getBean("jobApplied");
		jobAppliedDAO = (JobAppliedDAO) context.getBean("jobAppliedDAOImpl");
	}
	
	@Test
	public void getjobAppliedById(){
		jobApplied = jobAppliedDAO.getJobApplied(02);
		Assert.assertNotNull("get jobApplied by id",jobApplied);
	}
	
	@Test
	public void getAlljobApplieds(){
		int size = jobAppliedDAO.getAllApplied().size();
		Assert.assertEquals("get all jobApplieds",2,size);
	}
	
	@Test
	public void getAlljobAppliedsUser(){
		int size  =jobAppliedDAO.getAllAppliedByUser("yushmitha6a1a").size();
		Assert.assertEquals("get all jobApplieds by user",2,size);
	}
	
	@Test
	public void savejobApplied(){
		jobApplied.setId(02);
		jobApplied.setJobId("job06");
		jobApplied.setUserId("yushmitha6a1a");
		jobApplied.setRemarks("This person is not applicable because he is no age");
		jobApplied.setStatus('N');
	
		Assert.assertEquals("save the jobAppliedr",true,jobAppliedDAO.save(jobApplied));
	}
	
	@Test
	public void updatejobApplied(){
		jobApplied.setId(02);
		jobApplied.setRemarks("He is not eligible due to branch ");
		Assert.assertEquals("get all jobApplieds by user",true,jobAppliedDAO.update(jobApplied));
	}
	
	@Test
	public void deletejobApplied(){
		Assert.assertEquals("get all jobApplieds by user",true,jobAppliedDAO.delete(02));
	}

	
}
