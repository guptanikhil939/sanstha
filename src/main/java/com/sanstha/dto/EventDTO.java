package com.sanstha.dto;

import java.util.Date;

public class EventDTO
{
	private Integer eventId;
	private String eventName;
	private Integer societyId;
	private String eventCoordinator;
	private String eventLocation;
	private Date eventStartDate;
	private Date eventEndDate;
	private Date eventCreatedDate;

	public Integer getEventId()
	{
		return eventId;
	}

	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	public Integer getSocietyId()
	{
		return societyId;
	}

	public void setSocietyId(Integer societyId)
	{
		this.societyId = societyId;
	}

	public String getEventCoordinator()
	{
		return eventCoordinator;
	}

	public void setEventCoordinator(String eventCoordinator)
	{
		this.eventCoordinator = eventCoordinator;
	}

	public String getEventLocation()
	{
		return eventLocation;
	}

	public void setEventLocation(String eventLocation)
	{
		this.eventLocation = eventLocation;
	}

	public Date getEventStartDate()
	{
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate)
	{
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate()
	{
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate)
	{
		this.eventEndDate = eventEndDate;
	}

	public Date getEventCreatedDate()
	{
		return eventCreatedDate;
	}

	public void setEventCreatedDate(Date eventCreatedDate)
	{
		this.eventCreatedDate = eventCreatedDate;
	}
}