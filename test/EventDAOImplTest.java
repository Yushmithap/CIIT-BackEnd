package com.ciit.collaborativebackend.test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ciit.collaborativebackend.dao.EventDAO;
import com.ciit.collaborativebackend.model.Event;

import junit.framework.Assert;

public class EventDAOImplTest {

	@Autowired
	static EventDAO eventDAO;
	
	@Autowired
	static Event event;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context= new AnnotationConfigApplicationContext();
		context.scan("com.ciit.collaborativebackend");
		context.refresh();
		
		event = (Event) context.getBean("event");
		eventDAO = (EventDAO) context.getBean("eventDAOImpl");
		
	}
	
	@Test
	public void getEvent(){
		event = eventDAO.getEvent(01);
		Assert.assertNotNull("get the event",event);
	}
	
	@Test
	public void getAllEvents(){
		int size = eventDAO.getAllEvents().size();
		Assert.assertEquals("get all events", 2, size);
	}
	
	@Test
	public void saveEvent(){
		event.setId(01);
		event.setName("Angara event");
		event.setVenue("Road 229,Banjara Street,Near Hanmakonda");
		event.setDescription("It is a robotic exhibition to be held as a part of developing NIIT studets IQ");
		event.setDateTime(new Date());
		Assert.assertEquals("save the event", true, eventDAO.save(event));
	}
	
	@Test
	public void updateEvent(){
		event.setId(01);
		event.setName("ROBOEXIBO");
		Assert.assertEquals("update the event", true, eventDAO.update(event));
		
	}
	
	@Test
	public void deleteEvent(){
		Assert.assertEquals("delete the event",true,eventDAO.delete(02));
	}
	
	
	
}
