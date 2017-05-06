<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<head>
<script src="resources/js/createMember.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dateOfBirthPicker').datetimepicker({
			format : 'DD/MM/YYYY HH:mm'
		});
	});
</script>
</head>
<body>
	<form id="viewAllMembers" method="post" action="viewAllMembers">
		<input type="hidden" id="societyId" name="societyId"
			class="form-control">
	</form>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<form id="updateProfileForm" method="post" action="">
		<div class="row col-lg-8 list-group col-lg-offset-2">
			<a id="registrationButton" class="list-group-item active" href="#">
				<h4 class="list-group-item-heading">Create Member</h4> <span
				class="badge">Enter details below to register a member</span>
			</a>
			<div id="member" class="row" style="padding-left: 20px;">
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<label>Personal Details</label>
					</div>
					<div class="col-lg-6">
						<label>&nbsp;</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6">
						<div class="col-sm-4">
							<select name="titleFirstName" id="titleFirstName"
								class="form-control">
								<option value="">Title</option>
								<c:forEach items="${titleList}" var="title">
									<option value="${title.id}">${title.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-8">
							<input type="text" id="firstName" class="form-control"
								name="firstName" placeholder="First Name &#42;">
						</div>
					</div>
					<div class="col-lg-6">
						<input type="text" id="lastName" class="form-control"
							name="lastName" placeholder="Last Name">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div>
						<div class="col-lg-6">
							<div class="col-sm-4">
								<select name="titleFathersFirstName" id="titleFathersFirstName"
									class="form-control">
									<option value="">Title</option>
									<c:forEach items="${titleList}" var="title">
										<option value="${title.id}">${title.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-8">
								<input type="text" id="fathersFirstName" class="form-control"
									name="fathersFirstName"
									placeholder="Father's/Husband's First Name">
							</div>
						</div>
					</div>
					<div>
						<div class="col-lg-6">
							<input type="text" id="fathersLastName" class="form-control"
								name="fathersLastName"
								placeholder="Father's/Husband's Last Name">
						</div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="emailId" class="form-control"
							name="emailId" placeholder="Email Id &#42;">
					</div>
					<div class="col-lg-6">
						<div class='input-group date' id='dateOfBirthPicker'>
							<input type='text' class="form-control" id="dateOfBirth"
								name="dateOfBirth" placeholder="Date Of Birth" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="col-lg-6"></div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<select name="gender" id="gender" class="form-control">
							<option value="0">Male</option>
							<option value="1">Female</option>
						</select>
					</div>
					<div class="col-lg-6">
						<select name="memberType" id="memberType" class="form-control">
							<option value="">Member Type</option>
							<c:forEach items="${memberTypeList}" var="memberType">
								<option value="${memberType.abbreviation}">${memberType.type}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<label>Address-Residence/Local/Correspondence</label>
					</div>
					<div class="col-lg-6">
						<label>&nbsp;</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="localBuildingNumber" class="form-control"
							name="localBuildingNumber" placeholder="Address Line 1">
					</div>
					<div class="col-lg-6">
						<input type="text" id="localAddress Line 2" name="localLocation"
							class="form-control" placeholder="Address Line 2">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="localCity/District" name="localCity"
							class="form-control" placeholder="City/District">
					</div>
					<div class="col-lg-6">
						<input type="text" id="localState" class="form-control"
							name="localState" placeholder="State">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">

					<div class="col-lg-6">
						<input type="text" id="localPincode" name="localPincode"
							class="form-control" placeholder="PIN/Zip">
					</div>
					<div class="col-lg-6">
						<input type="text" id="localPhoneNumber" class="form-control"
							name="localPhoneNumber" placeholder="Phone Number &#42;">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<label>Address-Office</label>
					</div>
					<div class="col-lg-6">
						<label>&nbsp;</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="officeBuildingNumber" class="form-control"
							name="officeBuildingNumber" placeholder="Address Line 1">
					</div>
					<div class="col-lg-6">
						<input type="text" id="officeLocation" name="officeLocation"
							class="form-control" placeholder="Address Line 2">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="officeCity" name="officeCity"
							class="form-control" placeholder="City/District">
					</div>
					<div class="col-lg-6">
						<input type="text" id="officeState" class="form-control"
							name="officeState" placeholder="State">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="officePincode" name="officePincode"
							class="form-control" placeholder="PIN/Zip">
					</div>
					<div class="col-lg-6">
						<input type="text" id="officePhoneNumber" name="officePhoneNumber"
							class="form-control" placeholder="Phone Number">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<label>Address-Permanent/Native</label>
					</div>
					<div class="col-lg-6">
						<label>&nbsp;</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="permanentBuildingNumber"
							class="form-control" name="permanentBuildingNumber"
							placeholder="Address Line 1">
					</div>
					<div class="col-lg-6">
						<input type="text" id="permanentLocation" name="permanentLocation"
							class="form-control" placeholder="Address Line 2">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="permanentCity" name="permanentCity"
							class="form-control" placeholder="City/District">
					</div>
					<div class="col-lg-6">
						<input type="text" id="permanentState" class="form-control"
							name="permanentState" placeholder="State">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6">
						<input type="text" id="permanentPincode" name="permanentPincode"
							class="form-control" placeholder="PIN/Zip">
					</div>
					<div class="col-lg-6">
						<input type="text" id="permanentPhoneNumber"
							name="permanentPhoneNumber" class="form-control"
							placeholder="Phone Number">
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<div class="col-lg-6" id="createButton">
						<a class="btn btn-success" onclick="validate()" role="button">Create</a>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<div class="alert alert-danger" id="validationMessage"></div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="alert alert-success" id="loading">Creating...</div>
			<div class="alert-danger" id="fail"></div>
			<div class="alert alert-success" id="success">Member Created
				Successfully.</div>
		</div>
	</form>
</body>
</html>