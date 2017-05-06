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
	firstName = $("#firstName").val();
	lastName = $("#lastName").val();
	fathersFirstName = $("#fathersFirstName").val();
	fathersLastName = $("#fathersLastName").val();
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
		$("#validationMessage").html("Please enter First Name.");
		$("#validationMessage").show();
		$("#firstName").focus();
		return false;
	} else {
		$("#validationMessage").hide();

		if (checkNull(localPhoneNumber)) {
			$("#validationMessage").html("Please enter Phone Number.");
			$("#validationMessage").show();
			$("#phoneNumber").focus();
			return false;
		} else {
			$("#validationMessage").hide();

			if (validatePhoneNumber(localPhoneNumber)) {
				$("#validationMessage")
						.html(
								"Phone Number should be of 10 digits without any special character.");
				$("#validationMessage").show();
				$("#phoneNumber").focus();
				return false;
			} else {
				$("#validationMessage").hide();
			}
		}
	}
	
	$("#loading").show();
	
	if (isValid) {
		$.ajax({
			url : 'createMember',
			type : 'POST',
			data : {
				firstName : firstName,
				lastName : lastName,
				fathersFirstName : fathersFirstName,
				fathersLastName : fathersLastName,
				dateOfBirth : dateOfBirth,
				localBuildingNumber : localBuildingNumber,
				localLocation : localLocation,
				localCity : localCity,
				localState : localState,
				localPincode : localPincode,
				localPhoneNumber: localPhoneNumber,
				officeBuildingNumber : officeBuildingNumber,
				officeLocation : officeLocation,
				officeCity : officeCity,
				officeState : officeState,
				officePincode : officePincode,
				officePhoneNumber: officePhoneNumber,
				permanentBuildingNumber : permanentBuildingNumber,
				permanentLocation : permanentLocation,
				permanentCity : permanentCity,
				permanentState : permanentState,
				permanentPincode : permanentPincode,
				permanentPhoneNumber: permanentPhoneNumber	
			},
			success : function(data) {
				if (data == "success") {
					$("#fail").hide();
					$("#loading").hide();
					$("#success").show();// data updated successfully
					$("#createButton").hide();
				} else {
					$("#loading").hide();
					$("#fail").html(data);
					$("#fail").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#loading").hide();
				$("#fail").html(data);
				$("#fail").show();
			}
		});
	}
}