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

import com.ciit.collaborativebackend.dao.ForumDAO;
import com.ciit.collaborativebackend.model.Event;
import com.ciit.collaborativebackend.model.Forum;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO{
	
	Logger log = LoggerFactory.getLogger(ForumDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Forum getForum(Integer id) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	@Transactional
	public List<Forum> getAllForums() {
		String hql = "from Forum";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<Forum> getAllForums(String userId) {
		String hql = "from Forum where userId'"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Forum forum) {
		log.debug("Starting of the method :save");
		try {
			sessionFactory.getCurrentSession().save(forum);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : save");
		return true;
	}

	@Transactional
	public boolean update(Forum forum) {
		log.debug("Starting of the method :update");
		
		try {
			sessionFactory.getCurrentSession().update(forum);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method :update");
		return true;
		
	}
	
	@Transactional
	public boolean delete(Integer id) {
		log.debug("Starting of the method: delete");
		try {
			Event event = new Event();
			event.setId(id);
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Ending of the method: delete");
			return false;
		}
	}
	
	
}
