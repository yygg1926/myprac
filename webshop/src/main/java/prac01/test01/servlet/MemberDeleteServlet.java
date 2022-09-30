package prac01.test01.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prac01.test01.dao.MemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
//			conn = (Connection)sc.getAttribute("conn");
			
//			stmt = conn.prepareStatement("DELETE FROM MEMBERS WHERE MNO=?");
//			stmt.setInt(1, Integer.parseInt(req.getParameter("no")));
//			stmt.executeUpdate();
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			memberDao.delete(Integer.parseInt(req.getParameter("no")));
			
//			res.sendRedirect("list");
			req.setAttribute("viewUrl", "redirect:list.do");
		}catch(Exception e) {
			throw new ServletException(e);
//			req.setAttribute("error", e);
//			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//			rd.forward(req, res);
		}
//		}finally {
//			try {if(conn != null) conn.close();}catch(Exception e) {}
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//		}
		
	}
}
