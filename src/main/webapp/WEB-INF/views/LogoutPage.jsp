<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>
	<h1>Log Out</h1>
	<div>
		<form:form action="${pageContext.request.contextPath}/logout" >
		<input type="submit" value="Log out" />
		</form:form>
	</div>
</body>
</html>