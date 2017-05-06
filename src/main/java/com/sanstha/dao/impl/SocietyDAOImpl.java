package com.sanstha.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanstha.dao.SocietyDAO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.District;
import com.sanstha.model.Society;
import com.sanstha.model.SocietyRequest;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.service.impl.SocietyServiceImpl;

@Repository("societyDAO")
public class SocietyDAOImpl implements SocietyDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger log = Logger
			.getLogger(SocietyServiceImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public boolean saveUser(User user) throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: saveUser");

		try
		{
			getCurrentSession().save(user);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return true;
	}

	public boolean saveUserDetails(UserDetails userDetails)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: saveUserDetails");

		try
		{
			getCurrentSession().save(userDetails);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return true;
	}

	public boolean saveSociety(Society society) throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: saveSociety");

		try
		{
			getCurrentSession().saveOrUpdate(society);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return true;
	}

	public Integer getSocietyId(String societyName) throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyId");

		Integer societyId = 0;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Society.class);
			cr.add(Restrictions.eq("societyName", societyName));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					Society society = (Society) iterator.next();
					return society.getSocietyId();
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
		return 0;
	}

	public Integer getSocietyAdminUserId(String emailId)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyAdminUserId");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("emailId", emailId));

			List result = cr.list();
			if (result.size() > 0)
			{
				for (Iterator iterator = result.iterator(); iterator.hasNext();)
				{
					User user = (User) iterator.next();
					return user.getUserId();
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
		return 0;
	}

	public boolean requestedAlready(String emailId) throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: requestedAlready");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(
					SocietyRequest.class);
			cr.add(Restrictions.eq("emailId", emailId));

			List result = cr.list();
			if (result.size() > 0)
			{
				return true;
			}
			else
			{
				Criteria cr1 = getCurrentSession().createCriteria(User.class);
				cr1.add(Restrictions.eq("emailId", emailId));

				List result1 = cr1.list();
				if (result1.size() > 0)
				{
					return true;
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
		return false;
	}

	public boolean registeredAlready(String emailId)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: registeredAlready");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("emailId", emailId));

			List result = cr.list();
			if (result.size() > 0)
			{
				return true;
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
		return false;
	}

	public boolean saveSocietyRequest(SocietyRequest societyRequest)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: saveSocietyRequest");

		try
		{
			getCurrentSession().save(societyRequest);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return true;
	}

	public SocietyRequest getSocietyRequest(String emailId)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyRequest");

		SocietyRequest societyRequest = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(
					SocietyRequest.class);
			cr.add(Restrictions.eq("emailId", emailId));

			List<SocietyRequest> result = (List<SocietyRequest>) cr.list();

			if (null != result && result.size() > 0)
			{
				societyRequest = result.get(0);
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

		return societyRequest;
	}

	public boolean deleteSocietyRequest(SocietyRequest societyRequest)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: deleteSocietyRequest");

		try
		{
			getCurrentSession().delete(societyRequest);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return true;
	}

	public Society getSocietyDetails(String societyWebName)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyDetails");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Society.class);
			cr.add(Restrictions.eq("societyWebName", societyWebName));

			List<Society> result = cr.list();
			if (!result.isEmpty())
				return (Society) result.get(0);
			else
				return null;
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

	public Society getSocietyDetails(Integer societyId)
			throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyDetails");

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Society.class);
			cr.add(Restrictions.eq("societyId", societyId));

			List<Society> result = cr.list();
			if (!result.isEmpty())
				return (Society) result.get(0);
			else
				return null;
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

	public List<SocietyRequest> getSocietyRequestList()
			throws PersistenceException
	{

		log.debug("SocietyDAOImpl :: getSocietyRequestList");

		List<SocietyRequest> societyRequestList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(
					SocietyRequest.class);
			societyRequestList = (List<SocietyRequest>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return societyRequestList;
	}

	public List<Society> getSocietyList() throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getSocietyList");

		List<Society> societyList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Society.class);
			societyList = (List<Society>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return societyList;
	}
	
	public List<District> getDistrictList() throws PersistenceException
	{
		log.debug("SocietyDAOImpl :: getDistrictList");

		List<District> districtList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(District.class);
			districtList = (List<District>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return districtList;
	}
}