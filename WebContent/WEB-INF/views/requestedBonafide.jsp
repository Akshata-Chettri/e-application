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
		<form:form action="/spring-hibernate-eapplication-portal/backToStaffPortal" method="GET">
			<input type="submit" value="Back" />
		</form:form>
	</div>
	<h3>Requested Bonafide Certificate</h3>
	<c:if test="${not empty staff }">
		<div class="table-responsive">
			<table class="table table-striped table-bordered"
				style="font-size: 13px">
				<thead>
					<tr>
						<th>Unique Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Certificate</th>
						<th>Approve Request</th>
						<th>Reject Request</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="staff" items="${staff }">
						<tr>
							<td>${staff.uniqueid}</td>
							<td>${staff.firstname}</td>
							<td>${staff.lastname}</td>
							<td>${staff.certificate}</td>
							<td><a href="approveBonafideStaff/${staff.uniqueid}"><span
									class="glyphicon glyphicon-thumbs-up"></span></a></td>
							<td><a href="rejectBonafideStaff?uniqueid=${staff.uniqueid}"><span
									class="glyphicon glyphicon-thumbs-down"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${empty staff }">
		<h4>Empty Results</h4> 
	</c:if>
</body>
</html>