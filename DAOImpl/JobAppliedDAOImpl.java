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

import com.ciit.collaborativebackend.dao.JobAppliedDAO;
import com.ciit.collaborativebackend.model.Forum;
import com.ciit.collaborativebackend.model.Job;
import com.ciit.collaborativebackend.model.JobApplied;

@Repository("jobAppliedDAO")
public class JobAppliedDAOImpl implements JobAppliedDAO {
	Logger log = LoggerFactory.getLogger(JobAppliedDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobAppliedDAOImpl(SessionFactory sessionFactory){
		
		this.sessionFactory = sessionFactory;
		
	}
	
	@Transactional
	public JobApplied getJobApplied(Integer id) {
		return (JobApplied) sessionFactory.getCurrentSession().get(JobApplied.class, id);
	}

	@Transactional
	public List<JobApplied> getAllApplied() {
		String hql ="from JobApplied";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<JobApplied> getAllAppliedByUser(String userId) {
		String hql = "from JobApplied where userId = '"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(JobApplied jobApplied) {
		log.debug("Starting of the method: save");
		try {
			sessionFactory.getCurrentSession().save(jobApplied);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : save");
			return true;	
	}

	@Transactional
	public boolean update(JobApplied jobApplied) {
		log.debug("Starting of the method: save");
		try {
			sessionFactory.getCurrentSession().update(jobApplied);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : save");
			return true;	
	}

	@Transactional
	public boolean delete(Integer id) {
		log.debug("Starting of the method: delete");
		try {
			JobApplied jobApplied = new JobApplied();
			jobApplied.setId(id);
			sessionFactory.getCurrentSession().delete(jobApplied);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
