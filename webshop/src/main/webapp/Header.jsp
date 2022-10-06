<%@page import="prac01.test01.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%-- <jsp:useBean id="member"
    		scope="session"
    		class="prac01.test01.vo.Member"/> --%>
    <%-- <% Member member = (Member)session.getAttribute("member"); %> --%>
<div style="background-color:orange;color:#ffffff;height:20px;padding:5px;">
My Practice
	<%-- <%if(member.getEmail() != null){ %> --%>
	<span style="float:right;">
		<a style="color:white;" href="<%=request.getContextPath() %>/project/list.do">프로젝트 </a>
		<a style="color:white;" href="<%=request.getContextPath() %>/member/list.do">회원</a>
		<c:if test="${empty sessionScope.member or empty sessionScope.member.email }">
			<a style="color:white;" href="<%=request.getContextPath() %>/auth/login.do">로그인</a>	
		</c:if>
		
		<c:if test="${!empty sessionScope.member or !empty sessionScope.member.email }">
			<%-- <%=member.getName()%> --%>
			${sessionScope.member.name }
			<a style="color:white;" href="<%=request.getContextPath()%>/auth/logout.do">Logout</a>	
		</c:if>
	</span>
</div>