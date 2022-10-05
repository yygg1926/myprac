package prac01.test01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import prac01.test01.vo.Project;

public class MariaProjectDao implements ProjectDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Project> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT PNO, PNAME, STA_DATE, END_DATE, STATE" + " FROM PROJECTS" + " ORDER BY PNO DESC");

			ArrayList<Project> projects = new ArrayList<Project>();

			while (rs.next()) {
				projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("STA_DATE")).setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE")));
			}

			return projects;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (conn != null) conn.close();} catch (Exception e) {}
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close(); } catch (Exception e) {}

		}
	}

//	@Override
//	public int insert(Project prj) throws Exception {
//		PreparedStatement stmt = null;
//		Connection conn = null;
//
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement("INSERT INTO PROJECT("
//					+ "PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)" + 
//					"VALUES(?, ?, ?, ?, 0, NOW(), ?)");
//			stmt.setString(1, prj.getTitle());
//			stmt.setString(2, prj.getContent());
//			stmt.setDate(3, new java.sql.Date(prj.getStartDate().getTime()));
//			stmt.setDate(4, new java.sql.Date(prj.getEndDate().getTime()));
//			stmt.setString(5, prj.getTags());
//			
//			return stmt.executeUpdate();
//			
//		} catch (Exception e) {
//			throw e;
//		}finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
//		}
//
//	}
}