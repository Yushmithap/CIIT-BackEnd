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

import com.ciit.collaborativebackend.dao.ForumCommentDAO;
import com.ciit.collaborativebackend.model.ForumComment;

@Repository("forumCommentDAO")
public class ForumCommentDAOImpl implements ForumCommentDAO{
	
	Logger log = LoggerFactory.getLogger(ForumCommentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumCommentDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public ForumComment getForumComment(Integer id) {
		return (ForumComment) sessionFactory.getCurrentSession().get(ForumComment.class, id);
	}

	@Transactional
	public List<ForumComment> getAllForumComments() {
		String hql = "from ForumComment";
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<ForumComment> getAllForumComments(String userId) {
		String hql = "from ForumComment where userId='"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(ForumComment forumComment) {
		log.debug("Starting of the method save");
		try {
			sessionFactory.getCurrentSession().save(forumComment);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Starting of the method save");
		return true;
	}

	@Transactional
	public boolean update(ForumComment forumComment) {
		log.debug("Starting of the method: update");
		try {
			sessionFactory.getCurrentSession().update(forumComment);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : update");
		return true;
		
	}

	@Transactional
	public boolean delete(Integer id) {
		log.debug("Starting of the method : delete");
		
		try {
			ForumComment forumComment = new ForumComment();
			forumComment.setId(id);
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Ending of the method: delete");
			return false;
		}
	}
	
	

}
