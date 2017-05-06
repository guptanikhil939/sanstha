$(document).ready(function() {
	$("#success").hide();
	$("#fail").hide();
});

function authorize(status, requestId, emailId) {
	$
			.ajax({
				url : 'authorize',
				type : 'POST',
				data : {

					status : status,
					emailId : emailId
				},
				success : function(data) {
					if (data == "success") {
						$("#success").show();// authorized successfully
						if (status == 1) {
							$("#success").html(
									"Request by Email Id '" + emailId
											+ "' is Approved.");
						} else {
							$("#success").html(
									"Request by Email Id '" + emailId
											+ "' is Rejected.");
						}
						$("#row_" + requestId).hide();
						$("#fail").hide();
					} else if (data == "failure") {
						$("#success").hide();
						$("#fail")
								.html(
										"Unable to process your request at the moment. Please try again Later.");
						$("#fail").show();
					} else {
						$("#success").hide();
						$("#fail").html(data);
						$("#fail").show();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#failEvent").show();
				}
			});
}