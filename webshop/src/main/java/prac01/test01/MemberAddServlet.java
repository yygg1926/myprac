package prac01.test01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = res.getWriter();
//		out.println("<html><head><title>회원등록</title></head>");
//		out.println("<body><h1>회원등록</h1>");
//		out.println("<form action='add' method='post'>");
//		out.println("이름 : <input type='text' name='name'><br>");
//		out.println("이메일 : <input type='text' name='email'><br>");
//		out.println("암호 : <input type='text' name='password'><br>");
//		out.println("<input type='submit' value='추가'>");
//		out.println("<input type='reset' value='취소'>");
//		out.println("</form>");
//		out.println("</body></html>");
		RequestDispatcher rd = req.getRequestDispatcher("/member/MemberAddForm.jsp");
		rd.forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		//	req.setCharacterEncoding("UTF-8");	//한글이 깨지는 현상 방지 , filter 적
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/test",
						"root",
						"qwer"
					);
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
					" VALUES (?,?,?,NOW(), NOW())"
					);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			stmt.executeUpdate();
			
//			res.sendRedirect("list");//결과 화면 안보여주고 다른화면 갈때는 리다이렉트를 이
			
			res.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = res.getWriter();
//			out.println("<html><head><title>회원등록결과</title>");
//			out.println("<meta http-equiv='Refresh' content='1; url=list'>");	// 1초뒤 자동 리프레쉬(header추가와 둘중 하나로 선택해서 사용가능)
//			out.println("</head>");
//			out.println("<body>");
//			out.println("<p>등록이 완료되었습니다.</p><br/>");
//			out.println("<a href='list'>돌아가기</a>");
//			out.println("</body></html>");			
//			res.addHeader("Refresh", "1;url=list");	// 1초 뒤 자동 리프레시 
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(req, res);
		}catch(Exception e) {
//			e.printStackTrace();
//			throw new ServletException(e);
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}finally {
			try {if(conn != null) conn.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
	}

}
