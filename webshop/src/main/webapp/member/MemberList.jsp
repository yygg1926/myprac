<%-- <%@ page import="prac01.test01.vo.Member" %>
<%@ page import="java.util.ArrayList" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<h1>회원 목록</h1>
	<p>
		<a href='add.do'>신규회원</a>
	</p>
	<%-- <jsp:useBean id="members"
		scope="request"
		class="java.util.ArrayList"
		type="java.util.ArrayList<prac01.test01.vo.Member>"
		/> --%>

	<!-- /* 	ArrayList<Member> members = 
			(ArrayList<Member>)request.getAttribute("members"); */
	/* for(Member member : members){ */
	 -->
	<%-- <c:forEach var="member" items="${members}"> --%>
	<%-- <%=member.getNo() %>,
	<a href='update?no=<%=member.getNo() %>'><%=member.getName() %></a>
	<%=member.getEmail() %>
	<%= member.getCreatedDate() %>
	<a href='delete?no=<%=member.getNo() %>'>[삭제]</a><br> --%>
	<%-- <%} %> --%>
	<%-- ${member.no },
	<a href="update.do?no=${member.no}">${member.name }</a>,
	${member.email},
	${member.createdDate }
	<a href="delete.do?no=${member.no }">[삭제]</a>
		<br>
	</c:forEach> --%>

	<table border="1">
		<tr>
			<th><c:choose>
					<c:when test="${orderCond == 'MNO_ASC'}">
						<a href="list.do?orderCond=MNO_DESC">번호↑</a>
					</c:when>
					<c:when test="${orderCond == 'MNO_DESC'}">
						<a href="list.do?orderCond=MNO_ASC">번호↓</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=MNO_ASC">번호︎</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'NAME_ASC'}">
						<a href="list.do?orderCond=NAME_DESC">이름↑</a>
					</c:when>
					<c:when test="${orderCond == 'NAME_DESC'}">
						<a href="list.do?orderCond=NAME_ASC">이름↓</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=NAME_ASC">이름</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'EMAIL_ASC'}">
						<a href="list.do?orderCond=EMAIL_DESC">이메일↑</a>
					</c:when>
					<c:when test="${orderCond == 'EMAIL_DESC'}">
						<a href="list.do?orderCond=EMAIL_ASC">이메일↓</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=EMAIL_ASC">이메일</a>
					</c:otherwise>
				</c:choose></th>
			<th><c:choose>
					<c:when test="${orderCond == 'CREDATE_ASC'}">
						<a href="list.do?orderCond=CREDATE_DESC">등록일↑</a>
					</c:when>
					<c:when test="${orderCond == 'CREDATE_DESC'}">
						<a href="list.do?orderCond=CREDATE_ASC">등록일↓</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?orderCond=CREDATE_ASC">등록일</a>
					</c:otherwise>
				</c:choose></th>
			<th></th>
		</tr>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.no}</td>
				<td><a href='update.do?no=${member.no}'>${member.name}</a></td>
				<td>${member.email}</td>
				<td>${member.createdDate}</td>
				<td><a href='delete.do?no=${member.no}'>[삭제]</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>