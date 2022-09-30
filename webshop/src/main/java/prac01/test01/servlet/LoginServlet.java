package prac01.test01.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prac01.test01.dao.MemberDao;
import prac01.test01.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher(
//				"/auth/LogInForm.jsp");
//		rd.forward(request, response);
		request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");  
//			stmt = conn.prepareStatement(
//					"SELECT MNAME,EMAIL FROM MEMBERS"
//					+ " WHERE EMAIL=? AND PWD=?");
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			rs = stmt.executeQuery();
			Member member = new Member();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			member = memberDao.exist(request.getParameter("email"), request.getParameter("password"));
			
//			if (rs.next()) {
//				Member member = new Member()
//						.setEmail(rs.getString("EMAIL"))
//						.setName(rs.getString("MNAME"));
//				HttpSession session = request.getSession();
//				session.setAttribute("member", member);
//				
//				response.sendRedirect("../member/list");
//			} else {
//				RequestDispatcher rd = request.getRequestDispatcher(
//						"/auth/LogInFail.jsp");
//				rd.forward(request, response);
//			}
			if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
//				response.sendRedirect("../member/list");
				request.setAttribute("viewUrl", "redirect:../member/list.do");
			}else {
//				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
//				rd.forward(request, response);
				request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}	
//		} finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close();} catch (Exception e) {}
//		}
	}
	
}
