package com.sanstha.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.SocietyDAO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.SocietyRequest;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.service.RegistarationService;
import com.sanstha.util.Constants;

@Transactional
@Service("registrationService")
public class RegistrationServiceImpl implements RegistarationService
{	
	@Autowired
	SocietyDAO societyDAO;
	
	private static final Logger log = Logger
			.getLogger(RegistrationServiceImpl.class);

	public String registerAdmin(HttpServletRequest request)
			throws ServiceException
	{
		log.debug("RegistrationServiceImpl :: registerUser(request)");

		String firstName = "";
		String lastName = "";
		String societyName = "";
		String societyPlace = "";
		String societyDistrict = "";
		String phoneNumber = "";
		String emailId = "";
		String password = "";
		String confirmPassword = "";
		boolean registeredAlready;

		try
		{

			Integer roleId = new Integer(Constants.ADMIN_ROLE);

			firstName = request.getParameter("firstNameAdmin") == null ? ""
					: request.getParameter("firstNameAdmin");
			lastName = request.getParameter("lastNameAdmin") == null ? ""
					: request.getParameter("lastNameAdmin");
			societyName = request.getParameter("societyNameAdmin") == null ? ""
					: request.getParameter("societyNameAdmin");
			societyPlace = request.getParameter("societyPlace") == null ? ""
					: request.getParameter("societyPlace");
			societyDistrict = request.getParameter("societyDistrict") == null ? ""
					: request.getParameter("societyDistrict");
			societyName = request.getParameter("societyNameAdmin") == null ? ""
					: request.getParameter("societyNameAdmin");
			phoneNumber = request.getParameter("phoneNumberAdmin") == null ? ""
					: request.getParameter("phoneNumberAdmin");
			emailId = request.getParameter("emailIdAdmin") == null ? ""
					: request.getParameter("emailIdAdmin");
			password = request.getParameter("passwordAdmin");
			confirmPassword = request.getParameter("confirmPasswordAdmin");

			if (null != password && null != confirmPassword
					&& !"".equals(password) && !"".equals(confirmPassword)
					&& password.equals(confirmPassword))
			{
				registeredAlready = societyDAO.requestedAlready(emailId);

				if (!registeredAlready)
				{
					SocietyRequest societyRequest = new SocietyRequest();

					societyRequest.setFirstName(firstName);
					societyRequest.setLastName(lastName);
					societyRequest.setSocietyName(societyName);
					societyRequest.setSocietyPlace(societyPlace);
					societyRequest.setSocietyDistrict(societyDistrict);
					societyRequest.setPhoneNumber(phoneNumber);
					societyRequest.setEmailId(emailId);
					societyRequest.setPassword(password);
					societyRequest.setRequestDate(new Date());

					boolean societyRequestSaved = societyDAO.saveSocietyRequest(societyRequest);

					if (societyRequestSaved == true)
					{
						return Constants.SUCCESS;
					}
					else
					{
						return Constants.ERROR_MESSAGE;
					}
				}
				else
				{
					return Constants.EMAIL_ID_REGISTERED;
				}
			}
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return Constants.ERROR_MESSAGE;
	}

	public String registerMember(HttpServletRequest request)
			throws ServiceException
	{
		log.debug("RegistrationServiceImpl :: registerUser(request)");

		String firstName = "";
		String lastName = "";
		String societyName = "";
		String societyId = "";
		String phoneNumber = "";
		String emailId = "";
		String password = "";
		String confirmPassword = "";
		boolean registeredAlready;

		try
		{

			Integer roleId = new Integer(Constants.MEMBER_ROLE);

			firstName = request.getParameter("firstNameMember") == null ? ""
					: request.getParameter("firstNameMember");
			lastName = request.getParameter("lastNameMember") == null ? ""
					: request.getParameter("lastNameMember");
			societyId = request.getParameter("societyId") == null ? ""
					: request.getParameter("societyId");
			phoneNumber = request.getParameter("phoneNumberMember") == null ? ""
					: request.getParameter("phoneNumberMember");
			emailId = request.getParameter("emailIdMember") == null ? ""
					: request.getParameter("emailIdMember");
			password = firstName;// Need to update Logic
			confirmPassword = firstName;

			if (null != password && null != confirmPassword
					&& !"".equals(password) && !"".equals(confirmPassword)
					&& password.equals(confirmPassword))
			{
				registeredAlready = societyDAO.registeredAlready(emailId);

				if (!registeredAlready)
				{
					User user = new User();
					UserDetails userDetails = new UserDetails();

					boolean userSaved = false;
					boolean userDetailsSaved = false;
					boolean societySaved = false;
					Integer userId = null;

					user.setEmailId(emailId);
					user.setStatus(true);
					user.setPassword(password);
					user.setRoleId(roleId);
					user.setCreatedDate(new Date());

					userSaved = societyDAO.saveUser(user);

					userId = societyDAO.getSocietyAdminUserId(emailId);
					userDetails.setSocietyId(Integer.parseInt(societyId));

					userDetails.setFirstName(firstName);
					userDetails.setLastName(lastName);
					userDetails.setLocalPhoneNumber(phoneNumber);
					userDetails.setUpdatedDate(new Date());
					userDetails
							.setProfilePictureName(Constants.DEFAULT_PROFILE_IMAGE);

					userDetailsSaved = societyDAO
							.saveUserDetails(userDetails);
					if (userSaved == true && userDetailsSaved == true
							&& societySaved == true)
					{
						return Constants.SUCCESS;
					}
					else
					{
						return Constants.ERROR_MESSAGE;
					}
				}
				else
				{
					return Constants.EMAIL_ID_REGISTERED;
				}
			}
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return Constants.ERROR_MESSAGE;
	}
}
