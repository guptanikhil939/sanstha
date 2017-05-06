<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sanstha.dto.UserDTO"%>
<%@ page import="com.sanstha.util.Constants"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="/resources/bootstrap/css/bootstrap-datetimepicker.min.css"
	media="screen">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/bootstrap/js/moment-with-locales.js"></script>
<script src="/resources/bootstrap/js/bootstrap-datetimepicker.js"></script>
<style>
.a-blue {
	color: #157ab5;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function checkNull(fieldName) {
		if (null == fieldName || "" == fieldName || fieldName.length < 1) {
			return true;
		}
	}

	function viewEventId(eventId) {
		$("#eventId").val(eventId);
		$("#viewEvent").submit();
	}
</script>
</head>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<span class="navbar-brand">e-Sanstha</span>
		</div>
		<c:choose>
			<c:when
				test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==1}">

				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/home">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Society <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="authorizeSociety">Authorize Society</a></li>
								<li><a href="viewAllSocieties">View Society</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Payment <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="addPaymentPage">Add Payment</a></li>
								<li><a href="viewAllPayments">View Payment</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Messages <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="inbox">Inbox</a></li>
								<li><a href="sendMessage">Send</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-right" action="logout">
						<button class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#register">Logout</button>
					</form>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when
				test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==2}">

				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/home">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Members <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="newMember">Create Member</a></li>
								<li><a href="viewAllMembers">View Members</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Account <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="myProfile">My Profile</a></li>
								<li><a href="viewAllEvents">Events</a></li>
								<li><a href=viewPayments>Payments</a></li>
								<li><a href="editWebPortal">Edit Web Portal</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-right" action="logout">
						<button class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#register">Logout</button>
					</form>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when
				test="${sessionScope.userDTO!=null && sessionScope.userDTO.getRoleId()==3}">

				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/home">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Members <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="viewAllMembers">View Members</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Account <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="myProfile">My Profile</a></li>
								<li><a href="viewAllEvents">Events</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-right" action="logout">
						<button class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#register">Logout</button>
					</form>
				</div>
			</c:when>
		</c:choose>
	</div>
</div>