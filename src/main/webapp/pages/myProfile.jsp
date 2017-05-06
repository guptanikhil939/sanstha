<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<head>
<script src="resources/js/myProfile.js"></script>
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
	<div class="row col-lg-8 list-group col-lg-offset-2">
		<a id="registrationButton" class="list-group-item active" href="#">
			<h4 class="list-group-item-heading">My Profile</h4> <span
			class="badge">Enter details below to update your information</span>
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
				<div>
					<div class="col-lg-6"><div class="col-sm-4 text-left"><select name="firstNameTitle" id="firstNameTitle" class="form-control text-left"><option>Shri</option>
								<option>Smt</option>
								<option>Dr Shri</option>
								<option>Dr Smt</option>
								<option>Prof Shri</option>
								<option>Prof Smt</option>
								<option>Ms</option>
								<option>Pt Shri</option>
								<option>Late</option></select></div><div class="col-sm-8">
						<input type="text" id="firstName" class="form-control"
							name="firstName" placeholder="First Name &#42;"
							value="<c:out value="${userDTO.firstName}" />" readonly disabled></div>
					</div>
				</div>
				<div>
					<div class="col-lg-6">
						<input type="text" id="lastName" class="form-control"
							name="lastName" placeholder="Last Name"
							value="<c:out value="${userDTO.lastName}" />">
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div>
					<div class="col-lg-6 text-left"><div class="col-sm-4 text-left"><select name="fathersFirstNameTitle" id="fathersFirstNameTitle"class="form-control text-left"><option>Shri</option>
								<option>Smt</option>
								<option>Dr Shri</option>
								<option>Dr Smt</option>
								<option>Prof Shri</option>
								<option>Prof Smt</option>
								<option>Ms</option>
								<option>Pt Shri</option>
								<option>Late</option></select></div><div class="col-sm-8">
						<input type="text" id="fathersFirstName" class="form-control"
							name="fathersFirstName" placeholder="Father's/Husband's First Name"
							value="<c:out value="${userDTO.fathersFirstName}" />"></div>
					</div>
				</div>
				<div>
					<div class="col-lg-6">
						<input type="text" id="fathersLastName" class="form-control"
							name="fathersLastName" placeholder="Father's/Husband's Last Name"
							value="<c:out value="${userDTO.fathersLastName}" />">
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="emailId" class="form-control" name="emailId"
						placeholder="Email Id &#42;" disabled readonly
						value="<c:out value="${userDTO.emailId}" />">
				</div>
				<div class="col-lg-6">
					<div class='input-group date' id='dateOfBirthPicker'>
						<input type='text' class="form-control" id="dateOfBirth"
							name="dateOfBirth" placeholder="Date Of Birth" value="<c:out value="${userDTO.dateOfBirth}" />" /> <span
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
				<div class="col-lg-3"><select name="gender" id="gender" class="form-control"><option
							value="0">Male</option>
						<option value="1">Female</option></select>
				</div>
				<div class="col-lg-6">
					<label>&nbsp;</label>
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
						name="localBuildingNumber" placeholder="Address Line 1"
						value="<c:out value="${userDTO.localBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="localLocation" name="localLocation"
						class="form-control" placeholder="Address Line 2"
						value="<c:out value="${userDTO.localLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="localCity" name="localCity"
						class="form-control" placeholder="City/District"
						value="<c:out value="${userDTO.localCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="localState" class="form-control"
						name="localState" placeholder="State"
						value="<c:out value="${userDTO.localState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">

				<div class="col-lg-6">
					<input type="text" id="localPincode" name="localPincode"
						class="form-control" placeholder="PIN/Zip"
						value="<c:out value="${userDTO.localPincode}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="localPhoneNumber" class="form-control"
						name="localPhoneNumber" placeholder="Phone Number &#42;"
						value="<c:out value="${userDTO.localPhoneNumber}" />">
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
						name="officeBuildingNumber" placeholder="Address Line 1"
						value="<c:out value="${userDTO.officeBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="officeLocation" name="officeLocation"
						class="form-control" placeholder="Address Line 2"
						value="<c:out value="${userDTO.officeLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="officeCity" name="officeCity"
						class="form-control" placeholder="City/District"
						value="<c:out value="${userDTO.officeCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="officeState" class="form-control"
						name="officeState" placeholder="State"
						value="<c:out value="${userDTO.officeState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="officePincode" name="officePincode"
						class="form-control" placeholder="PIN/Zip"
						value="<c:out value="${userDTO.officePincode}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="officePhoneNumber" name="officePhoneNumber"
						class="form-control" placeholder="Phone Number"
						value="<c:out value="${userDTO.officePhoneNumber}" />">
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
						placeholder="Address Line 1"
						value="<c:out value="${userDTO.permanentBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="permanentLocation" name="permanentLocation"
						class="form-control" placeholder="Address Line 2"
						value="<c:out value="${userDTO.permanentLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="permanentCity" name="permanentCity"
						class="form-control" placeholder="City/District"
						value="<c:out value="${userDTO.permanentCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="permanentState" class="form-control"
						name="permanentState" placeholder="State"
						value="<c:out value="${userDTO.permanentState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="permanentPincode" name="permanentPincode"
						class="form-control" placeholder="PIN/Zip"
						value="<c:out value="${userDTO.permanentState}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" id="permanentPhoneNumber"
						name="permanentPhoneNumber" class="form-control"
						placeholder="Phone Number"
						value="<c:out value="${userDTO.permanentPhoneNumber}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<label>Society Details</label>
				</div>
				<div class="col-lg-6">
					<label>&nbsp;</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" id="societyName" name="societyName"
						class="form-control"
						value="<c:out value="${userDTO.membershipNumber}" />"
						placeholder="Membership Number" disabled>
				</div>
				<div class="col-lg-6">&nbsp;</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<a class="btn btn-success" onclick="validate()" role="button">Update</a>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="alert alert-danger" id="validationMessage"></div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="alert alert-success" id="loading">Updating...</div>
		<div class="alert-danger" id="fail"></div>
		<div class="alert alert-success" id="success">Data Updated
			Successfully.</div>
	</div>
</body>
</html>