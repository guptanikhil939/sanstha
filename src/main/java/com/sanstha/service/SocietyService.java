package com.sanstha.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sanstha.dto.DistrictDTO;
import com.sanstha.dto.SocietyDTO;
import com.sanstha.dto.SocietyRequestDTO;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.Society;

public interface SocietyService
{
	public List<SocietyDTO> getSocietyList() throws ServiceException;

	public List<SocietyRequestDTO> getSocietyRequestList()
			throws ServiceException;

	public String authorize(HttpServletRequest request) throws ServiceException;

	public Society getSocietyDetails(String societyWebName)
			throws ServiceException;

	public Society getSocietyDetails(Integer societyId) throws ServiceException;

	public boolean saveSociety(Society society) throws ServiceException;

	public List<DistrictDTO> getDistrictList() throws ServiceException;
}