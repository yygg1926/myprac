<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록 </title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>프로젝트 목록 </h1>
<table border="1">
	<tr>
		<th>no</th>
		<th>title</th>
		<th>stDate</th>
		<th>endDate</th>
		<th>state</th>
		<th></th>
	</tr>
	<c:forEach var="project" items="${projects }">
		<tr>
			<td>${project.no }</td>
			<td><a href="update.do?no=${proejct.no }">${project.title }</a></td>
			<td>${project.startDate }</td>
			<td>${project.endDate }</td>
			<td>${project.state }</td>
			<td><a href="delete.do?no=${project.no }">[DELETE]</a></td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/Tail.jsp"/>
</body>
</html>