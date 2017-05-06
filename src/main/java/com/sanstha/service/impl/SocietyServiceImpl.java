package com.sanstha.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanstha.dao.MemberDAO;
import com.sanstha.dao.SocietyDAO;
import com.sanstha.dto.DistrictDTO;
import com.sanstha.dto.SocietyDTO;
import com.sanstha.dto.SocietyRequestDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.District;
import com.sanstha.model.Society;
import com.sanstha.model.SocietyRequest;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;
import com.sanstha.service.SocietyService;
import com.sanstha.service.TemplateService;
import com.sanstha.util.Constants;

@Transactional
@Service("societyService")
public class SocietyServiceImpl implements SocietyService
{
	@Autowired
	MemberDAO memberDAO;

	@Autowired
	SocietyDAO societyDAO;

	@Autowired
	TemplateService templateService;

	private static final Logger log = Logger
			.getLogger(SocietyServiceImpl.class);

	public List<SocietyDTO> getSocietyList() throws ServiceException
	{
		log.debug("SocietyServiceImpl :: getSocietyList");

		List<SocietyDTO> societyDTOList = new ArrayList<SocietyDTO>();

		try
		{
			List<Society> societyList = societyDAO.getSocietyList();

			for (Society society : societyList)
			{
				SocietyDTO societyDTO = new SocietyDTO();
				societyDTO.setSocietyId(society.getSocietyId());
				societyDTO.setSocietyName(society.getSocietyName());
				societyDTO.setSocietyWebAddress(society.getSocietyWebAddress()
						+ Constants.WEB_ADDRESS_EXTENSION);
				societyDTO.setSocietyWebName(society.getSocietyWebName());
				societyDTOList.add(societyDTO);
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

		return societyDTOList;
	}

	public List<SocietyRequestDTO> getSocietyRequestList()
			throws ServiceException
	{
		log.debug("SocietyServiceImpl :: getSocietyList");

		List<SocietyRequestDTO> societyRequestDTOList = new ArrayList<SocietyRequestDTO>();

		try
		{
			List<SocietyRequest> societyRequestList = societyDAO
					.getSocietyRequestList();

			for (SocietyRequest societyRequest : societyRequestList)
			{
				SocietyRequestDTO societyRequestDTO = new SocietyRequestDTO();
				societyRequestDTO.setRequestId(societyRequest.getRequestId());
				societyRequestDTO.setEmailId(societyRequest.getEmailId());
				societyRequestDTO.setSocietyName(societyRequest
						.getSocietyName());
				societyRequestDTO.setSocietyPlace(societyRequest
						.getSocietyPlace());
				societyRequestDTO.setSocietyDistrict(societyRequest
						.getSocietyDistrict());
				societyRequestDTOList.add(societyRequestDTO);
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

		return societyRequestDTOList;
	}

	public String authorize(HttpServletRequest request) throws ServiceException
	{
		log.debug("SocietyServiceImpl :: registerSociety(request)");

		String message = Constants.SUCCESS;

		try
		{

			Integer roleId = new Integer(Constants.ADMIN_ROLE);

			String emailId = request.getParameter("emailId") == null ? ""
					: request.getParameter("emailId");
			String status = request.getParameter("status") == null ? ""
					: request.getParameter("status");

			SocietyRequest societyRequest = societyDAO
					.getSocietyRequest(emailId);

			if (null != emailId && !"".equals(emailId) && null != status
					&& !"".equals(status) && status.equals("1"))
			{
				boolean registeredAlready = societyDAO
						.registeredAlready(emailId);

				if (!registeredAlready && null != societyRequest)
				{
					SocietyRequestDTO societyRequestDTO = new SocietyRequestDTO();
					societyRequestDTO.setRequestId(societyRequest
							.getRequestId());
					societyRequestDTO.setFirstName(societyRequest
							.getFirstName());
					societyRequestDTO.setLastName(societyRequest.getLastName());
					societyRequestDTO.setPassword(societyRequest.getPassword());
					societyRequestDTO.setEmailId(societyRequest.getEmailId());
					societyRequestDTO.setSocietyName(societyRequest
							.getSocietyName());
					societyRequestDTO.setSocietyPlace(societyRequest
							.getSocietyPlace());
					societyRequestDTO.setSocietyDistrict(societyRequest
							.getSocietyDistrict());
					societyRequestDTO.setPhoneNumber(societyRequest
							.getPhoneNumber());

					User user = new User();
					UserDetails userDetails = new UserDetails();
					Society society = new Society();

					boolean userSaved = false;
					boolean userDetailsSaved = false;
					boolean societySaved = false;
					Integer societyIdNew = null;

					user.setEmailId(emailId);
					user.setStatus(true);
					user.setPassword(societyRequestDTO.getPassword());
					user.setRoleId(roleId);
					user.setCreatedDate(new Date());

					userSaved = societyDAO.saveUser(user);

					society.setSocietyName(societyRequestDTO.getSocietyName());
					society.setSocietyPlace(societyRequestDTO.getSocietyPlace());
					society.setSocietyDistrict(societyRequestDTO.getSocietyDistrict());
					String societyWebAddress = "";
					societyWebAddress = societyRequestDTO.getSocietyName()
							.replaceAll("[^a-zA-Z]", "");
					society.setSocietyWebAddress(societyWebAddress);
					society.setSocietyWebName(societyWebAddress.toLowerCase());
					society.setUserId(user.getUserId());

					societySaved = societyDAO.saveSociety(society);

					societyIdNew = societyDAO.getSocietyId(societyRequestDTO
							.getSocietyName());
					userDetails.setUserId(user.getUserId());
					userDetails.setSocietyId(societyIdNew);

					userDetails.setFirstName(societyRequestDTO.getFirstName());
					userDetails.setLastName(societyRequestDTO.getLastName());
					userDetails.setLocalPhoneNumber(societyRequestDTO
							.getPhoneNumber());
					userDetails.setUpdatedDate(new Date());
					userDetails.setUpdatedDate(new Date());
					userDetails
							.setProfilePictureName(Constants.DEFAULT_PROFILE_IMAGE);

					userDetailsSaved = societyDAO.saveUserDetails(userDetails);

					if (userSaved == true && userDetailsSaved == true
							&& societySaved == true)
					{
						message = Constants.SUCCESS;
					}
					else
					{
						message = Constants.ERROR_MESSAGE;
					}
				}
				else
				{
					message = Constants.EMAIL_ID_REGISTERED;
				}
			}

			if (null != societyRequest
					&& null != status
					&& !"".equals(status)
					&& (status.equals("0") || status.equals("1"))
					&& (message.equals(Constants.SUCCESS) || message
							.equals(Constants.EMAIL_ID_REGISTERED)))
			{
				boolean societyRequestDeleted = societyDAO
						.deleteSocietyRequest(societyRequest);

				message = Constants.SUCCESS;
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
		return message;
	}

	public Society getSocietyDetails(String societyWebName)
			throws ServiceException
	{

		try
		{
			return societyDAO.getSocietyDetails(societyWebName);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	public Society getSocietyDetails(Integer societyId) throws ServiceException
	{

		try
		{
			return societyDAO.getSocietyDetails(societyId);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	public boolean saveSociety(Society society) throws ServiceException
	{
		try
		{
			return societyDAO.saveSociety(society);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	@Override
	public List<DistrictDTO> getDistrictList() throws ServiceException
	{
		log.debug("SocietyServiceImpl :: getDistrictList");

		List<DistrictDTO> districtDTOList = new ArrayList<DistrictDTO>();

		try
		{
			List<District> districtList = societyDAO.getDistrictList();

			for (District district : districtList)
			{
				DistrictDTO districtDTO = new DistrictDTO();
				districtDTO.setName(district.getName());
				districtDTO.setAbbreviation(district.getAbbreviation());
				districtDTOList.add(districtDTO);
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

		return districtDTOList;
	}
}