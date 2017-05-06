package com.sanstha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.dto.EventDTO;
import com.sanstha.dto.MemberDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;

public interface LoginService
{
	public boolean loginAuth(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public UserDTO login(HttpServletRequest request, UserDTO userDTO)
			throws ServiceException, PersistenceException;

	public List<EventDTO> getEventList(HttpServletRequest request,
			Integer societyId) throws ServiceException, PersistenceException;
}
