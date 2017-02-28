package com.ciit.collaborativebackend.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ciit.collaborativebackend.dao.JobDAO;
import com.ciit.collaborativebackend.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	Logger log = LoggerFactory.getLogger(JobDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public Job getJob(Integer id) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class, id);
	}

	@Transactional
	public List<Job> getAllJobs() {
		String hql ="from Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<Job> getAllStatusJobs() {
		String hql="from Job where status='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Job job) {
		log.debug("Starting of the method: save");
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : save");
			return true;	
	}

	@Transactional
	public boolean update(Job job) {
		log.debug("Starting of the method: update");
		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method: update");
		return true;
	}

	@Transactional
	public boolean delete(Integer id) {
		log.debug("Starting of the method: delete");
		try {
			Job job = new Job();
			job.setId(id);
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
