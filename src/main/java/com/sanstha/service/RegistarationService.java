package com.sanstha.service;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.exception.ServiceException;

public interface RegistarationService
{
	public String registerAdmin(HttpServletRequest request) throws ServiceException;
	
	public String registerMember(HttpServletRequest request) throws ServiceException;
}
