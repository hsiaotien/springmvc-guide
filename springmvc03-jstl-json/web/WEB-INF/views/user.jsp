<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jstl Demo</title>
</head>
<body>
	<table cellpadding=0 cellspacing=0 border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>UserName</th>
				<th>Name</th>
				<th>Age</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userlist }" var="user">
				<tr>
					<td>${user.ID }</td>
					<td>${user.userName }</td>
					<td>${user.name }</td>
					<td>${user.age }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
