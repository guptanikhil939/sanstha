$(document).ready(function() {
	$("#loginSuccessMessage").hide();
	$("#loginFailMessage").hide();
	$("#emailIdLoginMessageNull").hide();
	$("#emailIdLoginMessageInvalid").hide();
	$("#passwordLoginMessageNull").hide();

	$('#passwordLogin').bind("cut copy paste", function(e) {
		e.preventDefault();
	});
});

function checkNull(fieldName) {
	if (null == fieldName || "" == fieldName || fieldName.length < 1) {
		return true;
	}
}

function validateEmail(email) {
	// using regex pattern from -
	// http://www.w3resource.com/javascript/form/email-validation.php
	return !((/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email));
}

function validatePassword(password) {
	return !(password.length >= 8);
}

function validateLogin() {
	isValid = true;
	emailIdLogin = $("#emailIdLogin").val();
	passwordLogin = $("#passwordLogin").val();

	if (checkNull(emailIdLogin)) {
		$("#emailIdLoginMessageNull").show();
		$("#emailIdLogin").focus();
		return false;
	} else {
		$("#emailIdLoginMessageNull").hide();

		if (validateEmail(emailIdLogin)) {
			$("#emailIdLoginMessageInvalid").show();
			$("#emailIdLogin").focus();
			return false;
		} else {
			$("#emailIdLoginMessageInvalid").hide();

			if (checkNull(passwordLogin)) {
				$("#passwordLoginMessageNull").show();
				$("#passwordLogin").focus();
				return false;
			} else {
				$("#passwordLoginMessageNull").hide();
			}
		}
	}

	if (isValid) {
		// trying to login
		$('#loginButton').prop('disabled', true);
		$('#forgotPasswordButton').prop('disabled', true);
		$("#loginSuccessMessage").show();
		$("#loginSuccessMessage").html("Logging in...");
		
		$.ajax({
			url : 'loginAuth',
			type : 'POST',
			data : {
				emailIdLogin : emailIdLogin,
				passwordLogin : passwordLogin
			},
			success : function(data) {
				if (data == "success") {
					$("#loginSuccessMessage").html("Login Successful. Customizing your account...");// login successful
					$("#loginFailMessage").hide();
					$("#loginForm").submit();// submitting form
				} else {
					$("#loginFailMessage").show();
					$("#loginSuccessMessage").hide();// login successful
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#loginFailMessage").show();
				$("#loginSuccessMessage").hide();// login successful
			}
		});
		$("#emailIdMember").val("");
	}
}