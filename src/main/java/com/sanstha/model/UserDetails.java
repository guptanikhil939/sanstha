package com.sanstha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails
{
	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "society_id")
	private Integer societyId;

	@Column(name = "title_first_name")
	private String titleFirstName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "title_fathers_first_name")
	private String titleFathersFirstName;

	@Column(name = "fathers_first_name")
	private String fathersFirstName;

	@Column(name = "fathers_last_name")
	private String fathersLastName;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "gender")
	private Boolean gender;

	@Column(name = "member_type")
	private String memberType;

	@Column(name = "local_building_number")
	private String localBuildingNumber;

	@Column(name = "local_location")
	private String localLocation;

	@Column(name = "local_city")
	private String localCity;

	@Column(name = "local_state")
	private String localState;

	@Column(name = "local_pincode")
	private String localPincode;

	@Column(name = "local_phone_number")
	private String localPhoneNumber;

	@Column(name = "office_building_number")
	private String officeBuildingNumber;

	@Column(name = "office_location")
	private String officeLocation;

	@Column(name = "office_city")
	private String officeCity;

	@Column(name = "office_state")
	private String officeState;

	@Column(name = "office_pincode")
	private String officePincode;

	@Column(name = "office_phone_number")
	private String officePhoneNumber;

	@Column(name = "permanent_building_number")
	private String permanentBuildingNumber;

	@Column(name = "permanent_location")
	private String permanentLocation;

	@Column(name = "permanent_city")
	private String permanentCity;

	@Column(name = "permanent_state")
	private String permanentState;

	@Column(name = "permanent_pincode")
	private String permanentPincode;

	@Column(name = "permanent_phone_number")
	private String permanentPhoneNumber;

	@Column(name = "about")
	private String about;

	@Column(name = "hobbies")
	private String hobbies;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "profile_picture_name")
	private String profilePictureName;

	@Column(name = "membership_number")
	private String membershipNumber;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public Integer getSocietyId()
	{
		return societyId;
	}

	public void setSocietyId(Integer societyId)
	{
		this.societyId = societyId;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date date)
	{
		this.dateOfBirth = date;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getTitleFirstName()
	{
		return titleFirstName;
	}

	public void setTitleFirstName(String titleFirstName)
	{
		this.titleFirstName = titleFirstName;
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

	public String getTitleFathersFirstName()
	{
		return titleFathersFirstName;
	}

	public void setTitleFathersFirstName(String titleFathersFirstName)
	{
		this.titleFathersFirstName = titleFathersFirstName;
	}

	public String getFathersFirstName()
	{
		return fathersFirstName;
	}

	public void setFathersFirstName(String fathersFirstName)
	{
		this.fathersFirstName = fathersFirstName;
	}

	public String getFathersLastName()
	{
		return fathersLastName;
	}

	public void setFathersLastName(String fathersLastName)
	{
		this.fathersLastName = fathersLastName;
	}

	public Boolean getGender()
	{
		return gender;
	}

	public void setGender(Boolean gender)
	{
		this.gender = gender;
	}

	public String getMemberType()
	{
		return memberType;
	}

	public void setMemberType(String memberType)
	{
		this.memberType = memberType;
	}

	public String getLocalBuildingNumber()
	{
		return localBuildingNumber;
	}

	public void setLocalBuildingNumber(String localBuildingNumber)
	{
		this.localBuildingNumber = localBuildingNumber;
	}

	public String getLocalLocation()
	{
		return localLocation;
	}

	public void setLocalLocation(String localLocation)
	{
		this.localLocation = localLocation;
	}

	public String getLocalCity()
	{
		return localCity;
	}

	public void setLocalCity(String localCity)
	{
		this.localCity = localCity;
	}

	public String getLocalState()
	{
		return localState;
	}

	public void setLocalState(String localState)
	{
		this.localState = localState;
	}

	public String getLocalPincode()
	{
		return localPincode;
	}

	public void setLocalPincode(String localPincode)
	{
		this.localPincode = localPincode;
	}

	public String getLocalPhoneNumber()
	{
		return localPhoneNumber;
	}

	public void setLocalPhoneNumber(String localPhoneNumber)
	{
		this.localPhoneNumber = localPhoneNumber;
	}

	public String getOfficeBuildingNumber()
	{
		return officeBuildingNumber;
	}

	public void setOfficeBuildingNumber(String officeBuildingNumber)
	{
		this.officeBuildingNumber = officeBuildingNumber;
	}

	public String getOfficeLocation()
	{
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation)
	{
		this.officeLocation = officeLocation;
	}

	public String getOfficeCity()
	{
		return officeCity;
	}

	public void setOfficeCity(String officeCity)
	{
		this.officeCity = officeCity;
	}

	public String getOfficeState()
	{
		return officeState;
	}

	public void setOfficeState(String officeState)
	{
		this.officeState = officeState;
	}

	public String getOfficePincode()
	{
		return officePincode;
	}

	public void setOfficePincode(String officePincode)
	{
		this.officePincode = officePincode;
	}

	public String getOfficePhoneNumber()
	{
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(String officePhoneNumber)
	{
		this.officePhoneNumber = officePhoneNumber;
	}

	public String getPermanentBuildingNumber()
	{
		return permanentBuildingNumber;
	}

	public void setPermanentBuildingNumber(String permanentBuildingNumber)
	{
		this.permanentBuildingNumber = permanentBuildingNumber;
	}

	public String getPermanentLocation()
	{
		return permanentLocation;
	}

	public void setPermanentLocation(String permanentLocation)
	{
		this.permanentLocation = permanentLocation;
	}

	public String getPermanentCity()
	{
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity)
	{
		this.permanentCity = permanentCity;
	}

	public String getPermanentState()
	{
		return permanentState;
	}

	public void setPermanentState(String permanentState)
	{
		this.permanentState = permanentState;
	}

	public String getPermanentPincode()
	{
		return permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode)
	{
		this.permanentPincode = permanentPincode;
	}

	public String getPermanentPhoneNumber()
	{
		return permanentPhoneNumber;
	}

	public void setPermanentPhoneNumber(String permanentPhoneNumber)
	{
		this.permanentPhoneNumber = permanentPhoneNumber;
	}

	public String getAbout()
	{
		return about;
	}

	public void setAbout(String about)
	{
		this.about = about;
	}

	public String getHobbies()
	{
		return hobbies;
	}

	public void setHobbies(String hobbies)
	{
		this.hobbies = hobbies;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public String getProfilePictureName()
	{
		return profilePictureName;
	}

	public void setProfilePictureName(String profilePictureName)
	{
		this.profilePictureName = profilePictureName;
	}

	public String getMembershipNumber()
	{
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber)
	{
		this.membershipNumber = membershipNumber;
	}
}