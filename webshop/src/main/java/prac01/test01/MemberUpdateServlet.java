package prac01.test01;

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
			
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS where MNO='" + req.getParameter("no") +"'"
					);
			//rs.next();
			res.setContentType("text/html; charset=UTF-8");
			Member member = new Member();
			if(rs.next()) {
				member.setNo(rs.getInt("MNO"))
				.setName(rs.getString("MNAME"))
				.setEmail(rs.getString("EMAIL"))
				.setCreatedDate(rs.getDate("CRE_DATE"));
			}
			req.setAttribute("member", member);
//			PrintWriter out = res.getWriter();
//			out.println("<html><head><title>회원정보</title></head>");
//			out.println("<body><h1>회원정보</h1>");
//			out.println("<form action='update' method='post'>");
//			out.println("번호 : <input type='text' name='no' value='" + req.getParameter("no") + "' readonly> <br>");
//			out.println("이름 : <input type='text' name='name' value='" + rs.getString("MNAME") + "'><br>");
//			out.println("이메일 : <input type='text' name='email' value='" + rs.getString("EMAIL") + "'><br>");
//			out.println("가입일 : " + rs.getDate("CRE_DATE") + "<br>");
//			out.println("<input type='submit' value='저장'>")	;
//			out.println("<input type='button' value='삭제'" + "onclick='location.href=\"delete?no=" + req.getParameter("no") + "\"'>");
//			out.println("<input type='button' value='취소'" + " onclick='location.href=\"list\"'>");
//			out.println("</form> </body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.include(req, res);
		}catch(Exception e) {
			//e.printStackTrace();
			//throw new ServletException(e);
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}finally {
			try {if(conn != null) conn.close();}catch(Exception e) {}
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
			try {if(rs != null) rs.close();}catch(Exception e) {}
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		//req.setCharacterEncoding("UTF-8");	//filter적
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			Class.forName(this.getInitParameter("driver"));
//			conn = DriverManager.getConnection(this.getInitParameter("url"),
//						this.getInitParameter("username"),
//						this.getInitParameter("password")
//					);
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.prepareStatement(
						"UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" +
						" WHERE MNO=?"
					);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));
			stmt.executeUpdate();
			
			res.sendRedirect("list");
		}catch(Exception e) {
			throw new ServletException(e);
		}finally {
			try {if(conn != null) conn.close();}catch(Exception e) {}
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
		}
	}
	
}
