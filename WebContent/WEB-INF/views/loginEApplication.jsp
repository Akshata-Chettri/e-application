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
	<h3>Welcome to E-Application System</h3>
	<br>

	<div align="center">
		<form:form action="login" method="POST"
			modelAttribute="loginEApplication">
			<table border="0">
				<input type="text" name="uniqueid" placeholder="Unique ID*" required />
				<input type="submit" value="Login" />
			</table>
		</form:form>
	</div>
</body>
</html>