package com.sanstha.dao;

import java.util.List;

import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;

public interface EventDAO
{
	public boolean createEvent(Event event) throws PersistenceException;

	public Event viewEvent(String eventId) throws PersistenceException;

	public List<Event> getEventList(Integer societyId)
			throws PersistenceException;
}