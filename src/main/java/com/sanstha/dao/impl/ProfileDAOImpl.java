package com.sanstha.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.ProfileDAO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.model.WebContent;

@Transactional
@Repository("profileDAO")
public class ProfileDAOImpl implements ProfileDAO
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

	public boolean updateInfo(UserDTO userDTO) throws PersistenceException
	{
		try
		{
			UserDetails userDetail = (UserDetails) getCurrentSession().get(
					UserDetails.class, userDTO.getUserId());
			userDetail.setFirstName(userDTO.getFirstName());
			userDetail.setLastName(userDTO.getLastName());
			userDetail.setFathersFirstName(userDTO.getFathersFirstName());
			userDetail.setFathersLastName(userDTO.getFathersLastName());
			userDetail.setLocalBuildingNumber(userDTO.getLocalBuildingNumber());
			userDetail.setLocalLocation(userDTO.getLocalLocation());
			userDetail.setLocalCity(userDTO.getLocalCity());
			userDetail.setLocalState(userDTO.getLocalState());
			userDetail.setLocalPincode(userDTO.getLocalPincode());
			userDetail.setLocalPhoneNumber(userDTO.getLocalPhoneNumber());
			userDetail.setOfficeBuildingNumber(userDTO
					.getOfficeBuildingNumber());
			userDetail.setOfficeLocation(userDTO.getOfficeLocation());
			userDetail.setOfficeCity(userDTO.getOfficeCity());
			userDetail.setOfficeState(userDTO.getOfficeState());
			userDetail.setOfficePincode(userDTO.getOfficePincode());
			userDetail.setOfficePhoneNumber(userDTO.getOfficePhoneNumber());
			userDetail.setPermanentBuildingNumber(userDTO
					.getPermanentBuildingNumber());
			userDetail.setPermanentLocation(userDTO.getPermanentLocation());
			userDetail.setPermanentCity(userDTO.getPermanentCity());
			userDetail.setPermanentState(userDTO.getPermanentState());
			userDetail.setPermanentPincode(userDTO.getPermanentPincode());
			userDetail.setPermanentPhoneNumber(userDTO
					.getPermanentPhoneNumber());

			getCurrentSession().update(userDetail);

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

	public boolean updatePhoto(UserDTO userDTO) throws PersistenceException
	{
		try
		{
			UserDetails userDetail = (UserDetails) getCurrentSession().load(
					UserDetails.class, userDTO.getUserId());
			userDetail.setProfilePictureName(userDTO.getProfilePictureName());
			getCurrentSession().update(userDetail);

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

	public boolean updateAbout(UserDTO userDTO) throws PersistenceException
	{
		try
		{
			UserDetails userDetail = (UserDetails) getCurrentSession().load(
					UserDetails.class, userDTO.getUserId());
			userDetail.setAbout(userDTO.getAbout());
			getCurrentSession().update(userDetail);

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

	public boolean updateHobbies(UserDTO userDTO) throws PersistenceException
	{
		try
		{
			UserDetails userDetail = (UserDetails) getCurrentSession().load(
					UserDetails.class, userDTO.getUserId());
			userDetail.setHobbies(userDTO.getHobbies());
			getCurrentSession().update(userDetail);

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

	public boolean saveArticle(WebContent webcontent) throws PersistenceException
	{
		try
		{
			getCurrentSession().save(webcontent);
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

	public String getUserEmail(Integer memberId) throws PersistenceException
	{
		User user = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(User.class);
			cr.add(Restrictions.eq("userId", memberId));

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
		return user.getEmailId();
	}

	public List<UserDetails> getMemberList(Integer societyId, Integer userId) throws PersistenceException
	{
		List<UserDetails> userDetails = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(UserDetails.class);
			cr.add(Restrictions.eq("societyId", societyId));
			cr.add(Restrictions.ne("userId", userId));
			userDetails = (List<UserDetails>) cr.list();

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
}
