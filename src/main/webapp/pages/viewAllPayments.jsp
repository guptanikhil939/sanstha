<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="menu.jsp"></jsp:include>
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
					<h3 class="media-heading">Payments</h3>
					<div>&nbsp;</div>
					<div class="form-group">
						<c:if test="${paymentList.size() > 0 }">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Society Name</th>
										<th>Depositor Name</th>
										<th>Payment Date</th>
										<th>Amount</th>
									</tr>
								</thead>
								<c:forEach items="${paymentList}" var="payment">
									<tr>
										<td width="40%"><c:out value="${payment.societyName}" /></td>
										<td width="20%"><c:out value="${payment.depositorName}" /></td>
										<td width="20%"><c:out value="${payment.paymentDate}" /></td>
										<td width="20%"><c:out value="${payment.amount}" /></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${paymentList.size() == 0 }">
							<div>No payments received Yet.</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>