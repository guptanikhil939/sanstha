package com.sanstha.dao;

import java.util.List;

import com.sanstha.dto.UserDTO;
import com.sanstha.exception.PersistenceException;
import com.sanstha.model.MemberType;
import com.sanstha.model.Title;
import com.sanstha.model.UserDetails;

public interface MemberDAO
{
	public List<UserDetails> getMemberList(Integer societyId, Integer userId)
			throws PersistenceException;

	public boolean createMember(UserDTO userDTO) throws PersistenceException;

	public UserDetails getProfile(Integer userId) throws PersistenceException;

	public List<Title> getTitleList() throws PersistenceException;

	public List<MemberType> getMemberTypeList() throws PersistenceException;

	public int getUserCountforInitials(char c) throws PersistenceException;
}