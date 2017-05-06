package com.sanstha.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanstha.dao.MemberDAO;
import com.sanstha.dto.MemberDTO;
import com.sanstha.dto.MemberTypeDTO;
import com.sanstha.dto.TitleDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.MemberType;
import com.sanstha.model.Title;
import com.sanstha.model.UserDetails;
import com.sanstha.service.MemberService;
import com.sanstha.util.CommonUtil;
import com.sanstha.util.Constants;

@Service("memberService")
public class MemberServiceImpl implements MemberService
{
	@Autowired
	CommonUtil commonUtil;

	@Autowired
	MemberDAO memberDAO;

	private static final Logger log = Logger.getLogger(MemberServiceImpl.class);

	public List<MemberDTO> getMemberList(HttpServletRequest request,
			Integer societyId, Integer userId) throws ServiceException,
			PersistenceException
	{
		log.debug("MemberServiceImpl :: getMemberList");

		List<MemberDTO> memberDTOList = new ArrayList<MemberDTO>();

		try
		{
			List<UserDetails> userDetailsList = memberDAO.getMemberList(
					societyId, userId);

			for (UserDetails userDetails : userDetailsList)
			{
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setUserId(userDetails.getUserId());
				memberDTO.setFirstName(userDetails.getFirstName());
				memberDTO.setLastName(userDetails.getLastName());
				memberDTO.setSocietyId(userDetails.getSocietyId());
				String profilePictureName = null;
				if (null != userDetails.getProfilePictureName()
						&& "default.png".equals(userDetails
								.getProfilePictureName()))
				{
					profilePictureName = "resources/images/"
							+ Constants.DEFAULT_PROFILE_IMAGE;
				}
				else
				{
					profilePictureName = request.getContextPath() + "/images/"
							+ userDetails.getProfilePictureName();
				}
				memberDTO.setProfilePictureName(profilePictureName);
				memberDTOList.add(memberDTO);
			}
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
		}
		return memberDTOList;
	}

	@Override
	public boolean createMember(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		try
		{
			String titleFirstName = request.getParameter("titleFirstName") == null ? ""
					: request.getParameter("titleFirstName");
			String firstName = request.getParameter("firstName") == null ? ""
					: request.getParameter("firstName");
			String lastName = request.getParameter("lastName") == null ? ""
					: request.getParameter("lastName");
			String titleFathersFirstName = request
					.getParameter("titleFathersFirstName") == null ? ""
					: request.getParameter("titleFathersFirstName");
			String fathersFirstName = request.getParameter("fathersFirstName") == null ? ""
					: request.getParameter("fathersFirstName");
			String fathersLastName = request.getParameter("fathersLastName") == null ? ""
					: request.getParameter("fathersLastName");
			String emailId = request.getParameter("emailId") == null ? ""
					: request.getParameter("emailId");
			String dateOfBirth = request.getParameter("dateOfBirth") == null ? ""
					: request.getParameter("dateOfBirth");
			boolean gender = request.getParameter("gender") == null ? true
					: request.getParameter("gender").equals("0") ? true : false;
			String memberType = request.getParameter("memberType") == null ? ""
					: request.getParameter("memberType");
			String localBuildingNumber = request
					.getParameter("localBuildingNumber") == null ? "" : request
					.getParameter("localBuildingNumber");
			String localLocation = request.getParameter("localLocation") == null ? ""
					: request.getParameter("localLocation");
			String localCity = request.getParameter("localCity") == null ? ""
					: request.getParameter("localCity");
			String localState = request.getParameter("localState") == null ? ""
					: request.getParameter("localState");
			String localPincode = request.getParameter("localPincode") == null ? ""
					: request.getParameter("localPincode");
			String localPhoneNumber = request.getParameter("localPhoneNumber") == null ? ""
					: request.getParameter("localPhoneNumber");
			String officeBuildingNumber = request
					.getParameter("officeBuildingNumber") == null ? ""
					: request.getParameter("officeBuildingNumber");
			String officeLocation = request.getParameter("officeLocation") == null ? ""
					: request.getParameter("officeLocation");
			String officeCity = request.getParameter("officeCity") == null ? ""
					: request.getParameter("officeCity");
			String officeState = request.getParameter("officeState") == null ? ""
					: request.getParameter("officeState");
			String officePincode = request.getParameter("officePincode") == null ? ""
					: request.getParameter("officePincode");
			String officePhoneNumber = request
					.getParameter("officePhoneNumber") == null ? "" : request
					.getParameter("officePhoneNumber");
			String permanentBuildingNumber = request
					.getParameter("permanentBuildingNumber") == null ? ""
					: request.getParameter("permanentBuildingNumber");
			String permanentLocation = request
					.getParameter("permanentLocation") == null ? "" : request
					.getParameter("permanentLocation");
			String permanentCity = request.getParameter("permanentCity") == null ? ""
					: request.getParameter("permanentCity");
			String permanentState = request.getParameter("permanentState") == null ? ""
					: request.getParameter("permanentState");
			String permanentPincode = request.getParameter("permanentPincode") == null ? ""
					: request.getParameter("permanentPincode");
			String permanentPhoneNumber = request
					.getParameter("permanentPhoneNumber") == null ? ""
					: request.getParameter("permanentPhoneNumber");

			UserDTO userDTO = new UserDTO();
			HttpSession session = request.getSession(false);
			if (null != session && null != session.getAttribute("userDTO"))
			{
				userDTO.setSocietyId(((UserDTO) session.getAttribute("userDTO"))
						.getSocietyId());
				userDTO.setSocietyName(((UserDTO) session
						.getAttribute("userDTO")).getSocietyName());
				userDTO.setSocietyPlace(((UserDTO) session
						.getAttribute("userDTO")).getSocietyPlace());
				userDTO.setSocietyDistrict(((UserDTO) session
						.getAttribute("userDTO")).getSocietyDistrict());
			}

			userDTO.setTitleFirstName(titleFirstName);
			userDTO.setFirstName(firstName);
			userDTO.setLastName(lastName);
			userDTO.setTitleFathersFirstName(titleFathersFirstName);
			userDTO.setFathersFirstName(fathersFirstName);
			userDTO.setFathersLastName(fathersLastName);
			userDTO.setEmailId(emailId);
			userDTO.setDateOfBirth(commonUtil.convertStringToDate(dateOfBirth));
			userDTO.setGender(gender);
			userDTO.setMemberType(memberType);
			userDTO.setLocalBuildingNumber(localBuildingNumber);
			userDTO.setLocalLocation(localLocation);
			userDTO.setLocalCity(localCity);
			userDTO.setLocalState(localState);
			userDTO.setLocalPincode(localPincode);
			userDTO.setLocalPhoneNumber(localPhoneNumber);
			userDTO.setOfficeBuildingNumber(officeBuildingNumber);
			userDTO.setOfficeLocation(officeLocation);
			userDTO.setOfficeCity(officeCity);
			userDTO.setOfficeState(officeState);
			userDTO.setOfficePincode(officePincode);
			userDTO.setOfficePhoneNumber(officePhoneNumber);
			userDTO.setPermanentBuildingNumber(permanentBuildingNumber);
			userDTO.setPermanentLocation(permanentLocation);
			userDTO.setPermanentCity(permanentCity);
			userDTO.setPermanentState(permanentState);
			userDTO.setPermanentPincode(permanentPincode);
			userDTO.setPermanentPhoneNumber(permanentPhoneNumber);

			log.debug("Calling getUserCountforInitials()");
			
			int maxUser = memberDAO
					.getUserCountforInitials(firstName.charAt(0));

			log.debug("MAX User : " + maxUser);

			String numberString = convertToMemberShipNumber(maxUser,
					firstName.charAt(0));

			log.debug("numberString: " + numberString);
			log.debug("Society Name: " + userDTO.getSocietyName());
			log.debug("Place: " + userDTO.getSocietyPlace());
			log.debug("District: " + userDTO.getSocietyDistrict());
			log.debug("Member Type: " + memberType);
			log.debug("First Name: " + firstName);

			String membershipNumber = String.valueOf(
					userDTO.getSocietyName().charAt(0)).toUpperCase()
					+ (String.valueOf(userDTO.getSocietyPlace().charAt(0))
							.toUpperCase()
							+ "("
							+ userDTO.getSocietyDistrict()
							+ ")-" + (memberType + firstName.charAt(0) + numberString)
							.toUpperCase());

			log.debug("membershipNumber: " + membershipNumber);

			userDTO.setMembershipNumber(membershipNumber);

			return memberDAO.createMember(userDTO);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	private String convertToMemberShipNumber(int maxUser, char charAt)
	{
		maxUser = maxUser + 1;
		String tempString = String.valueOf(maxUser);
		String numberString = "";

		for (int i = 0; i < 5 - tempString.length(); i++)
		{
			numberString = numberString + "0";
		}
		return numberString + tempString;
	}

	public UserDTO myProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		UserDTO userDTO = null;

		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");
			userDTO = getProfile(request, userDTO.getUserId());
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return userDTO;
	}

	public UserDTO viewMember(HttpServletRequest request, Integer userId)
			throws ServiceException, PersistenceException
	{
		UserDTO userDTO = null;

		try
		{
			userDTO = getProfile(request, userId);
		}
		catch (PersistenceException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return userDTO;
	}

	public UserDTO getProfile(HttpServletRequest request, Integer userId)
			throws ServiceException, PersistenceException
	{
		UserDTO userDTO = null;
		UserDetails userDetails = null;

		try
		{
			if (null != userId)
			{
				userDetails = memberDAO.getProfile(userId);
			}

			if (null != userDetails)
			{
				userDTO = new UserDTO();
				userDTO.setUserId(userDetails.getUserId());
				userDTO.setTitleFirstName(userDetails.getTitleFirstName());
				userDTO.setFirstName(userDetails.getFirstName());
				userDTO.setLastName(userDetails.getLastName());
				userDTO.setTitleFathersFirstName(userDetails
						.getTitleFathersFirstName());
				userDTO.setFathersFirstName(userDetails.getFathersFirstName());
				userDTO.setFathersLastName(userDetails.getFathersLastName());
				userDTO.setDateOfBirth(userDetails.getDateOfBirth());
				userDTO.setGender(userDetails.getGender());
				userDTO.setLocalBuildingNumber(userDetails
						.getLocalBuildingNumber());
				userDTO.setLocalLocation(userDetails.getLocalLocation());
				userDTO.setLocalCity(userDetails.getLocalCity());
				userDTO.setLocalState(userDetails.getLocalState());
				userDTO.setLocalPincode(userDetails.getLocalPincode());
				userDTO.setLocalPhoneNumber(userDetails.getLocalPhoneNumber());
				userDTO.setOfficeBuildingNumber(userDetails
						.getOfficeBuildingNumber());
				userDTO.setOfficeLocation(userDetails.getOfficeLocation());
				userDTO.setOfficeCity(userDetails.getOfficeCity());
				userDTO.setOfficeState(userDetails.getOfficeState());
				userDTO.setOfficePincode(userDetails.getOfficePincode());
				userDTO.setOfficePhoneNumber(userDetails.getOfficePhoneNumber());
				userDTO.setPermanentBuildingNumber(userDetails
						.getPermanentBuildingNumber());
				userDTO.setPermanentLocation(userDetails.getPermanentLocation());
				userDTO.setPermanentCity(userDetails.getPermanentCity());
				userDTO.setPermanentState(userDetails.getPermanentState());
				userDTO.setPermanentPincode(userDetails.getPermanentPincode());
				userDTO.setPermanentPhoneNumber(userDetails
						.getPermanentPhoneNumber());
				// userDTO.setEmailId(user.getEmailId());
				userDTO.setAbout(userDetails.getAbout());
				userDTO.setHobbies(userDetails.getHobbies());
				userDTO.setSocietyId(userDetails.getSocietyId());
				userDTO.setMembershipNumber(userDetails.getMembershipNumber());

				String profilePictureName = null;
				if (null != userDetails.getProfilePictureName()
						&& "default.png".equals(userDetails
								.getProfilePictureName()))
				{
					profilePictureName = "resources/images/"
							+ Constants.DEFAULT_PROFILE_IMAGE;
				}
				else
				{
					profilePictureName = request.getContextPath() + "/images/"
							+ userDetails.getProfilePictureName();
				}
				userDTO.setProfilePictureName(profilePictureName);

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
		return userDTO;
	}

	@Override
	public List<TitleDTO> getTitleList() throws ServiceException,
			PersistenceException
	{
		log.debug("MemberServiceImpl :: getTitleList");

		List<TitleDTO> titleDTOList = new ArrayList<TitleDTO>();

		try
		{
			List<Title> titleList = memberDAO.getTitleList();

			for (Title title : titleList)
			{
				TitleDTO titleDTO = new TitleDTO();
				titleDTO.setId(title.getId());
				titleDTO.setName(title.getName());
				titleDTOList.add(titleDTO);
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

		return titleDTOList;
	}

	public List<MemberTypeDTO> getMemberTypeList() throws ServiceException,
			PersistenceException
	{
		log.debug("MemberServiceImpl :: getMemberTypeList");

		List<MemberTypeDTO> memberTypeDTOList = new ArrayList<MemberTypeDTO>();

		try
		{
			List<MemberType> MemberTypeList = memberDAO.getMemberTypeList();

			for (MemberType MemberType : MemberTypeList)
			{
				MemberTypeDTO memberTypeDTO = new MemberTypeDTO();
				memberTypeDTO.setId(MemberType.getId());
				memberTypeDTO.setType(MemberType.getType());
				memberTypeDTO.setAbbreviation(MemberType.getAbbreviation());
				memberTypeDTOList.add(memberTypeDTO);
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

		return memberTypeDTOList;
	}
}