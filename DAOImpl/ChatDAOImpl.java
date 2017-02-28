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

import com.ciit.collaborativebackend.dao.ChatDAO;
import com.ciit.collaborativebackend.model.Chat;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO {
	
	Logger log = LoggerFactory.getLogger(ChatDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ChatDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ChatDAOImpl(){
		
	}
	
	@Transactional
	public Chat getChat(Integer id) {
		return (Chat) sessionFactory.getCurrentSession().get(Chat.class,id);
	}

	@Transactional
	public List<Chat> getAllChats() {
		String hql = "from Chat";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Transactional
	public List<Chat> getAllChats(String userId) {
		String hql = "from Chat where userId='"+userId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Chat chat) {
		log.debug("Starting of the method : save");
		
			try {
				sessionFactory.getCurrentSession().save(chat);
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
			Chat chat = new Chat();
			chat.setId(id);
			sessionFactory.getCurrentSession().delete(chat);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
	

}
