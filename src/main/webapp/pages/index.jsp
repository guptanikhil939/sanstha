<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css"
	media="screen">
<script src="resources/js/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 20%;
	margin: auto;
}
</style>
<script src="resources/js/login.js"></script>
<script src="resources/js/registerAdmin.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div>&nbsp;</div>

	<div class="jumbotron" style="height: 500px">

		<div class="row">
			<div class="col-sm-4">
				<button id="socityListButton" class="btn btn-primary btn-lg"
					data-toggle="modal" data-target="#registeredSocities">Registered
					Socities</button>
			</div>
			<div class="col-sm-4">
				<h1 class="text-center">e-Sanstha</h1>
				<p class="text-center">A portal for togetherness</p>
			</div>
			<div class="col-sm-4">
				<p class="text-right">
					<!-- Button trigger modal -->
					<button class="btn btn-primary btn-lg" data-toggle="modal"
						data-target="#register">Register Your Society Here</button>
					<button id="loginButton" class="btn btn-primary btn-lg"
						data-toggle="modal" data-target="#login">Login</button>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 text-center">
				<table align="center">
					&nbsp;
					<tr></tr>
				</table>
			</div>
			<div class="col-md-6 text-center">There are many cultural &
				social societies, which got created and are getting created by the
				group of people initially. Few persons get added into the same
				subsequently. But almost none of those societies is able to run as
				the organization in professional way. As a result, some of those
				could not survive due to various reasons, some are surviving
				somehow. Major reasons, in our opinion, are not to have the system
				in place to manage the activities like managing members master data,
				assets, finance & accounts, events and other activities. We at
				e-sanstha are trying to provide a standard and affordable solution
				to such societies in managing all these through the system.</div>
			<div class="col-md-3 text-center">
				<table align="center">
					&nbsp;
					<tr></tr>
				</table>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="registeredSocities" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 760px;">
				<div class="modal-content">
					<div class="modal-header">
						<div>
							<div>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="">Registered Socities</h4>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div>&nbsp;</div>
						<c:if test="${societyList.size() > 0 }">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Society Name</th>
									</tr>
								</thead>
								<c:forEach items="${societyList}" var="society">
									<tr id="row_${societyRequest.societytId}">
										<td width="40%"><a class="a-blue"
											href='<c:out value="/society/${society.societyWebName}" />.htm'
											target="new"><c:out value="${society.societyName}" /></a></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${societyList.size() == 0 }">
							<div>No Society Registered Yet.</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="register" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 560px;">
				<div class="modal-content">
					<div class="modal-header">
						<div>
							<div>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Registration</h4>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div></div>
						<form id="registerAdminForm" method="post" action="registration">
							<div class="list-group" style="width: 500px;">
								<a id="registerAdminButton" class="list-group-item active"
									href="#">
									<h4 class="list-group-item-heading">Society Admin</h4> <span
									class="badge">Register here to become admin of a Society</span>
								</a>
								<div id="admin" class="row" style="padding-left: 20px;">
									<div class="row">&nbsp;</div>
									<div class="form-group">
										<div>
											<div class="col-lg-6">
												<input type="text" id="firstNameAdmin" name="firstNameAdmin"
													class="form-control" placeholder="First Name &#42;">
												<input type="hidden" id="isAdmin" name="isAdmin"
													class="form-control">
											</div>
										</div>
										<div>
											<div class="col-lg-6">
												<input type="text" id="lastNameAdmin" name="lastNameAdmin"
													class="form-control" placeholder="Last Name">
											</div>
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="form-group">
										<div>
											<div class="col-lg-6">
												<input type="text" id="emailIdAdmin" name="emailIdAdmin"
													class="form-control" placeholder="Email Id &#42;">
											</div>
										</div>
										<div class="col-lg-6">
											<input type="text" id="phoneNumberAdmin"
												name="phoneNumberAdmin" class="form-control"
												placeholder="Phone Number &#42;">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="form-group">
										<div>
											<div class="col-lg-6">
												<input type="password" id="passwordAdmin"
													name="passwordAdmin" class="form-control"
													placeholder="Password(minimum 8 words) &#42;">
											</div>
										</div>
										<div class="col-lg-6">
											<input type="password" id="confirmPasswordAdmin"
												name="confirmPasswordAdmin" class="form-control"
												placeholder="Confirm Password &#42;">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="form-group">
										<div>
											<div class="col-lg-6">
												<input type="text" id="societyNameAdmin"
													name="societyNameAdmin" class="form-control"
													placeholder="Society Name &#42;">
											</div>
										</div>
										<div class="col-lg-6">
											<input type="text" id="societyPlace" name="societyPlace"
												class="form-control" placeholder="Place &#42;">
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="form-group">
										<div>
											<div class="col-lg-6">
												<select name="societyDistrict" id="societyDistrict"
													class="form-control">
													<option value="">District</option>
													<c:forEach items="${districtList}" var="district">
														<option value="${district.abbreviation}">${district.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-lg-6">
											<a class="btn btn-success" onclick="validateAdmin()"
												role="button">Register</a>
										</div>
									</div>
									<div class="row">&nbsp;</div>
									<div class="row">&nbsp;</div>
									<div class="alert alert-danger" id="validationAdminMessage"></div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="alert alert-success" id="loadingAdmin">Registering...</div>
								<div class="alert alert-danger" id="failAdmin"></div>
								<div class="alert alert-success" id="successAdmin">Registration
									Successful. A confirmation mail has been sent to your
									registered email Id.</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="login" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 400px;">
				<div class="modal-content">
					<div class="modal-header">
						<div>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Login</h4>
						</div>
					</div>
					<div class="modal-body">
						<form id="loginForm" method="post" action="home">
							<div class="list-group" style="width: 350px;">
								<a class="list-group-item active" href="#">
									<h4 class="list-group-item-heading">Login</h4> <span
									class="badge">Login if you are an admin/member of a
										Society</span>
								</a>
								<div id="admin">
									<div class="row">&nbsp;</div>
									<div>
										<input type="text" id="emailIdLogin" name="emailIdLog"
											class="form-control" placeholder="Email Id">
									</div>
									<div class="row">&nbsp;</div>
									<div>
										<input type="password" id="passwordLogin" name="passwordLogin"
											class="form-control" placeholder="Password">
									</div>
									<div class="row">&nbsp;</div>
									<div>
										<a class="btn btn-success" onclick="validateLogin()"
											role="button" id="loginButton">Login</a>&nbsp;&nbsp; <a
											class="btn btn-danger" role="button"
											id="forgotPasswordButton">Forgot Password</a>
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="alert alert-success" id="loginSuccessMessage"></div>
								<div class="alert alert-danger" id="loginFailMessage">Incorrect
									Email Id or Password.</div>
								<div class="alert alert-danger" id="emailIdLoginMessageNull">Please
									enter Email Id.</div>
								<div class="alert alert-danger" id="emailIdLoginMessageInvalid">Please
									enter a valid Email Id.</div>
								<div class="alert alert-danger" id="passwordLoginMessageNull">Please
									enter Password.</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="resources/images/picture_1.jpg" alt="Picture 1">
				</div>

				<div class="item">
					<img src="resources/images/picture_2.jpg" alt="Picture 2">
				</div>

				<div class="item">
					<img src="resources/images/picture_3.jpg" alt="Picture 3">
				</div>

				<div class="item">
					<img src="resources/images/picture_4.jpg" alt="Picture 4">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" style="background-image: none"
				href="#myCarousel" role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" style="background-image: none"
				href="#myCarousel" role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>