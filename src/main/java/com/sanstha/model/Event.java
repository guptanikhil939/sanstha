package com.sanstha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "society_id")
	private Integer societyId;

	@Column(name = "event_coordinator")
	private String eventCoordinator;

	@Column(name = "event_location")
	private String eventLocation;

	@Column(name = "event_start_date")
	private Date eventStartDate;

	@Column(name = "event_end_date")
	private Date eventEndDate;

	@Column(name = "event_created_date")
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