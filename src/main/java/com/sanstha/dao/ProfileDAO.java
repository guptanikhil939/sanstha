package com.sanstha.dao;

import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.Event;
import com.sanstha.model.UserDetails;
import com.sanstha.model.WebContent;

public interface ProfileDAO
{
	public boolean updateInfo(UserDTO userDTO) throws PersistenceException;

	public boolean updatePhoto(UserDTO userDTO) throws PersistenceException;

	public boolean updateAbout(UserDTO userDTO) throws PersistenceException;

	public boolean updateHobbies(UserDTO userDTO) throws PersistenceException;

	public boolean saveArticle(WebContent webContent)
			throws PersistenceException;

	public String getUserEmail(Integer memberId) throws PersistenceException;
}
