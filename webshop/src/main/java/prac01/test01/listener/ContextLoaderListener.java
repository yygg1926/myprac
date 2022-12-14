package prac01.test01.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import prac01.test01.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	// Connection conn;
	// DBConnectionPool connPool;
	//BasicDataSource ds;
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicatonContext() {
		return applicationContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
//			ServletContext sc = event.getServletContext();
			applicationContext = new ApplicationContext();

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
//			InitialContext initialContext = new InitialContext();
//			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/test");

//			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
//			applicationContext = new ApplicationContext(propertiesPath);
			String resource = "prac01/test01/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
			ServletContext sc = event.getServletContext();
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			
			applicationContext.prepareObjectsByProperties(propertiesPath);
			applicationContext.prepareObjectByAnnotation("");
			applicationContext.injectDependency();
////			MemberDao memberDao = new MemberDao();
////			 MemberDao interface 생성해서 구현
//			MariaMemberDao memberDao = new MariaMemberDao();
////			memberDao.setConnection(conn);
////			memberDao.setDBConnectionPool(connPool);
//			memberDao.setDataSource(ds);
//
////			sc.setAttribute("memberDao", memberDao);
//			sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
//			sc.setAttribute("/auth/logout.do", new LogOutController());
//			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
//			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
//			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
//			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));

		} catch (Exception e) {
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
