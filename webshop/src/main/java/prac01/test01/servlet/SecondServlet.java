package prac01.test01.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/third")
public class SecondServlet extends HttpServlet {

	public void init() throws ServletException {
	      System.out.println("init 메서드 호출 Third");
	   }

	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
	      System.out.println("doGet 메서드 호출 Third");
	   } 

	   public void destroy() {
	      System.out.println("destroy 메서드 호출 Third");
	   }
	
}
