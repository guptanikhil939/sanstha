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
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.MemberDAO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.MemberType;
import com.sanstha.model.Title;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.util.Constants;

@Transactional
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO
{	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger log = Logger.getLogger(MemberDAOImpl.class);

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public List<UserDetails> getMemberList(Integer societyId, Integer userId)
			throws PersistenceException
	{
		log.debug("MemberDAOImpl :: getMemberList");

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
		return userDetails;
	}

	public boolean createMember(UserDTO userDTO) throws PersistenceException
	{
		log.debug("MemberDAOImpl :: createMember");

		try
		{
			User user = new User();
			user.setEmailId(userDTO.getEmailId());
			user.setStatus(true);
			user.setRoleId(Constants.MEMBER_ROLE);
			user.setPassword(Constants.TEMP_PASSWORD);

			getCurrentSession().save(user);

			userDTO.setUserId(user.getUserId());

			return saveUserDetails(userDTO);
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
	}

	public boolean saveUserDetails(UserDTO userDTO) throws PersistenceException
	{
		log.debug("MemberDAOImpl :: saveUserDetails");

		try
		{
			UserDetails userDetail = new UserDetails();
			userDetail.setUserId(userDTO.getUserId());
			userDetail.setTitleFirstName(userDTO.getTitleFirstName());
			userDetail.setFirstName(userDTO.getFirstName());
			userDetail.setLastName(userDTO.getLastName());
			userDetail.setTitleFathersFirstName(userDTO
					.getTitleFathersFirstName());
			userDetail.setFathersFirstName(userDTO.getFathersFirstName());
			userDetail.setFathersLastName(userDTO.getFathersLastName());
			userDetail.setDateOfBirth(userDTO.getDateOfBirth());
			userDetail.setGender(userDTO.isGender());
			userDetail.setMemberType(userDTO.getMemberType());
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
			userDetail.setSocietyId(userDTO.getSocietyId());
			userDetail.setMembershipNumber(userDTO.getMembershipNumber());

			getCurrentSession().save(userDetail);
			
			return true;
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
	}

	public UserDetails getProfile(Integer userId) throws PersistenceException
	{
		log.debug("MemberDAOImpl :: getProfile");

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
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		return userDetails;
	}

	public List<Title> getTitleList() throws PersistenceException
	{
		log.debug("MemberDAOImpl :: getTitleList");

		List<Title> titleList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(Title.class);
			titleList = (List<Title>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return titleList;
	}

	public List<MemberType> getMemberTypeList() throws PersistenceException
	{
		log.debug("MemberDAOImpl :: getMemberTypeList");

		List<MemberType> memberTypeList = null;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(MemberType.class);
			memberTypeList = (List<MemberType>) cr.list();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return memberTypeList;
	}

	@Override
	public int getUserCountforInitials(char initials)
			throws PersistenceException
	{
		log.debug("MemberDAOImpl :: getUserCountforInitials");

		int count = 0;

		try
		{
			Criteria cr = getCurrentSession().createCriteria(UserDetails.class);
			cr.add(Restrictions.like("firstName", initials + "%"));
			List result = cr.list();
			count = result.size();
		}
		catch (HibernateException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}

		return count;
	}
}