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

import com.ciit.collaborativebackend.dao.EventDAO;
import com.ciit.collaborativebackend.model.Event;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {
	Logger log = LoggerFactory.getLogger(EventDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Event getEvent(Integer id) {
		return (Event) sessionFactory.getCurrentSession().get(Event.class, id);
	}

	@Transactional
	public List<Event> getAllEvents() {
		String hql = "from Event";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(Event event) {
		log.debug("Starting of the method :save");
		
		try {
			sessionFactory.getCurrentSession().save(event);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Ending of the method: save");
		return true;
	}

	@Transactional
	public boolean update(Event event) {
		log.debug("Starting of the method: update");
		
		try {
			sessionFactory.getCurrentSession().update(event);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("ending of the method: update");
		return true;
	}

	@Transactional
	public boolean delete(Integer id) {
		log.debug("Starting of the method: delete");
		
		try {
			Event event = new Event();
			event.setId(id);
			sessionFactory.getCurrentSession().delete(id);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Ending of the method: delete");
			return false;
		}
	}
	
	
	
	
}
