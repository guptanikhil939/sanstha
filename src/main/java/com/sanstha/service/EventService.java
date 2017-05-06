package com.sanstha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.dto.EventDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;

public interface EventService
{
	public boolean createEvent(HttpServletRequest request)
			throws PersistenceException, ServiceException;

	public EventDTO viewEvent(HttpServletRequest request)
			throws PersistenceException, ServiceException;

	public List<EventDTO> getEventList(HttpServletRequest request,
			Integer societyId) throws PersistenceException, ServiceException;
}