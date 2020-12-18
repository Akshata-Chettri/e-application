<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Application System</title>
</head>
<body>
	<div align="right">
		<form:form action="logout" method="GET">
			<input type="submit" value="Logout" />
		</form:form>
	</div>
	<h3>Staff Portal</h3>
	Unique Id: ${staff.uniqueid}
	<br> Name: ${staff.firstname} ${staff.lastname}
	<br>
	<br>
	<div align="center">
		<h4>Requested Certificate</h4>
		<form:form action="requestedBonafide" method="GET">
			<input type="submit" value="Check Bonafide Request" />
		</form:form>
		<br>
		<form:form action="requestedTransfer" method="GET">
			<input type="submit" value="Check Transfer Request" />
		</form:form>
	</div>
</body>
</html>