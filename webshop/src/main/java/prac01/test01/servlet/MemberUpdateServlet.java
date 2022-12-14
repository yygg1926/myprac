package prac01.test01.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prac01.test01.dao.MemberDao;
import prac01.test01.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
//			Class.forName(this.getInitParameter("driver"));
//			conn = DriverManager.getConnection(this.getInitParameter("url"),
//					this.getInitParameter("username"),
//					this.getInitParameter("password"));
			ServletContext sc = this.getServletContext();
			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			
			
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS where MNO='" + req.getParameter("no") +"'"
//					);
			//rs.next();
			res.setContentType("text/html; charset=UTF-8");
			Member member = new Member();
//			if(rs.next()) {
//				member.setNo(rs.getInt("MNO"))
//				.setName(rs.getString("MNAME"))
//				.setEmail(rs.getString("EMAIL"))
//				.setCreatedDate(rs.getDate("CRE_DATE"));
//			}
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
			
			req.setAttribute("member", member);
//			PrintWriter out = res.getWriter();
//			out.println("<html><head><title>????????????</title></head>");
//			out.println("<body><h1>????????????</h1>");
//			out.println("<form action='update' method='post'>");
//			out.println("?????? : <input type='text' name='no' value='" + req.getParameter("no") + "' readonly> <br>");
//			out.println("?????? : <input type='text' name='name' value='" + rs.getString("MNAME") + "'><br>");
//			out.println("????????? : <input type='text' name='email' value='" + rs.getString("EMAIL") + "'><br>");
//			out.println("????????? : " + rs.getDate("CRE_DATE") + "<br>");
//			out.println("<input type='submit' value='??????'>")	;
//			out.println("<input type='button' value='??????'" + "onclick='location.href=\"delete?no=" + req.getParameter("no") + "\"'>");
//			out.println("<input type='button' value='??????'" + " onclick='location.href=\"list\"'>");
//			out.println("</form> </body></html>");
//			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
//			rd.include(req, res);
			req.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
		}catch(Exception e) {
			//e.printStackTrace();
			throw new ServletException(e);
//			req.setAttribute("error", e);
//			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//			rd.forward(req, res);
		}
//		}finally {
//			try {if(conn != null) conn.close();}catch(Exception e) {}
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			try {if(rs != null) rs.close();}catch(Exception e) {}
//		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		//req.setCharacterEncoding("UTF-8");	//filter???
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			Class.forName(this.getInitParameter("driver"));
//			conn = DriverManager.getConnection(this.getInitParameter("url"),
//						this.getInitParameter("username"),
//						this.getInitParameter("password")
//					);
			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
//			conn = (Connection) sc.getAttribute("conn");
			
			
//			stmt = conn.prepareStatement(
//						"UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" +
//						" WHERE MNO=?"
//					);
//			stmt.setString(1, req.getParameter("email"));
//			stmt.setString(2, req.getParameter("name"));
//			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));
//			stmt.executeUpdate();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
//			memberDao.update(new Member().setNo(Integer.parseInt(req.getParameter("no")))
//					.setEmail(req.getParameter("email"))
//					.setName(req.getParameter("name")));
			memberDao.update((Member)req.getAttribute("member"));
			
//			res.sendRedirect("list");
			req.setAttribute("viewUrl", "redirect:list.do");
		}catch(Exception e) {
			throw new ServletException(e);
		}
//		}finally {
//			try {if(conn != null) conn.close();}catch(Exception e) {}
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//		}
	}
	
}
