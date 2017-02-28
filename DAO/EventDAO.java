package com.ciit.collaborativebackend.dao;

import java.util.List;

import com.ciit.collaborativebackend.model.Event;

public interface EventDAO {

	public Event getEvent(Integer id);

	public List<Event> getAllEvents();

	public boolean save(Event event);

	public boolean update(Event event);

	public boolean delete(Integer id);

}