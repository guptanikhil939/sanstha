package com.sanstha.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanstha.controller.ProfileController;
import com.sanstha.dao.EventDAO;
import com.sanstha.dto.EventDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Event;
import com.sanstha.service.EventService;
import com.sanstha.util.CommonUtil;

@Service("eventService")
public class EventServiceImpl implements EventService
{
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	EventDAO eventDAO;
	
	private static final Logger log = Logger.getLogger(EventServiceImpl.class);
	
	public boolean createEvent(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method createEvent()");
		
		try
		{
			String eventName = request.getParameter("eventName") == null ? ""
					: request.getParameter("eventName");
			String eventLocation = request.getParameter("eventLocation") == null ? ""
					: request.getParameter("eventLocation");
			String societyId = request.getParameter("societyId") == null ? ""
					: request.getParameter("societyId");
			String eventStartDate = request.getParameter("eventStartDate") == null ? ""
					: request.getParameter("eventStartDate");
			String eventEndDate = request.getParameter("eventEndDate") == null ? ""
					: request.getParameter("eventEndDate");
			String eventCoordinator = request.getParameter("eventCoordinator") == null ? ""
					: request.getParameter("eventCoordinator");

			Event event = new Event();
			event.setEventName(eventName);
			event.setEventLocation(eventLocation);
			event.setSocietyId(Integer.parseInt(societyId));
			event.setEventStartDate(commonUtil.convertStringToDate(eventStartDate));
			event.setEventEndDate(commonUtil.convertStringToDate(eventEndDate));
			event.setEventCreatedDate(new Date());
			event.setEventCoordinator(eventCoordinator);

			return eventDAO.createEvent(event);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}
	
	public EventDTO viewEvent(HttpServletRequest request)
			throws PersistenceException, ServiceException
	{
		EventDTO eventDTO = null;

		try
		{
			String eventId = request.getParameter("eventId");

			Event event = eventDAO.viewEvent(eventId);

			if (null != event)
			{
				eventDTO = new EventDTO();
				eventDTO.setEventId(event.getEventId());
				eventDTO.setEventName(event.getEventName());
				eventDTO.setSocietyId(event.getSocietyId());
				eventDTO.setEventCoordinator(event.getEventCoordinator());
				eventDTO.setEventLocation(event.getEventLocation());
				eventDTO.setEventStartDate(event.getEventStartDate());
				eventDTO.setEventEndDate(event.getEventEndDate());
				eventDTO.setEventCreatedDate(event.getEventCreatedDate());
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return eventDTO;
	}

	public List<EventDTO> getEventList(HttpServletRequest request,
			Integer societyId) throws PersistenceException, ServiceException
	{
		List<EventDTO> eventDTOList = new ArrayList<EventDTO>();

		try
		{
			List<Event> eventList = eventDAO.getEventList(societyId);

			for (Event event : eventList)
			{
				EventDTO eventDTO = new EventDTO();
				eventDTO.setEventId(event.getEventId());
				eventDTO.setEventName(event.getEventName());
				eventDTO.setSocietyId(societyId);
				eventDTO.setEventCoordinator(event.getEventCoordinator());
				eventDTO.setEventLocation(event.getEventLocation());
				eventDTO.setEventStartDate(event.getEventStartDate());
				eventDTO.setEventEndDate(event.getEventEndDate());
				eventDTO.setEventCreatedDate(event.getEventCreatedDate());
				eventDTOList.add(eventDTO);
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return eventDTOList;
	}
}
