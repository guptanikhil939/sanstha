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

import com.sanstha.dao.LoginDAO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;
import com.sanstha.model.Society;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.service.impl.LoginServiceImpl;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO
{
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
	
	private static final Logger log = Logger.getLogger(LoginDAOImpl.class);

	public User loginUser(String email, String password) throws PersistenceException
	{
		User user = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("emailId", email));
			cr.add(Restrictions.eq("password", password));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					user = (User) iterator.next();
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
		
		return user;
	}

	public UserDetails getUserDetails(Integer userId) throws PersistenceException
	{
		log.debug("In Method getUserDetails");
		
		UserDetails userDetails = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(UserDetails.class);
			cr.add(Restrictions.eq("userId", userId));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					userDetails = (UserDetails) iterator.next();
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
		
		return userDetails;
	}

	public Society getSocietyDetails(Integer societyId) throws PersistenceException
	{
		Society society = new Society();
		
		try
		{
			Criteria cr = getCurrentSession().createCriteria(Society.class);
			cr.add(Restrictions.eq("societyId", societyId));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					society = (Society) iterator.next();
					return society;
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
		
		return society;
	}

	public List<Event> getEventList(Integer societyId) throws PersistenceException
	{
		List<Event> event = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Event.class);
			cr.add(Restrictions.eq("societyId", societyId));
			cr.addOrder(Order.asc("eventStartDate"));
			cr.setMaxResults(5);
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