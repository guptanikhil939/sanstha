<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/editportal.css" media="all" />
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<br/><br/><br/><br/>
<div  class="form">
    		<form:form id="contactform" commandName="society" action="saveWebPortal"> 
    			<c:if test="${not empty message}">
    			<p style="text-align: center; color:green"><b>${message}</b></p>
    			</c:if>
    			<label class="contact" for="societyname">Society Name</label>
    			<form:input path="societyName" id="name" name="name" placeholder="Name Of Society" required="" tabindex="1" type="text" /> 
    			<p class="contact"><form:errors path="societyName"></form:errors></p>
    			<label for="email">Email</label>
    			<form:input path="societyWebAddress" id="email" name="email" placeholder="example@domain.com" required="" type="email" /> 
                <p class="contact"><form:errors path="societyWebAddress"></form:errors></p>
                <label for="pagename">Society Page Name</label>
    			<form:input path="societyWebName" id="pagename" name="pagename" placeholder="Web Page Name of your society" required="" type="text" /> 
                <label class="contact" for="errorgap"></label>
                <p class="contact"><form:errors path="societyWebName"></form:errors></p>
                <label for="aboutsociety">About Society</label>
    			​<form:textarea path="societyAbout" id="about"  style="vertical-align: middle;" rows="5" cols="70" placeholder="About your society" /> 
    			<br/><br/>
    			<label for="contactsoc">Contact</label>
    			​<form:textarea path="societyContact" id="contactus"  style="vertical-align: middle;" rows="3" cols="50" placeholder="contacts for society" /> 
    			<br/><br/><br/>
    			<div style="text-align: center">
    			<form:input path="" class="button" name="submit" id="submit"  value="Update" type="submit" />
    			</div>
    			</form:form>
    			</div>
        </body>
</html>