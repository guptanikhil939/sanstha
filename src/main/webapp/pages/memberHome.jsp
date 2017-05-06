<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sanstha.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<head>
<script src="resources/js/events.js"></script>
<script src="resources/js/adminHome.js"></script>
</head>
<body>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div class="container">
			<div class="col-md-8">
				<div
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<div class="pull-left">
						<img src='<c:out value="${userDTO.profilePictureName}" />'
							class="media-object"
							alt='<c:out value="${userDTO.firstName} ${userDTO.lastName}" />'
							width="150"> <a href="#" class="pull-right"><img
							src="resources/images/upload.png" height="20" width="20"
							data-toggle="modal" data-target="#updatePhoto"></a>
					</div>
					<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div class="media-body">
						<p>
						<h3 class="media-heading">
							<span id="displayName"> <c:out
									value="${userDTO.firstName} ${userDTO.lastName}" />
							</span> <a href="#"><img src="resources/images/pencil.png"
								height="20" width="20" data-toggle="modal"
								data-target="#updateInfo"></a>
						</h3>
						</p>
						<p>
							<c:choose>
								<c:when
									test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==2}">
							Admin of
							</c:when>
								<c:when
									test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==3}">
							Member Of
							</c:when>
							</c:choose>
														<c:choose>
								<c:when
									test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()!=1}">
							<a class="a-blue"
								href='/society/<c:out value="${userDTO.societyWebName}" />.htm'
								target="new"> <c:out value="${userDTO.societyName}" /></a>
								</c:when>
								</c:choose>
						</p>
						<p>
							<span id="displayPhoneNumber"> <c:out
									value="${userDTO.localPhoneNumber}" />
							</span>
						</p>


						<p>
							<c:out value="${userDTO.emailId}" />
						</p>
						<p class=" pull-right">
							<c:choose>
								<c:when
									test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==2}">
									<button class="btn btn-primary btn-sm" data-toggle="modal"
										data-target="#createEvent">Create Event</button>&nbsp;&nbsp;
								</c:when>
							</c:choose>
							<button class="btn btn-primary btn-sm" data-toggle="modal"
								data-target="#donate">Donate</button>

						</p>
						<p>&nbsp;</p>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">
						About Me <a href="#"><img src="resources/images/pencil.png"
							height="20" width="20" data-toggle="modal"
							data-target="#updateAbout"></a>
					</h3>
					<div>&nbsp;</div>
					<p>
						<c:if test="${userDTO.about.length() != 0 }">
							<span id="displayAbout"><c:out value="${userDTO.about}" /></span>
						</c:if>
					</p>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">
						Hobbies and Interests <a href="#"><img
							src="resources/images/pencil.png" height="20" width="20"
							data-toggle="modal" data-target="#updateHobbies"></a>
					</h3>
					<div>&nbsp;</div>
					<p>
						<c:if test="${userDTO.hobbies.length() != 0 }">
							<span id="displayHobbies"><c:out
									value="${userDTO.hobbies}" /></span>
						</c:if>
					</p>
				</div>
				<div class="row">&nbsp;</div>
			</div>
			<div class="col-md-4">
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Society Members</h3>
					<div>
						<form id="viewMember" method="post" action="viewProfile">
							<input type="hidden" id="memberId" name="memberId"
								class="form-control">
							<c:forEach items="${memberList}" var="member"
								varStatus="theCount">
								<c:if test="${theCount.count < 4 }">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewMemberId(${member.userId})"> <img
										src='<c:out value="${member.profilePictureName}" />'
										class="media-object" alt="Sample Image" width="100"> <c:out
											value="${member.firstName}" /><br> <c:out
											value="${member.lastName}" />
									</a>
									<div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;</div>
								</c:if>
							</c:forEach>
						</form>
						<p>
						<form id="viewAllMembers" method="post" action="viewAllMembers">
							<input type="hidden" id="societyId" name="societyId"
								class="form-control">
							<c:if test="${memberList.size() > 3 }">
								<div class="pull-right">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewAllMembers(${memberList[0].societyId})">View
										all</a>
								</div>
							</c:if>
						</form>
						<c:if test="${memberList.size() == 0 }">
							<p>No members to show.</p>
						</c:if>
						</p>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Transactions</h3>
					<div class="row">&nbsp;</div>
					<p>No transactions.</p>
				</div>
				<div class="row">&nbsp;</div>
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Events</h3>
					<div class="row">&nbsp;</div>
					<div>
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
						<form id="viewAllEvents" method="post" action="viewAllEvents">
							<input type="hidden" id="societyId" name="societyId"
								class="form-control">
							<c:if test="${eventList.size() >= 5 }">
								<div class="pull-right">
									<a href="#" class="pull-left a-blue" align="center"
										onclick="viewAllEvents(${eventList[0].societyId})">View
										all</a>
								</div>
							</c:if>
						</form>
						<c:if test="${eventList.size() == 0 }">
							<p>No event scheduled.</p>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="updateInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updateInfoForm" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<div class="col-lg-6">
											<input type="text" id="firstName" name="firstName"
												class="form-control" value="${userDTO.firstName}"
												placeholder="First Name &#42;">
										</div>
										<div class="col-lg-6">
											<input type="text" id="lastName" name="lastName"
												class="form-control" value="${userDTO.lastName}"
												placeholder="Last Name">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div>
										<div class="col-lg-6">
											<input type="text" id="phoneNumber" name="phoneNumber"
												class="form-control" value="${userDTO.localPhoneNumber}"
												placeholder="Phone Number &#42;">
										</div>
										<div class="col-lg-6">
											<a class="btn btn-success" onclick="saveInfo()" role="button">Save</a>
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successProfile">Profile
										Updated.</div>
									<div class="alert alert-danger" id="firstNameMessageNull"
										style="position: relative; text-align: left;">&#42;Please
										enter First Name</div>
									<div class="alert alert-danger" id="phoneNumberMessageNull"
										style="position: relative; text-align: left;">&#42;Please
										enter Phone number</div>
									<div class="alert alert-danger" id="phoneNumberMessageInvalid"
										style="position: relative; text-align: left;">&#42;Phone
										Number should be of 10 digits without any special character</div>
									<div class="alert alert-danger" id="failProfile">Oops!
										Unable to process your request at this moment. It looks like
										there is some problem with your network or your session has
										expired. Please logout and try again.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updatePhoto" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Upload Photo</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updatePhotoForm" method="post" action="updatePhoto"
						enctype="multipart/form-data">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 20px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div>
										<input type="file" name="fileUpload" id="fileUpload" />
									</div>
									<div class="row">&nbsp;</div>
									<div>
										<a class="btn btn-success" onclick="updatePhoto()"
											role="button">Upload</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successPhoto">Profile
										Picture Updated. Pelase hit refresh button on the browser to
										see updated image.</div>
									<div class="alert alert-danger" id="sizeTooLarge">Image
										dimensions are more than expected. Kindly resize your image to
										a max of 300*300 pixels and try again.</div>
									<div class="alert alert-danger" id="failPhoto">Oops!
										Unable to process your request. It looks like there is some
										problem with your network or your session has expired. Please
										logout and try again.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="donate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Donate</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<p>To donate, kindly follow steps mentioned below :</p>
					<ol>
						<li>Make a DD in favour of "sanstha"</li>
						<li>Write your name, phone number and address behind DD</li>
						<li>Send it at our Office Address "23/A High Street,
							Kakadpur, Bangalore - 121345"</li>
					</ol>
					<p>Once received we will send across your receipt in 7 working
						days and the same will be reflected in your "My Transactions"
						section.</p>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updateAbout" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">About Me</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updateInfoAbout" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div class="row">
										<input type="text" id="about" name="about"
											class="form-control" value="${userDTO.about}"
											placeholder="Enter few lines about yourself">
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">
										<a class="btn btn-success" onclick="saveAbout()" role="button">Save</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successAbout">About
										Me Updated.</div>
									<div class="alert alert-danger" id="failAbout">Oops!
										Unable to process your request. It looks like there is some
										problem with your network or your session has expired. Please
										logout and try again.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="updateHobbies" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 500px;">
			<div class="modal-content">
				<div class="modal-header">
					<div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">My Hobbies</h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<form id="updateInfoHobbies" method="post">
						<div class="list-group" style="width: 400px;">
							<div id="admin" class="row" style="padding-left: 30px;">
								<div class="row">&nbsp;</div>
								<div class="form-group">
									<div class="row">
										<input type="text" id="hobbies" name="hobbies"
											class="form-control" value="${userDTO.hobbies}"
											placeholder="Enter your hobbies">
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">
										<a class="btn btn-success" onclick="saveHobbies()"
											role="button">Save</a>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-success" id="successHobbies">Hobbies
										Updated.</div>
									<div class="alert alert-danger" id="failHobbies">Oops!
										Unable to process your request. It looks like there is some
										problem with your network or your session has expired. Please
										logout and try again.</div>
								</div>
								<div class="row">&nbsp;</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="createEvent.jsp"></jsp:include>
</body>
</html>