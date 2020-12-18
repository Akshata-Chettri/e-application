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
	<h3>Student Portal</h3>
	Unique Id: ${student.uniqueid}
	<br> Name: ${student.firstname} ${student.lastname}
	<br>
	<br>
	<div align="center">
		<form:form action="studentBonafide" method="GET">
			<h4>Request for Your Bonafide Certificate</h4>
			<input type="submit" value="Request Bonafide Certificate" />
		</form:form>
		<form:form action="studentTransfer" method="GET">
			<h4>Request for Your Transfer Certificate</h4>
			<input type="submit" value="Request Transfer Certificate" />
		</form:form>
		<form:form action="tracker/${student.uniqueid}" method="GET">
			<h4>Track Your Request</h4>
			<input type="submit" value="Tracking" />
		</form:form>
	</div>

</body>
</html>