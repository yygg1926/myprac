package prac01.test01.listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import prac01.test01.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	//Connection conn;
	//DBConnectionPool connPool;
	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//					);
//			connectionpool 을 이용한 커넥
//			connPool = new DBConnectionPool(
//					sc.getInitParameter("driver"),
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//					);
//			DataSource의 구현체인 BasicDataSource를 이용한 커넥션 
//			ds = new BasicDataSource();
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
//			DataSource를 이용한 커넥션 
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
					"java:comp/env/jdbc/test");
			
			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
//			memberDao.setDBConnectionPool(connPool);
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		try {
//			conn.close();
//		}catch(Exception e) {}
		
		
//		connPool.closeAll();
//		try {if(ds != null) ds.close();}catch(Exception e) {}
	}

}
