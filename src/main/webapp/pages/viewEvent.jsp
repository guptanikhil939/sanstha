<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap/css/hoverbox.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap/css/dialog_box.css" />
<script type="text/javascript"
	src="/resources/bootstrap/js/dialog_box.js"></script>
<script type="text/javascript">

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

	function activateButton() {
		$("#submit").prop('disabled', false);
	}

	function activateViewAllButton() {
		$("#imageviewall").show();
	}
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#upload").submit(function(e)
			{
		 e.preventDefault(); //Prevent Default action.
		$('#spinner').show();		 
			    var formObj = $(this);
			    var formURL = formObj.attr("action");
			    var formData = new FormData(this);
			    $.ajax({
			        url: formURL,
			    type: 'POST',
			        data:  formData,
			    mimeType:"multipart/form-data",
			    contentType: false,
			        cache: false,
			        processData:false,
			        async: false,
			    success: function(data, textStatus, jqXHR)
			    {
			    	$('#spinner').hide();
			    	$('#fileselect').val('');
			    	$("#submit").prop('disabled', true);
			    	if(data!='error'){
			    	javascript:showDialog('Success','Images uploaded successfully.','success',2);
			    	$('#imagediv').html(data);
			    	}
			    	else{
			    		javascript:showDialog('Error','Cannot upload image. Please try again later !','error',2);
				    	}
			    	var count = ($("#imagediv img").length)/2;
			    	if(count == 12){
			    		$('#imagesubmitbutton').show();
			    		}
			    	//alert("Data: " + data + "\nStatus: " + textStatus);
			    },
			     error: function(jqXHR, textStatus, errorThrown) 
			     {
			    	 $('#spinner').hide();
			    	 $('#fileselect').val('');
			    	 $("#submit").prop('disabled', true);
			    	 javascript:showDialog('Error','Cannot upload image. Please try again later !','error',2);
			    	 //alert("Status: " + textStatus+ "  "+jqXHR.responseText);
			     }       
			    });
			    return false; 
			}); 
	});

$(document).ready(function() {
	$("#imageviewall").click(function(e)
			{
		$('#spinner').show();		 
			    var formURL = "/viewEvent/viewgallery";
			    var value = $("#eventidgiver").val();
			    $.ajax({
			        url: formURL,
			    type: 'GET',
			        data:  ({eventid : value}),
			    success: function(data, textStatus, jqXHR)
			    {
			    	$('#spinner').hide();
			    	if(data!='error'){
			    	$('#imagediv').html(data);
			    	}
			    		$('#imagesubmitbutton').hide();
			    	//alert("Data: " + data + "\nStatus: " + textStatus);
			    },
			     error: function(jqXHR, textStatus, errorThrown) 
			     {
			    	 $('#spinner').hide();
			    	 //alert("Status: " + textStatus+ "  "+jqXHR.responseText);
			     }       
			    });
			    e.preventDefault(); //Prevent Default action. 
			}); 
	});

</script>
</head>
<body>
	<form id="loginForm" method="post" action="loginSuccess"></form>
	<form id="viewAllMembers" method="post" action="viewAllMembers">
		<input type="hidden" id="societyId" name="societyId"
			class="form-control">
	</form>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">

		<div class="container">
			<div class="col-md-4">
				<h3 class="media-heading">Event Details</h3>
				<div class="row">&nbsp;</div>
				Event Name :
				<c:out value="${event.eventName}" />
				<div class="row">&nbsp;</div>
				Event Location :
				<c:out value="${event.eventLocation}" />
				<div class="row">&nbsp;</div>
				Event Coordinator :
				<c:out value="${event.eventCoordinator}" />
				<div class="row">&nbsp;</div>
				Event Start Date :
				<fmt:formatDate value="${event.eventStartDate}"
					pattern="dd/MM/yyyy HH:mm" />

				<div class="row">&nbsp;</div>
				Event End Date :
				<fmt:formatDate value="${event.eventEndDate}"
					pattern="dd/MM/yyyy HH:mm" />

			</div>
			<c:choose>
				<c:when
					test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==2}">
					<div class="col-md-4">
						<form id="upload" action="/viewEvent/upload" method="POST"
							enctype="multipart/form-data">
							<div id="spinner" class="spinner"
								style="display: none; position: fixed; top: 50%; left: 50%; margin-left: -50px; margin-top: -70px; text-align: center; z-index: 1234; overflow: auto; width: 100px; height: 102px;">
								<img src="http://i.stack.imgur.com/FhHRx.gif" id="img" />
							</div>
							<fieldset>
								<legend class="media-heading" style="color: #317eac;">Upload
									Event Photos</legend>

								<input type="hidden" id="MAX_FILE_SIZE" name="MAX_FILE_SIZE"
									value="300000" /> <input type="hidden" name="eventobj"
									id="eventidgiver" value="${event.eventId}" />
								<div style="align: right">
									<label for="fileselect1"></label> <input class="button"
										accept="image/gif, image/jpeg, , image/png" type="file"
										id="fileselect" name="files" multiple="multiple"
										onchange="activateButton();" />
									<div id="filedrag">or drop files here</div>
								</div>
								<br />
								<div id="submitbutton" style="align: right">
									<button type="submit" class="button" id="submit"
										disabled="disabled">Upload Files</button>
								</div>

							</fieldset>

						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-4"></div>
				</c:otherwise>
			</c:choose>

			<div class="col-md-4">
				<h3 class="media-heading pull-right1">Other Events</h3>
				<div class="row">&nbsp;</div>
				<div>
					<form id="viewEvent" method="post" action="viewEvent">
						<input type="hidden" id="eventId" name="eventId"
							class="form-control">
						<c:forEach items="${eventList}" var="event" varStatus="theCount">
							<c:if test="${theCount.count < 10 }">
								<a href="#" class="pull-right a-blue"
									onclick="viewEventId(${event.eventId})"> <fmt:formatDate
										value="${event.eventStartDate}" pattern="dd/MM/yyyy HH:mm" />
									&nbsp;&nbsp;<c:out value="${event.eventName}" />
								</a>
							</c:if>
							<div class="row">&nbsp;</div>
						</c:forEach>
					</form>
					<form id="viewAllEvents" method="post" action="viewAllEvents">
						<input type="hidden" id="societyId" name="societyId"
							class="form-control">
						<c:if test="${eventList.size() >= 10 }">
							<div class="pull-right">
								<a href="#" class="pull-left a-blue" align="center"
									onclick="viewAllEvents(${eventList[0].societyId})"> <c:out
										value="See all(${eventList.size()})" /></a>
							</div>
						</c:if>
					</form>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="col-md-12">
				<h3 class="media-heading">Event Photos</h3>
				<div id="imagediv"
					style="width: 1200px; position: relative; padding-left: 80px;"
					class="row">
					<ul class="hoverbox">
						<c:forEach items="${imagePaths}" var="imageloc" begin="0" end="11">
							<li><a href="#"><img src="${imageloc}" alt="description" /><img
									src="${imageloc}" alt="description" class="preview" /></a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="row">&nbsp;</div>
				<c:choose>
					<c:when test="${fn:length(imagePaths) > 11}">
						<div id="imagesubmitbutton"
							style="position: relative; width: 750px; padding-left: 570px;">
							<button type="button" class="button" id="imageviewall"
								style="float: left;">View All</button>
						</div>
					</c:when>
					<c:otherwise>
						<div id="imagesubmitbutton"
							style="position: relative; width: 750px; padding-left: 570px; display: none;">
							<button type="button" class="button" id="imageviewall"
								style="float: left;">View All</button>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="row" style="position: relative">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
</html>