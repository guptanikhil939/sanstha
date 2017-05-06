package com.sanstha.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;

public interface ProfileService
{
	public boolean updateInfo(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public boolean updatePhoto(HttpServletRequest request,
			MultipartFile fileUpload) throws ServiceException,
			PersistenceException;

	public boolean updateAbout(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public boolean updateHobbies(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public boolean saveArticle(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public UserDTO myProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException;
}