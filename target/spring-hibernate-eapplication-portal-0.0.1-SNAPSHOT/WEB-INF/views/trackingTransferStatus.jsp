<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>E-Application System</title>
</head>
<body>
	<div align="left">
		<form:form
			action="/spring-hibernate-eapplication-portal/backToTracking"
			method="GET">
			<input type="submit" value="Back" />
		</form:form>
	</div>

	<h3>Student Portal</h3>
	Unique Id: ${student.uniqueid}
	<br> Name: ${student.firstname} ${student.lastname}
	<br>
	<br>
	<h3>Student Transfer Request Tracking</h3>
	<c:if test="${not empty student }">
		<div class="table-responsive">
			<table class="table table-striped table-bordered"
				style="font-size: 13px">
				<thead>
					<tr>
						<th>Student</th>
						<th>Staff</th>
						<th>Principal</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${student.transferCertificate}</td>
						<td>${student.staffTransferStatus}</td>
						<td>${student.principalTransferStatus}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${empty student }">
		<h4>No Request Sent</h4>
	</c:if>
</body>
</html>