	$(document).ready(function() {
		hideErrorMessages();
	});

	function hideErrorMessages() {
		$("#firstNameMessageNull").hide();
		$("#phoneNumberMessageNull").hide();
		$("#phoneNumberMessageInvalid").hide();
		$("#successProfile").hide();
		$("#failProfile").hide();
		$("#successPhoto").hide();
		$("#sizeTooLarge").hide();
		$("#failPhoto").hide();
		$("#successAbout").hide();
		$("#failAbout").hide();
		$("#successHobbies").hide();
		$("#failHobbies").hide();
		$("#eventNameNull").hide();
		$("#eventLocationNull").hide();
		$("#eventCoordinatorNameNull").hide();
		$("#eventStartTimeNull").hide();
		$("#eventEndTimeNull").hide();
		$("#successEvent").hide();
		$("#failEvent").hide();
	}

	function checkNull(fieldName) {
		if (null == fieldName || "" == fieldName || fieldName.length < 1) {
			return true;
		}
	}

	function validatePhoneNumber(phnNo) {
		return !((/^[0-9]*$/.test(phnNo)) && phnNo.length == 10);
	}

	function saveInfo() {
		isValid = true;
		phoneNumber = $("#phoneNumber").val();
		firstName = $("#firstName").val();
		lastName = $("#lastName").val();

		if (checkNull(firstName)) {
			$("#firstNameMessageNull").show();
			isValid = false;
		} else {
			$("#firstNameMessageNull").hide();
		}

		if (checkNull(phoneNumber)) {
			$("#phoneNumberMessageNull").show();
			$("#phoneNumberMessageInvalid").hide();
			isValid = false;
		} else if (validatePhoneNumber(phoneNumber)) {
			$("#phoneNumberMessageNull").hide();
			$("#phoneNumberMessageInvalid").show();
			isValid = false;
		} else {
			$("#phoneNumberMessageNull").hide();
			$("#phoneNumberMessageInvalid").hide();
		}

		if (isValid) {
			$.ajax({
				url : 'updateProfile',
				type : 'POST',
				data : {
					phoneNumber : phoneNumber,
					firstName : firstName,
					lastName : lastName
				},
				success : function(data) {
					if (data == "success") {
						$("#failProfile").hide();
						$("#displayName").html(firstName + " " + lastName);
						$("#displayPhoneNumber").html(phoneNumber);
						$("#successProfile").show();//profile updated successfully
					} else {
						$("#failProfile").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failProfile").show();
				}
			});
		}
	}
	
	function updatePhoto()
	{
		//fileUpload = $("#fileUpload").val();
	
		//$("#updatePhotoForm").submit();
		var formData = new FormData();
		formData.append('fileUpload', $('input[type=file]')[0].files[0]);
		//formData.append('fileUpload', $('input[name=fileUploadForm]')[0].files[0]);
		    
		$.ajax({
			url : 'updatePhoto',
			type : 'POST',
			contentType : false,
			processData : false,
			data : formData,
			success : function(data) {
				if (data == "success") {
					$("#failPhoto").hide();
					$("#sizeTooLarge").hide();
					$("#successPhoto").show();//profile picture updated successfully
					$("#successPhoto").fadeOut(3000);
				} else if (data == "sizeTooLarge") {
					$("#success").hide();
					$("#failPhoto").hide();
					$("#sizeTooLarge").show();
				}
				else {
					$("#success").hide();
					$("#sizeTooLarge").hide();
					$("#failPhoto").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#success").hide();
				$("#sizeTooLarge").hide();
				$("#failPhoto").show();
			}
		});
	}

	function saveAbout() {
		about = $("#about").val();
		
		$.ajax({
			url : 'updateAbout',
			type : 'POST',
			data : {
				about : about
			},
			success : function(data) {
				if (data == "success") {
					$("#failAbout").hide();
					$("#displayAbout").html(about);
					$("#successAbout").show();//profile updated successfully
					$("#successAbout").fadeOut(2000);
				} else {
					$("#failAbout").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failAbout").show();
			}
		});
	}
	
	function saveHobbies() {
		hobbies = $("#hobbies").val();
		
		$.ajax({
			url : 'updateHobbies',
			type : 'POST',
			data : {
				hobbies : hobbies
			},
			success : function(data) {
				if (data == "success") {
					$("#failHobbies").hide();
					$("#displayHobbies").html(hobbies);//
					$("#successHobbies").show();//profile updated successfully
					$("#successHobbies").fadeOut(2000);
				} else {
					$("#failHobbies").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failHobbies").show();
			}
		});
	}
	
	function home() {
		$("#loginForm").submit();
	}

	function viewMemberId(memberId){
		$("#memberId").val(memberId);
		$("#viewMember").submit();
	}
	
	function viewAllMembers(societyId){
		$("#societyId").val(societyId);
		$("#viewAllMembers").submit();
	}
	
	function viewEventId(eventId){
		$("#eventId").val(eventId);
		$("#viewEvent").submit();
	}
	
	function viewAllEvents(societyId){
		$("#societyId").val(societyId);
		$("#viewAllEvents").submit();
	}
	
	function saveArticle(societyId) {
		article = $("#article").val();
		$("#societyId").val(societyId);

		$.ajax({
			url : 'saveArticle',
			type : 'POST',
			data : {
				article : article,
				societyId : societyId
			},
			success : function(data) {
				if (data == "success") {
					//show success dialog
				} else {
					//show failure dialog
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}