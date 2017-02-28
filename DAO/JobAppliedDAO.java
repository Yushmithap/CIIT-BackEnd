package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.JobApplied;

public interface JobAppliedDAO {
	public JobApplied getJobApplied(Integer id);
	
	public List<JobApplied> getAllApplied();
	
	public List<JobApplied> getAllAppliedByUser(String userId);
	
	public boolean save(JobApplied jobApplied);
	
	public boolean update(JobApplied jobApplied);
	
	public boolean delete(Integer id);
	
}
