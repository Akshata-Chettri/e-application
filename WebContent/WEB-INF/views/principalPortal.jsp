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
	<h3>Principal Portal</h3>
	Unique Id: ${principal.uniqueid}
	<br>Name: ${principal.firstname} ${principal.lastname}
	<br>
	<br>

	<div align="center">
		<h4>Approved Request By Staff</h4>
		<form:form action="approvedBonafide" method="GET">
			<input type="submit" value="Check Approved Bonafide Request" />
		</form:form>
		<br>
		<form:form action="approvedTransfer" method="GET">
			<input type="submit" value="Check Approved Transfer Request" />
		</form:form>
	</div>
</body>
</html>