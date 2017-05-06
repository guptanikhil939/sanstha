<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<body>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row col-lg-8 list-group col-lg-offset-2">
		<a id="registrationButton" class="list-group-item active" href="#">
			<h4 class="list-group-item-heading">View Member</h4> <span
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
					<div class="col-lg-6">
						<input type="text" readonly="readonly" id="firstName" class="form-control"
							name="firstName" placeholder="First Name &#42;"
							value="<c:out value="${member.firstName}" />">
					</div>
				</div>
				<div>
					<div class="col-lg-6">
						<input type="text" readonly="readonly" id="lastName" class="form-control"
							name="lastName" placeholder="Last Name"
							value="<c:out value="${member.lastName}" />">
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div>
					<div class="col-lg-6">
						<input type="text" readonly="readonly" id="fathersFirstName" class="form-control"
							name="fathersFirstName" placeholder="Father's First Name"
							value="<c:out value="${member.fathersFirstName}" />">
					</div>
				</div>
				<div>
					<div class="col-lg-6">
						<input type="text" readonly="readonly" id="fathersLastName" class="form-control"
							name="fathersLastName" placeholder="Father's Last Name"
							value="<c:out value="${member.fathersLastName}" />">
					</div>
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="emailId" class="form-control" name="emailId"
						placeholder="Email Id &#42;" disabled
						value="<c:out value="${member.emailId}" />">
				</div>
				<div class="col-lg-6">
					<div class='input-group date' readonly="readonly" id='dateOfBirthPicker'>
						<input type='text' class="form-control" readonly="readonly" id="dateOfBirth"
							name="dateOfBirth" placeholder="Date Of Birth" value="<c:out value="${member.dateOfBirth}" />"/>
						</span>
					</div>
				</div>
				<div class="col-lg-6"></div>
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
					<input type="text" readonly="readonly" id="localBuildingNumber" class="form-control"
						name="localBuildingNumber" placeholder="Building Number"
						value="<c:out value="${member.localBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="localLocation" name="localLocation"
						class="form-control" placeholder="Location"
						value="<c:out value="${member.localLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="localCity" name="localCity"
						class="form-control" placeholder="City"
						value="<c:out value="${member.localCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="localState" class="form-control"
						name="localState" placeholder="State"
						value="<c:out value="${member.localState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">

				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="localPincode" name="localPincode"
						class="form-control" placeholder="Pincode"
						value="<c:out value="${member.localPincode}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="localPhoneNumber" class="form-control"
						name="localPhoneNumber" placeholder="Phone Number &#42;"
						value="<c:out value="${member.localPhoneNumber}" />">
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
					<input type="text" readonly="readonly" id="officeBuildingNumber" class="form-control"
						name="officeBuildingNumber" placeholder="Building Number"
						value="<c:out value="${member.officeBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="officeLocation" name="officeLocation"
						class="form-control" placeholder="Location"
						value="<c:out value="${member.officeLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="officeCity" name="officeCity"
						class="form-control" placeholder="City"
						value="<c:out value="${member.officeCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="officeState" class="form-control"
						name="officeState" placeholder="State"
						value="<c:out value="${member.officeState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="officePincode" name="officePincode"
						class="form-control" placeholder="Pincode"
						value="<c:out value="${member.officePincode}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="officePhoneNumber" name="officePhoneNumber"
						class="form-control" placeholder="Phone Number"
						value="<c:out value="${member.officePhoneNumber}" />">
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
					<input type="text" readonly="readonly" id="permanentBuildingNumber"
						class="form-control" name="permanentBuildingNumber"
						placeholder="Building Number"
						value="<c:out value="${member.permanentBuildingNumber}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="permanentLocation" name="permanentLocation"
						class="form-control" placeholder="Location"
						value="<c:out value="${member.permanentLocation}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="permanentCity" name="permanentCity"
						class="form-control" placeholder="City"
						value="<c:out value="${member.permanentCity}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="permanentState" class="form-control"
						name="permanentState" placeholder="State"
						value="<c:out value="${member.permanentState}" />">
				</div>
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="form-group">
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="permanentPincode" name="permanentPincode"
						class="form-control" placeholder="Pincode"
						value="<c:out value="${member.permanentState}" />">
				</div>
				<div class="col-lg-6">
					<input type="text" readonly="readonly" id="permanentPhoneNumber"
						name="permanentPhoneNumber" class="form-control"
						placeholder="Phone Number"
						value="<c:out value="${member.permanentPhoneNumber}" />">
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
					<input type="text" readonly="readonly" id="societyName" name="societyName"
						class="form-control"
						value="<c:out value="${member.membershipNumber}" />"
						placeholder="Membership Number" disabled>
				</div>
				<div class="col-lg-6">&nbsp;</div>
			</div>
		</div>
	</div>
</body>
</html>