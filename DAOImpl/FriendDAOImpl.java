package com.ciit.collaborativebackend.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ciit.collaborativebackend.dao.FriendDAO;
import com.ciit.collaborativebackend.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession(); 
	}

	@Transactional
	public Friend getFriend(Integer id) {
		return (Friend) getSession().get(Friend.class, id);
	}

	@Transactional
	public List<Friend> getAllFriends() {
		String hql = "from Friend";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<Friend> getAllFriends(String userId) {
		String hql = "from Friend where userId='"+userId+"'";
		Query query=getSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Friend friend) {
		log.debug("Starting of the method:save");
		try {
			getSession().save(friend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("Ending of the method : save");
		return true;
	}
	
	@Transactional
	public boolean update(Friend friend) {
		log.debug("Starting of the method:save");
		try {
			getSession().update(friend);
		} catch (Exception e) {
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
			Friend friend = new Friend();
			friend.setId(id);
			getSession().delete(friend);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Ending of the method: delete");
			return false;
		}
	}
	
	
	
}
