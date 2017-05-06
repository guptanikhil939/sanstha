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
					<h3 class="media-heading">All Societies</h3>
					<div>&nbsp;</div>
					<div class="form-group">
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
	</div>
</body>
</html>