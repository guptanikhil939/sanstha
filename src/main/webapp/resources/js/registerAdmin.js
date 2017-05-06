$(document).ready(function() {
	$("#loadingAdmin").hide();
	$("#successAdmin").hide();
	$("#failAdmin").hide();
	$("#validationAdminMessage").hide();

	$('#passwordAdmin').bind("cut copy paste", function(e) {
		e.preventDefault();
	});

	$('#confirmPasswordAdmin').bind("cut copy paste", function(e) {
		e.preventDefault();
	});

	$("#registerAdminButton").click(function() {
		$("#firstNameAdmin").focus();
	});
});

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

function validatePassword(password) {
	return !(password.length >= 8);
}

function validateConfirmPassword(password, confirmPassword) {
	return !(password == confirmPassword);
}

function validateAdmin() {
	isValid = true;
	firstNameAdmin = $("#firstNameAdmin").val();
	lastNameAdmin = $("#lastNameAdmin").val();
	societyNameAdmin = $("#societyNameAdmin").val();
	societyPlace = $("#societyPlace").val();
	societyDistrict = $("#societyDistrict").val();
	phoneNumberAdmin = $("#phoneNumberAdmin").val();
	emailIdAdmin = $("#emailIdAdmin").val();
	passwordAdmin = $("#passwordAdmin").val();
	confirmPasswordAdmin = $("#confirmPasswordAdmin").val();

	if (checkNull(firstNameAdmin)) {
		$("#validationAdminMessage").html("Please enter First Name.");
		$("#validationAdminMessage").show();
		$("#firstNameAdmin").focus();
		return false;
	} else {
		$("#validationAdminMessage").hide();
		if (checkNull(emailIdAdmin)) {
			$("#validationAdminMessage").html("Please enter Email Id.");
			$("#validationAdminMessage").show();
			$("#emailIdAdmin").focus();
			return false;
		} else {
			$("#emailIdAdminMessageNull").hide();

			if (validateEmail(emailIdAdmin)) {
				$("#validationAdminMessage").html(
						"Please enter a valid Email Id.");
				$("#validationAdminMessage").show();
				$("#emailIdAdmin").focus();
				return false;
			} else {
				$("#emailIdAdminMessageInvalid").hide();

				if (checkNull(phoneNumberAdmin)) {
					$("#validationAdminMessage").html(
							"Please enter Phone Number.");
					$("#validationAdminMessage").show();
					$("#phoneNumberAdmin").focus();
					return false;
				} else {
					$("#validationAdminMessage").hide();

					if (validatePhoneNumber(phoneNumberAdmin)) {
						$("#validationAdminMessage")
								.html(
										"Phone Number should be of 10 digits without any special character.");
						$("#validationAdminMessage").show();
						$("#phoneNumberAdmin").focus();
						return false;
					} else {
						$("#validationAdminMessage").hide();

						if (checkNull(passwordAdmin)) {
							$("#validationAdminMessage").html(
									"Please enter password.");
							$("#validationAdminMessage").show();
							$("#passwordAdmin").focus();
							return false;
						} else {
							$("#validationAdminMessage").hide();

							if (validatePassword(passwordAdmin)) {
								$("#validationAdminMessage")
										.html(
												"Password should be minimum 8 letters.");
								$("#validationAdminMessage").show();
								$("#passwordAdmin").focus();
								return false;
							} else {
								$("#validationAdminMessage").hide();

								if (checkNull(confirmPasswordAdmin)) {
									$("#validationAdminMessage").html(
											"Please enter confirm password.");
									$("#validationAdminMessage").show();
									$("#confirmPasswordAdmin").focus();
									return false;
								} else {
									$("#validationAdminMessage").hide();

									if (validateConfirmPassword(passwordAdmin,
											confirmPasswordAdmin)) {
										$("#validationAdminMessage").html(
												"Password does not match.");
										$("#validationAdminMessage").show();
										$("#confirmPasswordAdmin").focus();
										return false;
									} else {
										$("#validationAdminMessage").hide();

										if (checkNull(societyNameAdmin)) {
											$("#validationAdminMessage")
													.html(
															"Please enter Society Name.");
											$("#validationAdminMessage").show();
											$("#societyNameAdmin").focus();
											return false;
										} else {
											$("#validationAdminMessage").hide();

											if (checkNull(societyPlace)) {
												$("#validationAdminMessage")
														.html(
																"Please enter Society Place.");
												$("#validationAdminMessage")
														.show();
												$("#societyPlace").focus();
												return false;
											} else {
												$("#validationAdminMessage")
														.hide();

												if (checkNull(societyDistrict)) {
													$("#validationAdminMessage")
															.html(
																	"Please select District.");
													$("#validationAdminMessage")
															.show();
													$("#societyDistrict")
															.focus();
													return false;
												} else {
													$("#validationAdminMessage")
															.hide();
												}
											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	if (isValid) {

		$("#loadingAdmin").show();

		$("#isAdmin").val("T");
		isAdmin = $("#isAdmin").val();

		$.ajax({
			url : 'registration',
			type : 'POST',
			data : {
				firstNameAdmin : firstNameAdmin,
				lastNameAdmin : lastNameAdmin,
				societyNameAdmin : societyNameAdmin,
				societyPlace : societyPlace,
				societyDistrict : societyDistrict,
				phoneNumberAdmin : phoneNumberAdmin,
				emailIdAdmin : emailIdAdmin,
				passwordAdmin : passwordAdmin,
				confirmPasswordAdmin : confirmPasswordAdmin,
				isAdmin : isAdmin
			},
			success : function(data) {
				if (data == "success") {
					$("#failAdmin").hide();
					$("#loadingAdmin").hide();
					$("#successAdmin").show();// registered successfully
					$("#firstNameAdmin").val("");
					$("#lastNameAdmin").val("");
					$("#societyNameAdmin").val("");
					$("#phoneNumberAdmin").val("");
					$("#emailIdAdmin").val("");
					$("#passwordAdmin").val("");
					$("#confirmPasswordAdmin").val("");
					$("#firstNameAdmin").focus();
				} else {
					$("#loadingAdmin").hide();
					$("#failAdmin").html(data);
					$("#failAdmin").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#loadingAdmin").hide();
				$("#failAdmin").html(data);
				$("#failAdmin").show();
			}
		});
	}
}