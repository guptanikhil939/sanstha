package com.sanstha.dto;

import java.util.Date;

public class SocietyRequestDTO
{
	private Integer requestId;

	private String firstName;

	private String lastName;

	private String password;

	private String emailId;

	private String phoneNumber;

	private String societyName;
	
	private String societyPlace;
	
	private String societyDistrict;

	private Date requestDate;

	public Integer getRequestId()
	{
		return requestId;
	}

	public void setRequestId(Integer requestId)
	{
		this.requestId = requestId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getSocietyName()
	{
		return societyName;
	}

	public void setSocietyName(String societyName)
	{
		this.societyName = societyName;
	}
	
	public String getSocietyPlace()
	{
		return societyPlace;
	}

	public void setSocietyPlace(String societyPlace)
	{
		this.societyPlace = societyPlace;
	}

	public String getSocietyDistrict()
	{
		return societyDistrict;
	}

	public void setSocietyDistrict(String societyDistrict)
	{
		this.societyDistrict = societyDistrict;
	}

	public Date getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}
}