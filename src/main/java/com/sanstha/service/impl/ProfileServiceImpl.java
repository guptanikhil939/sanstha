package com.sanstha.service.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanstha.dao.ProfileDAO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Event;
import com.sanstha.model.UserDetails;
import com.sanstha.model.WebContent;
import com.sanstha.service.MemberService;
import com.sanstha.service.ProfileService;
import com.sanstha.util.Constants;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService
{
	@Autowired
	ProfileDAO profileDAO;

	@Autowired
	MemberService memberService;

	public UserDTO myProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		UserDTO userDTO = null;

		try
		{
			userDTO = memberService.myProfile(request);
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

	public boolean updateInfo(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		try
		{
			String firstName = request.getParameter("firstName") == null ? ""
					: request.getParameter("firstName");
			String lastName = request.getParameter("lastName") == null ? ""
					: request.getParameter("lastName");
			String fathersFirstName = request.getParameter("fathersFirstName") == null ? ""
					: request.getParameter("fathersFirstName");
			String fathersLastName = request.getParameter("fathersLastName") == null ? ""
					: request.getParameter("fathersLastName");
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
				userDTO.setUserId(((UserDTO) session.getAttribute("userDTO"))
						.getUserId());
			}

			userDTO.setFirstName(firstName);
			userDTO.setLastName(lastName);
			userDTO.setFathersFirstName(fathersFirstName);
			userDTO.setFathersLastName(fathersLastName);
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

			return profileDAO.updateInfo(userDTO);
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

	public boolean updatePhoto(HttpServletRequest request,
			MultipartFile fileUpload) throws ServiceException,
			PersistenceException
	{
		HttpSession session = request.getSession(false);
		UserDTO userDTO = null;

		if (null != session && null != session.getAttribute("userDTO"))
		{
			userDTO = (UserDTO) session.getAttribute("userDTO");
		}

		String extension = fileUpload.getOriginalFilename().substring(
				fileUpload.getOriginalFilename().lastIndexOf("."));
		String fileName = userDTO.getFirstName() + "_" + userDTO.getUserId()
				+ extension;

		Dimension dimension = null;

		try
		{
			ImageInputStream in = ImageIO.createImageInputStream(fileUpload
					.getInputStream());
			if (null != in)
			{
				final Iterator<ImageReader> readers = ImageIO
						.getImageReaders(in);
				if (readers.hasNext())
				{
					ImageReader reader = readers.next();
					try
					{
						reader.setInput(in);
						dimension = new Dimension(reader.getWidth(0),
								reader.getHeight(0));
					}
					finally
					{
						reader.dispose();
					}
				}
			}

			if (null != dimension && dimension.height > 300
					&& dimension.width > 300)
			{
				return false;
			}

			File file = new File(Constants.COMMON_LOCATION + fileName);
			FileUtils.writeByteArrayToFile(file, fileUpload.getBytes());

			BufferedImage originalImage = null;

			originalImage = ImageIO.read(new File(Constants.COMMON_LOCATION
					+ fileName));

			BufferedImage croppedImage = originalImage.getSubimage(5, 5, 5, 5);
			ImageIO.write(croppedImage, "png", new File(
					Constants.COMMON_LOCATION + "_crop" + fileName));

			userDTO.setProfilePictureName(fileName);
			profileDAO.updatePhoto(userDTO);

			return true;
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

	public boolean updateAbout(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String about = request.getParameter("about") == null ? "" : request
				.getParameter("about");

		UserDTO userDTO = new UserDTO();
		HttpSession session = request.getSession(false);
		if (null != session && null != session.getAttribute("userDTO"))
		{
			userDTO.setUserId(((UserDTO) session.getAttribute("userDTO"))
					.getUserId());
		}
		userDTO.setAbout(about);

		return profileDAO.updateAbout(userDTO);
	}

	public boolean updateHobbies(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String hobbies = request.getParameter("hobbies") == null ? "" : request
				.getParameter("hobbies");

		UserDTO userDTO = new UserDTO();
		HttpSession session = request.getSession(false);
		if (null != session && null != session.getAttribute("userDTO"))
		{
			userDTO.setUserId(((UserDTO) session.getAttribute("userDTO"))
					.getUserId());
		}
		userDTO.setHobbies(hobbies);

		return profileDAO.updateHobbies(userDTO);
	}

	private Date convertStringToDate(String dateString) throws ServiceException
	{
		Date date = null;
		String finlaString = dateString + ":00";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		try
		{
			date = sdf.parse(finlaString);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		return date;
	}

	public boolean saveArticle(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		try
		{
			String article = request.getParameter("article") == null ? ""
					: request.getParameter("article");
			String societyId = request.getParameter("societyId") == null ? ""
					: request.getParameter("societyId");

			WebContent webContent = new WebContent();
			webContent.setContent(article);
			webContent.setSocietyId(Integer.parseInt(societyId));

			return profileDAO.saveArticle(webContent);
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
}