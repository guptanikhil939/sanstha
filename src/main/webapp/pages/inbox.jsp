<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
<script src="resources/js/authorizeSocietyRequest.js"></script>
<body>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>

	<div class="row">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<div class="container">
			<div class="col-md-12">
				<div class="media-body"
					style="border: 2px solid #eaeaea; border-radius: 10px; padding: 4px;">
					<h3 class="media-heading">Inbox</h3>
					<div>&nbsp;</div>
					<c:if test="${societyRequestList.size() > 0 }">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Message From
									</th>
									<th>Message
									</th>
									<th>Action
									</th>
								</tr>
							</thead>
							<c:forEach items="${societyRequestList}" var="societyRequest">
								<tr id="row_${societyRequest.requestId}">
									<td width="40%"><c:out
											value="${societyRequest.societyName}" /></td>
									<td width="40%"><c:out value="${societyRequest.emailId}" /></td>
									<td width="20%"><button type="button"
											class="btn btn-success"
											onclick="authorize('1','${societyRequest.requestId}','${societyRequest.emailId}');">Reply</button>
										<button type="button" class="btn btn-danger"
											onclick="authorize('0','${societyRequest.requestId}','${societyRequest.emailId}');">Date</button></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${societyRequestList.size() == 0 }">
						<div>No Messages to show.</div>
					</c:if>
				</div>
				<div class="alert-danger" id="fail"></div>
				<div class="alert alert-success" id="success">Data Updated
					Successfully.</div>
			</div>
		</div>
	</div>
</body>
</html>