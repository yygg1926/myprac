package prac01.test01.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import prac01.test01.annotation.Component;
import prac01.test01.vo.Member;

@Component("memberDao")
public class MariaMemberDao implements MemberDao{

//	Connection connection;
//	
//	// DAO에서는 ServletContext에 접근할 수 없어 외부주입을 위한 setter 선언(DI)
//	public void setConnection(Connection connection) {
//		this.connection = connection;
//	}
//	DBConnectionPool 적용해 객체 주입
//	DBConnectionPool connPool;
//	public void setDBConnectionPool(DBConnectionPool connPool) {
//		this.connPool = connPool;
//	}
//	DataSource를 적용
//	DataSource ds;
//	public void setDataSource(DataSource ds) {
//		this.ds = ds;
//	}
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<Member> selectList(HashMap<String, Object> paramMap) throws IOException{
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.createStatement();
//			rs = stmt.executeQuery(
//					"SELECT MNO,MNAME,EMAIL,CRE_DATE" +
//					" FROM MEMBERS" +
//					" ORDER BY MNO ASC"
//					);
			
//			
//			ArrayList<Member> members = new ArrayList<Member>();
//			while(rs.next()) {
//				members.add(new Member().setNo(rs.getInt("MNO"))
//						.setName(rs.getString("MNAME"))
//						.setEmail(rs.getString("EMAIL"))
//						.setCreatedDate(rs.getDate("CRE_DATE"))
//						);
//			}
//			return members;
			return sqlSession.selectList("prac01.test01.dao.MemberDao.selectList", paramMap);
//			
//		}catch(Exception e) {
//			throw new IOException(e);
		}finally {
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			try {if(rs != null) rs.close();}catch(Exception e) {}
////			try{if(connection != null) connPool.returnConnection(connection);}catch(Exception e) {}
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
	public int insert(Member member) throws Exception{
//		PreparedStatement stmt = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
//			connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.prepareStatement(
//					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
//					" VALUES (?,?,?,NOW(), NOW())"
//					);
//			stmt.setString(1, member.getEmail());
//			stmt.setString(2, member.getPassword());
//			stmt.setString(3, member.getName());
//			return stmt.executeUpdate();
			int count = sqlSession.insert("prac01.test01.dao.MemberDao.insert", member);
			sqlSession.commit();
			return count;
//		}catch(Exception e) {
//			throw new IOException(e);
		}finally {
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
////			if(connection != null) connPool.returnConnection(connection);
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
	public int delete(int no) throws Exception{
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.createStatement();
//			return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO=" + no);
//					
			int count = sqlSession.delete("prac01.test01.dao.MemberDao.delete", no);
			sqlSession.commit();
			return count;
//		}catch(Exception e) {
//			throw new Exception(e);
		}finally {
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
////			if(connection != null) connPool.returnConnection(connection);
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
	public Member selectOne(int no) throws Exception{
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.createStatement();
//			rs = stmt.executeQuery("select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS where MNO=" + no);
//			Member member = new Member();
//			if(rs.next()) {
//				member.setNo(rs.getInt("MNO"))
//				.setName(rs.getString("MNAME"))
//				.setEmail(rs.getString("EMAIL"))
//				.setCreatedDate(rs.getDate("CRE_DATE"));
//			}
//			return member;
			return sqlSession.selectOne("prac01.test01.dao.MemberDao.selectOne", no);
//		}catch(Exception e) {
//			throw new Exception(e);
		}finally {
//			try{if(stmt != null) stmt.close();}catch(Exception e) {}
//			try {if(rs != null) rs.close();}catch(Exception e) {}
////			if(connection != null) connPool.returnConnection(connection);
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
	public int update(Member member) throws Exception{
//		PreparedStatement stmt = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" +
//						" WHERE MNO=?");
//			stmt.setString(1, member.getEmail());
//			stmt.setString(2, member.getName());
//			stmt.setInt(3, member.getNo());
//			return stmt.executeUpdate();
			int count = sqlSession.update("prac01.test01.dao.MemberDao.update", member);
			sqlSession.commit();
			return count;
//		}catch(Exception e) {
//			throw new Exception(e);
		}finally {
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
////			if(connection != null) connPool.returnConnection(connection);
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
	public Member exist(String email, String password) throws Exception{
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		Connection connection = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		paramMap.put("password", password);
		try {
//			connection = connPool.getConnection();
//			connection = ds.getConnection();
//			stmt = connection.prepareStatement(
//					"SELECT MNAME,EMAIL FROM MEMBERS"
//					+ " WHERE EMAIL=? AND PWD=?");
//			stmt.setString(1, email);
//			stmt.setString(2, password);
//			rs = stmt.executeQuery();
//			Member member = new Member();
//			if(rs.next()) {
//				member.setEmail(rs.getString("EMAIL"))
//						.setName(rs.getString("MNAME"));
//			}else {
//				return null;
//			}
//			return member;
			return sqlSession.selectOne("prac01.test01.dao.memberDao.exist", paramMap);
//		}catch(Exception e) {
//			throw new Exception(e);
		}finally {
//			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			try {if(rs != null) rs.close();}catch(Exception e) {}
////			if(connection != null) connPool.returnConnection(connection);
//			try {if(connection != null) connection.close();}catch(Exception e) {}
			sqlSession.close();
		}
	}
	
}
