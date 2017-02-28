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

import com.ciit.collaborativebackend.dao.BlogDAO;
import com.ciit.collaborativebackend.model.Blog;

@Repository 
//subcategory of component..it creates instance..suppose want  to interact with database
public class BlogDAOImpl implements BlogDAO {
	
	Logger log  = LoggerFactory.getLogger(BlogDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Blog getBlog(Integer id) {
		return (Blog)sessionFactory.getCurrentSession().get(Blog.class, id); //returns only single row
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ciit.collaborativebackend.dao.BlogDAO#getAllBlogs()
	 * It will return all the approved blogs
	 */
	@Transactional
	public List<Blog> getAllBlogs() {
		String hql = "from Blog where status='A'";//domain name not table name
		//in queries we can write domains and properties
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
			}
	/*
	 * (non-Javadoc)
	 * @see com.ciit.collaborativebackend.dao.BlogDAO#getAllBlogs(java.lang.String)
	 * It will return all the blogs that belong to particular user
	 */
	@Transactional
	public List<Blog> getAllBlogs(String userID) {
		//select * from Blog where userID = 'yushmitha';
		String hql = "from Blog where userID = '"+userID+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Transactional
	public boolean save(Blog blog) {
		log.debug("Starting of the method : save");
		try {
			sessionFactory.getCurrentSession().save(blog);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : save");
		return true;
	}


	public boolean update(Blog blog) {
		log.debug("Starting of the method : update");
		try {
			sessionFactory.getCurrentSession().update(blog);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method : update");
		return true;
	}

}
