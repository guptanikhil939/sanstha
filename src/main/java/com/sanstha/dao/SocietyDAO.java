package com.sanstha.dao;

import java.util.List;

import com.sanstha.exception.PersistenceException;
import com.sanstha.exception.ServiceException;
import com.sanstha.model.District;
import com.sanstha.model.Society;
import com.sanstha.model.SocietyRequest;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;

public interface SocietyDAO
{
	public boolean saveUser(User user) throws PersistenceException;

	public boolean saveUserDetails(UserDetails userDetails)
			throws PersistenceException;

	public boolean saveSociety(Society society) throws PersistenceException;

	public Integer getSocietyId(String societyName) throws PersistenceException;

	public Integer getSocietyAdminUserId(String emailId)
			throws PersistenceException;

	public boolean requestedAlready(String emailId) throws PersistenceException;

	public boolean registeredAlready(String emailId)
			throws PersistenceException;

	public boolean saveSocietyRequest(SocietyRequest societyRequest)
			throws PersistenceException;

	public SocietyRequest getSocietyRequest(String emailId)
			throws PersistenceException;

	public boolean deleteSocietyRequest(SocietyRequest societyRequest)
			throws PersistenceException;

	public Society getSocietyDetails(String societyWebName)
			throws PersistenceException;

	public Society getSocietyDetails(Integer societyId)
			throws PersistenceException;

	public List<SocietyRequest> getSocietyRequestList()
			throws PersistenceException;

	public List<Society> getSocietyList() throws PersistenceException;

	public List<District> getDistrictList() throws PersistenceException;
}
