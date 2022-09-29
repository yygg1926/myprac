package prac01.test01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import prac01.test01.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{

	
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection(
//	                "jdbc:mysql://localhost:3306/test",
//	                "root",
//	                "qwer");
//			ServletContext를 이용한 커넥션 공통 사용
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO, MNAME, EMAIL, CRE_DATE" +
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			res.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = res.getWriter();
//			out.println("<html><head><title>회원목록</title></head>");
//			out.println("<body><h1>회원목록</h1>");
//			out.println("<p><a href='add'>신규 회원</a></p>");
//			while(rs.next()) {
//					out.println(
//						rs.getInt("MNO") + ", " +
//					"<a href='update?no=" + rs.getInt("MNO") + "'>" +
//						rs.getString("MNAME") + "</a>, "  +
//						rs.getString("EMAIL") + ", " +
//						rs.getDate("CRE_DATE") + 
//						"<a href='delete?no=" + rs.getInt("MNO") 
//						+"'> [delete] </a>" + "<br>"
//							);
//			}
//			out.println("</body></html>");
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
			}
			
			req.setAttribute("members", members);
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberList.jsp");
			// 다른 서블릿이나 JSP로 작업 위임하는 객체 = RequestDispatcher
			rd.include(req, res);
			
		}catch(Exception e) {
			//throw new ServletException(e); // 에러 페이지로 포워드하기 위한 주석처리
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}finally {
			try {if(rs != null) rs.close();}catch(Exception e) {}
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
			//try {if(conn != null) conn.close();}catch(Exception e) {}
			//ServletContext를 이용한 커넥션 공통 사용
			
		}
		
	}
	
	  	
}
