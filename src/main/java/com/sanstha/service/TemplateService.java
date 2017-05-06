package com.sanstha.service;

import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;


public interface TemplateService
{
	public boolean createTemplate(String societyName) throws ServiceException;
	
	public boolean updateTemplateContent(String content, String fileName) throws ServiceException;
	
	public String getTemplate(Society society) throws ServiceException;
	
	public String getTemplate(Society society, String pageName) throws ServiceException;
}
