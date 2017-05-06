package com.sanstha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.dto.MemberDTO;
import com.sanstha.dto.MemberTypeDTO;
import com.sanstha.dto.TitleDTO;
import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;

public interface MemberService
{
	public List<MemberDTO> getMemberList(HttpServletRequest request,
			Integer societyId, Integer userId) throws ServiceException,
			PersistenceException;

	public boolean createMember(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public UserDTO myProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public UserDTO viewMember(HttpServletRequest request, Integer userId)
			throws ServiceException, PersistenceException;

	public List<TitleDTO> getTitleList() throws ServiceException, PersistenceException;

	public List<MemberTypeDTO> getMemberTypeList() throws ServiceException, PersistenceException;
}