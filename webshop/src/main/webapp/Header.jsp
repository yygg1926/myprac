<%@page import="prac01.test01.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="member"
    		scope="session"
    		class="prac01.test01.vo.Member"/>
    <%-- <% Member member = (Member)session.getAttribute("member"); %> --%>
<div style="background-color:orange;color:#ffffff;height:20px;padding:5px;">
My Practice
	<%if(member.getEmail() != null){ %>
	<span style="float:right;">
		<%=member.getName()%>
		<a style="color:white;" href="<%=request.getContextPath()%>/auth/logout">Logout</a>
	</span>
	<%} %>
</div>