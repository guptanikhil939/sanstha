package com.sanstha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "society_request")
public class SocietyRequest
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_id")
	private Integer requestId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "society_name")
	private String societyName;
	
	@Column(name = "society_place")
	private String societyPlace;
	
	@Column(name = "society_district")
	private String societyDistrict;

	@Column(name = "request_date")
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