$(document).ready(function() {
	$("#loading").hide();
	$("#success").hide();
	$("#fail").hide();
	$("#viewPortalButton").hide();
	$("#validationMessage").hide();
	$("#firstName").focus();
});

function openPortal(webAddress) {
	if (null != webAddress && "" != webAddress) {
		url = document.URL;
		lastIndexSlash = url.lastIndexOf("/");
		siteName = url.substring(0, lastIndexSlash);
		finalURL = siteName + "/" + webAddress;
		$("#viewPortalButton").show();
		$("#viewPortalButton").attr('href', finalURL);
	} else {
		$("#viewPortalButton").hide();
	}
}

function checkNull(fieldName) {
	if (null == fieldName || "" == fieldName || fieldName.length < 1) {
		return true;
	}
}

function validatePhoneNumber(phnNo) {
	return !((/^[0-9]*$/.test(phnNo)) && phnNo.length == 10);
}

function validateEmail(email) {
	// using regex pattern from -
	// http://www.w3resource.com/javascript/form/email-validation.php
	return !((/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email));
}

function validate() {
	isValid = true;
	titleFirstName = $("#titleFirstName").val();
	firstName = $("#firstName").val();
	lastName = $("#lastName").val();
	titleFathersFirstName = $("#titleFathersFirstName").val();
	fathersFirstName = $("#fathersFirstName").val();
	fathersLastName = $("#fathersLastName").val();
	emailId = $("#emailId").val();
	memberType = $("#memberType").val();
	dateOfBirth = $("#dateOfBirth").val();
	localBuildingNumber = $("#localBuildingNumber").val();
	localLocation = $("#localLocation").val();
	localCity = $("#localCity").val();
	localState = $("#localState").val();
	localPincode = $("#localPincode").val();
	localPhoneNumber = $("#localPhoneNumber").val();
	officeBuildingNumber = $("#officeBuildingNumber").val();
	officeLocation = $("#officeLocation").val();
	officeCity = $("#officeCity").val();
	officeState = $("#officeState").val();
	officePincode = $("#officePincode").val();
	officePhoneNumber = $("#officePhoneNumber").val();
	permanentBuildingNumber = $("#permanentBuildingNumber").val();
	permanentLocation = $("#permanentLocation").val();
	permanentCity = $("#permanentCity").val();
	permanentState = $("#permanentState").val();
	permanentPincode = $("#permanentPincode").val();
	permanentPhoneNumber = $("#permanentPhoneNumber").val();

	if (checkNull(firstName)) {
		alert("Please enter First Name.");
		$("#firstName").focus();

		return false;
	} else {

		if (checkNull(emailId)) {
			alert("Please enter Email Id.");
			$("#emailId").focus();

			return false;
		} else {
			if (checkNull(memberType)) {
				alert("Please select Member Type.");
				$("#memberType").focus();

				return false;
			} else {
				if (checkNull(localPhoneNumber)) {
					alert("Please enter Phone Number.");
					$("#localPhoneNumber").focus();

					return false;
				} else {

					if (validatePhoneNumber(localPhoneNumber)) {
						alert("Phone Number should be of 10 digits without any special character.");
						$("#phoneNumber").focus();

						return false;
					}
				}
			}
		}
	}

	$("#loading").show();
	
	if (isValid) {
		$.ajax({
			url : 'createMember',
			type : 'POST',
			data : {
				titleFirstName : titleFirstName,
				firstName : firstName,
				lastName : lastName,
				titleFathersFirstName : titleFathersFirstName,
				fathersFirstName : fathersFirstName,
				fathersLastName : fathersLastName,
				emailId : emailId,
				memberType : memberType,
				dateOfBirth : dateOfBirth,
				localBuildingNumber : localBuildingNumber,
				localLocation : localLocation,
				localCity : localCity,
				localState : localState,
				localPincode : localPincode,
				localPhoneNumber : localPhoneNumber,
				officeBuildingNumber : officeBuildingNumber,
				officeLocation : officeLocation,
				officeCity : officeCity,
				officeState : officeState,
				officePincode : officePincode,
				officePhoneNumber : officePhoneNumber,
				permanentBuildingNumber : permanentBuildingNumber,
				permanentLocation : permanentLocation,
				permanentCity : permanentCity,
				permanentState : permanentState,
				permanentPincode : permanentPincode,
				permanentPhoneNumber : permanentPhoneNumber
			},
			success : function(data) {
				if (data == "success") {
					$("#fail").hide();
					$("#loading").hide();
					$("#success").show();// data updated successfully
					$("#firstName").focus();
				} else {
					$("#loading").hide();
					$("#fail").html("Unable to Create Member.");
					$("#fail").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#loading").hide();
				$("#fail").html("Unable to Create Member.");
				$("#fail").show();
			}
		});
	}
}