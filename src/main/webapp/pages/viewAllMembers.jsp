<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	
	function viewMember(memberId){
		$("#memberId").val(memberId);
		$("#viewMember").submit();
	}

	function home() {
		$("#loginForm").submit();
	}

	function viewAllEvents(societyId){
		$("#societyId").val(societyId);
		$("#viewAllEvents").submit();
	}

</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
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
				<div>
					<h3 class="media-heading">
						<c:out value="Members(${memberList.size()})" />
					</h3>
				</div>
				<div class="row">&nbsp;</div>
				<div>
					<form id="viewMember" method="post" action="viewMember">
						<input type="hidden" id="memberId" name="memberId"
							class="form-control">
						<c:forEach items="${memberList}" var="member" varStatus="theCount">
							<a href="#" onClick="viewMember(${member.userId})" class="pull-left a-blue" align="center"> <img
								src='<c:out value="${member.profilePictureName}" />'
								class="media-object" alt="Sample Image" width="100"> <c:out
									value="${member.firstName}" /><br> <c:out
									value="${member.lastName}" />
							</a>
							<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</c:forEach>
					</form>
					<c:if test="${memberList.size() == 0 }">
						<div class="pull-right">No members to show</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>