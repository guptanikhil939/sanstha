package com.sanstha.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.EventDAO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;
import com.sanstha.service.impl.LoginServiceImpl;

@Transactional
@Repository("eventDAO")
public class EventDAOImpl implements EventDAO
{
	private static final Logger log = Logger.getLogger(EventDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public boolean createEvent(Event event) throws PersistenceException
	{
		log.debug("In Method createEvent()");
		
		try
		{
			getCurrentSession().save(event);
			return true;
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
	}

	@Override
	public Event viewEvent(String eventId) throws PersistenceException
	{
		Event event = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Event.class);
			cr.add(Restrictions.eq("eventId", Integer.parseInt(eventId)));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					event = (Event) iterator.next();
				}
			}
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return event;
	}

	public List<Event> getEventList(Integer societyId)
			throws PersistenceException
	{
		List<Event> event = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Event.class);
			cr.add(Restrictions.eq("societyId", societyId));
			cr.addOrder(Order.asc("eventStartDate"));
			event = (List<Event>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return event;
	}
}
