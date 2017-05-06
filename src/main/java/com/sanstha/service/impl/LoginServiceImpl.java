package com.sanstha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.LoginDAO;
import com.sanstha.dto.EventDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Event;
import com.sanstha.model.Society;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.service.LoginService;
import com.sanstha.util.Constants;

@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService
{
	@Autowired
	LoginDAO loginDAO;

	private static final Logger log = Logger.getLogger(LoginServiceImpl.class);

	public boolean loginAuth(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("LoginServiceImpl :: loginAuth");

		UserDTO userDTO = null;

		String email = request.getParameter("emailIdLogin");
		String password = request.getParameter("passwordLogin");
		
		try
		{
			HttpSession session = request.getSession();
			userDTO = new UserDTO();
			User user = loginDAO.loginUser(email, password);

			if (null != user)
			{
				userDTO.setUserId(user.getUserId());
				userDTO.setRoleId(user.getRoleId());
				userDTO.setEmailId(user.getEmailId());

				if (null != user.getRoleId() && (user.getRoleId().equals(Constants.ADMIN_ROLE) || user.getRoleId().equals(Constants.MEMBER_ROLE)))
				{
					UserDetails userDetails = loginDAO.getUserDetails(user.getUserId());
					
					Society society = loginDAO.getSocietyDetails(userDetails.getSocietyId());
					userDTO.setSocietyPlace(society.getSocietyPlace());
					userDTO.setSocietyDistrict(society.getSocietyDistrict());
					userDTO.setSocietyWebName(society.getSocietyWebName());
				}

				session.setAttribute("userDTO", userDTO);

				return true;
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

		return false;
	}

	public UserDTO login(HttpServletRequest request, UserDTO userDTO)
			throws ServiceException, PersistenceException
	{
		log.debug("LoginServiceImpl :: login");

		String profilePictureName = "";

		try
		{
			if (null != userDTO)
			{

				UserDetails userDetail = loginDAO.getUserDetails(userDTO
						.getUserId());
				if (null != userDetail)
				{
					userDTO.setFirstName(userDetail.getFirstName());
					userDTO.setLastName(userDetail.getLastName());
					userDTO.setSocietyId(userDetail.getSocietyId());
					userDTO.setLocalPhoneNumber(userDetail
							.getLocalPhoneNumber());
					userDTO.setAbout(userDetail.getAbout());
					userDTO.setHobbies(userDetail.getHobbies());

					if (null != userDetail.getProfilePictureName()
							&& "default.png".equals(userDetail
									.getProfilePictureName()))
					{
						profilePictureName = "resources/images/"
								+ Constants.DEFAULT_PROFILE_IMAGE;
					}
					else
					{
						profilePictureName = request.getContextPath()
								+ "/images/"
								+ userDetail.getProfilePictureName();
					}
					userDTO.setProfilePictureName(profilePictureName);

					Society society = loginDAO.getSocietyDetails(userDetail
							.getSocietyId());
					if (null != society)
					{
						userDTO.setSocietyName(society.getSocietyName());
						userDTO.setSocietyPlace(society.getSocietyPlace());
						userDTO.setSocietyDistrict(society.getSocietyDistrict());
						userDTO.setSocietyWebName(society.getSocietyWebName());
					}
				}
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

		return userDTO;
	}

	public List<EventDTO> getEventList(HttpServletRequest request,
			Integer societyId) throws ServiceException, PersistenceException
	{
		log.debug("LoginServiceImpl :: getEventList");

		List<EventDTO> eventDTOList = new ArrayList<EventDTO>();

		try
		{
			List<Event> eventList = loginDAO.getEventList(societyId);

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
