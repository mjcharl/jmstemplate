<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Search</title>
</head>
<body>
	<form:form modelAttribute="userBean" method="POST">
		<form:label path="username">Username</form:label>
		<form:input path="username" />
		<input type="submit" value="Submit">
	</form:form>

	${user.forename} ${user.surname}
</body>
</html>