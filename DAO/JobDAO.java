package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Job;

public interface JobDAO {
	public Job getJob(Integer id);
	
	public List<Job> getAllJobs();
	
	public List<Job> getAllStatusJobs();
	
	public boolean save(Job job);
	
	public boolean update(Job job);
	
	public boolean delete(Integer id);
}
