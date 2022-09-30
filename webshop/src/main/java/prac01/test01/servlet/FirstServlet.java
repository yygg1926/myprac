package prac01.test01.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	public void init() throws ServletException {
	      System.out.println("init 메서드 호출 First");
	   }

	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
	      System.out.println("doGet 메서드 호출 First");
	   } 

	   public void destroy() {
	      System.out.println("destroy 메서드 호출 First");
	   }
	
}