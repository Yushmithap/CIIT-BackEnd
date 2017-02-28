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

import com.ciit.collaborativebackend.dao.UserDAO;
import com.ciit.collaborativebackend.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(){
		
	}
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {
		log.debug("Starting of the method list");
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method list");
		return query.list();
	}
	
	@Transactional
	public boolean saveOrUpdate(User user) {
		log.debug("Starting of the method saveOrUpdate");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("not able to save the record"+e.getMessage());
			e.printStackTrace();
		}
		log.debug("Ending of the method saveOrUpdate");
		return true;
		
	}

	@Transactional
	public boolean delete(String id) {
		log.debug("Starting of the method : delete");
		try {
			User user = new User();
			user.setId(id);
			sessionFactory.getCurrentSession().delete(user);
			log.debug("Ending of the method : delete");
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("Not able to delete the record"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public User get(String id) {
		log.debug("Starting of the method: get");
		String hql = "from User where id="+"'"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);	
		}
		log.debug("Ending of the method: getByName");
		return null;
	}
	
	@Transactional
	public User isValidUser(String id, String password) {
		log.debug("Starting of the method: is ValidUser");
		log.info("The user id"+id);
		String hql = "from User where id='"+id+"' and password='"+password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method : is valid user");
		return (User)query.uniqueResult();
		
	}

}
