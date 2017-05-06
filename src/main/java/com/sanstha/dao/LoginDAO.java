package com.sanstha.dao;

import java.util.List;

import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;
import com.sanstha.model.Society;
import com.sanstha.model.User;
import com.sanstha.model.UserDetails;

public interface LoginDAO
{
	public User loginUser(String email, String password) throws PersistenceException;

	public UserDetails getUserDetails(Integer userId) throws PersistenceException;

	public Society getSocietyDetails(Integer societyId) throws PersistenceException;

	public List<Event> getEventList(Integer societyId) throws PersistenceException;

}
