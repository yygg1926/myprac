package prac01.test01.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import prac01.test01.vo.Member;

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
	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws IOException{
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			//connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" +
					" FROM MEMBERS" +
					" ORDER BY MNO ASC"
					);
			
			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE"))
						);
			}
			return members;
		}catch(Exception e) {
			throw new IOException(e);
		}finally {
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
			try {if(rs != null) rs.close();}catch(Exception e) {}
//			try{if(connection != null) connPool.returnConnection(connection);}catch(Exception e) {}
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
	public int insert(Member member) throws Exception{
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)" +
					" VALUES (?,?,?,NOW(), NOW())"
					);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw new IOException(e);
		}finally {
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			if(connection != null) connPool.returnConnection(connection);
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM MEMBERS WHERE MNO=" + no);
					
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			if(connection != null) connPool.returnConnection(connection);
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS where MNO=" + no);
			Member member = new Member();
			if(rs.next()) {
				member.setNo(rs.getInt("MNO"))
				.setName(rs.getString("MNAME"))
				.setEmail(rs.getString("EMAIL"))
				.setCreatedDate(rs.getDate("CRE_DATE"));
			}
			return member;
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			try{if(stmt != null) stmt.close();}catch(Exception e) {}
			try {if(rs != null) rs.close();}catch(Exception e) {}
//			if(connection != null) connPool.returnConnection(connection);
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception{
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" +
						" WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
//			if(connection != null) connPool.returnConnection(connection);
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
//			connection = connPool.getConnection();
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"SELECT MNAME,EMAIL FROM MEMBERS"
					+ " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			Member member = new Member();
			if(rs.next()) {
				member.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"));
			}else {
				return null;
			}
			return member;
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			try {if(stmt != null) stmt.close();}catch(Exception e) {}
			try {if(rs != null) rs.close();}catch(Exception e) {}
//			if(connection != null) connPool.returnConnection(connection);
			try {if(connection != null) connection.close();}catch(Exception e) {}
		}
	}
	
}
