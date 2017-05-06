<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		hideErrorMessages();
	});

	function hideErrorMessages() {
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

	function home() {
		$("#loginForm").submit();
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
						$("#successEvent").show();//profile updated successfully
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
</script>
</head>
<body>
	<form id="loginForm" method="post" action="loginSuccess"></form>
	<form id="viewAllMembers" method="post" action="viewAllMembers">
		<input type="hidden" id="societyId" name="societyId"
			class="form-control">
	</form>
	<form id="viewAllEvents" method="post" action="viewAllEvents">
		<input type="hidden" id="societyId" name="societyId"
			class="form-control">
	</form>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div class="container">
			<div class="row">
				<h3 class="media-heading">Events</h3>
			</div>
			<div class="row">
				<c:choose>
					<c:when test="${userDTO.getRoleId()==2}">
						<div class="navbar-form navbar-right">
							<button class="btn btn-primary btn-sm" data-toggle="modal"
								data-target="#createEvent">Create Event</button>
					</c:when>
				</c:choose>
			</div>
			<div>&nbsp;</div>
			<div class="row">
				<form id="viewEvent" method="post" action="viewEvent">
					<input type="hidden" id="eventId" name="eventId"
						class="form-control">
					<c:forEach items="${eventList}" var="event" varStatus="theCount">
						<a href="#" class="pull-left a-blue" align="center"
							onclick="viewEventId(${event.eventId})"> <fmt:formatDate
								value="${event.eventStartDate}" pattern="dd/MM/yyyy HH:mm" />
							&nbsp;&nbsp;<c:out value="${event.eventName}" />
						</a>
						<div class="row">&nbsp;</div>
					</c:forEach>
				</form>
				<c:if test="${eventList.size() == 0 }">
					<div class="pull-left">No event scheduled.</div>
				</c:if>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="createEvent" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 700px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<h4 class="modal-title" id="myModalLabel">Create an Event</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div>
						<div class="text-danger" id="eventNameNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event Name</div>
						<div class="text-danger" id="eventLocationNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event location</div>
						<div class="text-danger" id="eventStartTimeNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event start Time</div>
						<div class="text-danger" id="eventEndTimeNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event end Time</div>
						<div class="text-danger" id="eventCoordinatorNameNull"
							style="position: relative; text-align: left;">&#42;Please
							enter Event Coordinator's name</div>
					</div>
					<form id="createEventForm" method="post">
						<div class="list-group" style="width: 600px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<div class="col-lg-6">
											<input type="text" id="eventName" name="eventName"
												class="form-control" placeholder="Event Name&#42;">
										</div>
										<div class="col-lg-6">
											<input type="text" id="eventLocation" name="eventLocation"
												class="form-control" placeholder="Event Location&#42;">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div>
										<div class="col-lg-6">
											<div class='input-group date' id='datetimepicker_start'>
												<input type='text' class="form-control" id="eventStartDate"
													name="eventStartDate" /> <span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class='input-group date' id='datetimepicker_end'>
											<input type='text' class="form-control" id="eventEndDate"
												name="eventEndDate" /> <span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div>
									<div class="col-lg-6">
										<input type="text" id="eventCoordinator"
											name="eventCoordinator" class="form-control"
											placeholder="Event Coordinator&#42;">
									</div>
									<div class="col-lg-6">
										<a class="btn btn-success"
											onclick="saveEvent(${userDTO.societyId})" role="button">Create
											Event</a>
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div class="alert alert-success" id="successEvent">Event
									Created</div>
								<div class="alert alert-danger" id="failEvent">Oops!
									Unable to create event at this moment. We will shortly restore
									our services for you.</div>
							</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>