$(document).ready(function() {
	$("#success").hide();
	$("#fail").hide();
});

$(function() {
	$('#paymentDateCalender').datetimepicker({
		format : 'DD/MM/YYYY HH:mm'
	});
});

function addPayment() {
	societyId = $("#societyId").val();
	from = $("#from").val();
	paymentDate = $("#paymentDate").val();
	amount = $("#amount").val();
	
	$
			.ajax({
				url : 'addPayment',
				type : 'POST',
				data : {
					societyId : societyId,
					from : from,
					paymentDate : paymentDate,
					amount : amount
				},
				success : function(data) {
					if (data == "success") {
						$("#success").show();
						$("#success").html("Payment Added in records.");
						$("#fail").hide();
						$("#societyId").val("");
						$("#from").val("");
						$("#paymentDate").val("");
						$("#amount").val("");
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
					$("#success").hide();
					$("#fail")
							.html(
									"Unable to process your request at the moment. Please try again Later.");
					$("#fail").show();
				}
			});
}