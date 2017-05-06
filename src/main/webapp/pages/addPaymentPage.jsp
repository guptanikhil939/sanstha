<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<script src="resources/js/addPayment.js"></script>
<body>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div class="container">
			<div class="col-md-12">
				<div
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Add Payment</h3>
					<div>&nbsp;</div>
					<div class="form-group">
						<div>
							<div class="col-lg-3">Society Name *:</div>
							<div class="col-lg-3">
								<select id="societyId" class="form-control" name="societyId">
									<option value="0">Select</option>
									<c:forEach items="${societyList}" var="society">
										<option value='<c:out value="${society.societyId}"/>'><c:out
												value="${society.societyName}" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-3">Received From *:</div>
							<div class="col-lg-3">
								<input type="text" id="from" class="form-control" name="from">
							</div>
						</div>
						<div class="row col-lg-12">&nbsp;</div>
						<div>
							<div class="col-lg-3">Payment Date:</div>
							<div class="col-lg-3">
								<div class='input-group date' id='paymentDateCalender'>
									<input type='text' class="form-control" id="paymentDate"
										name="paymentDate" /> <span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="col-lg-3">Amount *:</div>
							<div class="col-lg-3">
								<input type="text" id="amount" class="form-control"
									name="amount">
							</div>
						</div>
						<div class="row col-lg-12">&nbsp;</div>
						<div>
							<div class="col-lg-12">
								<button type="button" class="btn btn-success"
									id="addPaymentButton" onclick=addPayment();>Add</button>
							</div>
						</div>
						<div class="row">&nbsp;</div>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="alert-danger" id="fail"></div>
				<div class="alert alert-success" id="success"></div>
			</div>
		</div>
	</div>
</body>
</html>