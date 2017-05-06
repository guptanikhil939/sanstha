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
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "society")
public class Society
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "society_id")
	private Integer societyId;
	
	@NotEmpty(message = "Please enter society name")
	@Size(max = 150, message = "Society name can be of 150 charaters max")
	@Column(name = "society_name")
	private String societyName;
	
	@NotEmpty(message = "Please enter society place")
	@Size(max = 50, message = "Society place can be of 50 charaters max")
	@Column(name = "society_place")
	private String societyPlace;
	
	@NotEmpty(message = "Please select district")
	@Size(max = 5, message = "District can be of 5 charaters max")
	@Column(name = "society_district")
	private String societyDistrict;
	
	@NotEmpty(message = "Please enter society email address")
    @Size(max = 150, message = "Society email can be of 150 charaters max")
    @Column(name = "society_web_address")
	private String societyWebAddress;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "society_about")
	private String societyAbout;
	
	@NotEmpty(message = "Please enter society page name")
	@Size(max = 25, message = "Society page name can be of 25 charaters max")
	@Column(name = "society_webname")
	private String societyWebName;
	
	@Column(name = "society_contact")
	private String societyContact;
	
	public String getSocietyWebName() {
		return societyWebName;
	}

	public void setSocietyWebName(String societyWebName) {
		this.societyWebName = societyWebName;
	}

	public String getSocietyAbout() {
		return societyAbout;
	}

	public void setSocietyAbout(String societyAbout) {
		this.societyAbout = societyAbout;
	}

	public String getSocietyContact() {
		return societyContact;
	}

	public void setSocietyContact(String societyContact) {
		this.societyContact = societyContact;
	}
	
	public Integer getSocietyId()
	{
		return societyId;
	}

	public void setSocietyId(Integer societyId)
	{
		this.societyId = societyId;
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

	public String getSocietyWebAddress()
	{
		return societyWebAddress;
	}

	public void setSocietyWebAddress(String societyWebAddress)
	{
		this.societyWebAddress = societyWebAddress;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
}