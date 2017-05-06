<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css"
	media="screen">
<script src="resources/js/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function() {
	});
	function aboutUs() {
		$("#footerButton").val("A");
		$("#footerForm").submit();
	}

	function contactUs() {
		$("#footerButton").val("C");
		$("#footerForm").submit();
	}
</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span class="navbar-brand">Sanstha</span>
			</div>
		</div>
	</div>

	<div>&nbsp;</div>
	<div class="jumbotron">
		<div class="row">
			<h1 class="text-center">ERROR</h1>
			<p>
				It seems you have landed up at wrong place. We would suggest you to
				go back to our <a href="/">Home</a> page and start exploring.
			</p>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
			<div class="row">&nbsp;</div>
		</div>
	</div>
	<div class="navbar footer">
		<form class="text-center" id="footerForm" method="post"
			action="footer">
			<input type="hidden" id="footerButton" name="footerButton"
				class="form-control"> <a onclick="aboutUs()" class="btn">About
				Us</a><a onclick="contactUs()" class="btn">Contact Us</a>
		</form>
	</div>
</body>
</html>