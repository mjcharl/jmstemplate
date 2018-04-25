<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>
	<h1>Register</h1>
	<form:form modelAttribute="registerBean" method="POST">
		<table>
			<tr>
				<td><form:label path="forename">Forename</form:label></td>
				<td><form:input path="forename" /> <form:errors path="forename" /></td>
			</tr>
			<tr>
				<td><form:label path="surname">Surname</form:label></td>
				<td><form:input path="surname" /> <form:errors path="surname" /></td>
			</tr>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" /> <form:errors path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" /> <form:errors path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register"></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>

	${user.forename} ${user.surname}
	
	<div>
		<form:form action="${pageContext.request.contextPath}/logout" >
		<input type="submit" value="Log out" />
		</form:form>
	</div>
</body>
</html>