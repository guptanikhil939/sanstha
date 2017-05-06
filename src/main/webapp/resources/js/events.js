$(function() {
	$('#datetimepicker_start').datetimepicker({
		format : 'DD/MM/YYYY HH:mm'
	});
	$('#datetimepicker_end').datetimepicker({
		format : 'DD/MM/YYYY HH:mm'
	});
});

function saveEvent(societyId) {
	isValid = true;
	eventName = $("#eventName").val();
	eventLocation = $("#eventLocation").val();
	eventStartDate = $("#eventStartDate").val();
	eventEndDate = $("#eventEndDate").val();
	eventCoordinator = $("#eventCoordinator").val();

	if (checkNull(eventName)) {
		$("#eventNameNull").show();
		isValid = false;
	} else {
		$("#eventNameNull").hide();
	}

	if (checkNull(eventLocation)) {
		$("#eventLocationNull").show();
		isValid = false;
	} else {
		$("#eventLocationNull").hide();
	}

	if (checkNull(eventStartDate)) {
		$("#eventStartTimeNull").show();
		isValid = false;
	} else {
		$("#eventStartTimeNull").hide();
	}

	if (checkNull(eventEndDate)) {
		$("#eventEndTimeNull").show();
		isValid = false;
	} else {
		$("#eventEndTimeNull").hide();
	}

	if (checkNull(eventCoordinator)) {
		$("#eventCoordinatorNameNull").show();
		isValid = false;
	} else {
		$("#eventCoordinatorNameNull").hide();
	}

	if (isValid) {
		$.ajax({
			url : 'createEvent',
			type : 'POST',
			data : {

				eventName : eventName,
				eventLocation : eventLocation,
				societyId : societyId,
				eventStartDate : eventStartDate,
				eventEndDate : eventEndDate,
				eventCoordinator : eventCoordinator
			},
			success : function(data) {
				if (data == "success") {
					str = "<div>" + eventStartDate + "&nbsp;&nbsp;" + eventName
							+ "</div>";
					$("#viewEvent").append(str);
					$("#eventName").val("");
					$("#eventLocation").val("");
					$("#eventStartDate").val("");
					$("#eventEndDate").val("");
					$("#eventCoordinator").val("");
					$("#failEvent").hide();
					$("#successEvent").show();// profile updated successfully
					$("#successEvent").fadeOut(2000);
				} else {
					$("#failEvent").show();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$("#failEvent").show();
			}
		});
	}
}