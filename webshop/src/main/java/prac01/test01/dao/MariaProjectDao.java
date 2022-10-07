package prac01.test01.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import prac01.test01.annotation.Component;
import prac01.test01.vo.Project;

@Component("projectDao")
public class MariaProjectDao implements ProjectDao {
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactdory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
//	DataSource ds;
//
//	public void setDataSource(DataSource ds) {
//		this.ds = ds;
//	}

	@Override
	public List<Project> selectList(HashMap<String, Object> paramMap) throws Exception {
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection conn = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"SELECT PNO, PNAME, STA_DATE, END_DATE, STATE" + " FROM PROJECTS" + " ORDER BY PNO DESC");
//
//			ArrayList<Project> projects = new ArrayList<Project>();
//
//			while (rs.next()) {
//				projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
//						.setStartDate(rs.getDate("STA_DATE")).setEndDate(rs.getDate("END_DATE"))
//						.setState(rs.getInt("STATE")));
//			}
//
//			return projects;
			return sqlSession.selectList("prac01.test01.dao.ProjectDao.selectList", paramMap);
//		} catch (Exception e) {
//			throw e;
		} finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
			sqlSession.close();
		}
	}

	@Override
	public int insert(Project prj) throws Exception {
//		PreparedStatement stmt = null;
//		Connection conn = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
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
			int count = sqlSession.insert("prac01.test01.dao.ProjectDao.insert", prj);
			sqlSession.commit();
			return count;
			
//		} catch (Exception e) {
//			throw e;
		}finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
			sqlSession.close();
		}

	}
	
	@Override
	public Project selectOne(int no) throws Exception{
//		Statement stmt = null;
//		Connection conn = null;
//		ResultSet rs = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"SELECT PNO, PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE,TAGS" +
//							" FROM PROJECTS WHERE NO=" + no
//					);
//			if(rs.next()) {
//				return new Project()
//						.setNo(rs.getInt("PNO"))
//						.setTitle(rs.getString("PNAME"))
//						.setContent(rs.getString("CONTENT"))
//						.setStartDate(rs.getDate("STA_DATE"))
//						.setEndDate(rs.getDate("END_DATE"))
//						.setState(rs.getInt("STATE"))
//						.setCreatedDate(rs.getDate("CRE_DATE"))
//						.setTags(rs.getString("TAGS"));
//			}else {
//				throw new Exception("해당 번호의 프로젝트를 찾을 수 없습니다.");
//			}
			return sqlSession.selectOne("prac01.test01.dao.ProjectDao.selectOne", no);
			
//		}catch(Exception e) {
//			throw e;
		}finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
			sqlSession.close();
		}
	}
	
	@Override
	public int update(Project project) throws Exception{
//		Connection conn = null;
//		PreparedStatement stmt = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"UPDATE PROJECTS SET " +
//							"PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=?" +
//							" WHERE PNO=?"
//					);
//			stmt.setString(1, project.getTitle());
//			stmt.setString(2, project.getContent());
//			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
//			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
//			stmt.setInt(5, project.getState());
//			stmt.setString(6, project.getTags());
//			stmt.setInt(7, project.getNo());
//			
//			return stmt.executeUpdate();
			Project original = sqlSession.selectOne("prac01.test01.dao.ProjectDao.selectOne", project.getNo());
			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if(!project.getTitle().equals(original.getTitle())) {
				paramMap.put("title", project.getTitle());
			}
			if(!project.getContent().equals(original.getContent())) {
				paramMap.put("content", project.getContent());
			}
			if(project.getStartDate().compareTo(original.getStartDate()) != 0) {
				paramMap.put("startDate", project.getStartDate());
			}
			if(project.getEndDate().compareTo(original.getEndDate()) != 0) {
				paramMap.put("endDate", project.getEndDate());
			}
			if(project.getState() != original.getState()) {
				paramMap.put("state", project.getState());
			}
			if(!project.getTags().equals(original.getTags())) {
				paramMap.put("tags", project.getTags());
			}
			if(paramMap.size() > 0) {
				paramMap.put("no", project.getNo());
				int count = sqlSession.update("prac01.test01.dao.ProjectDao.update", project);
				sqlSession.commit();
				return count;
			}else {
				return 0;
			}

//		}catch(Exception e) {
//			throw e;
		}finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
			sqlSession.close();
		}
	}
	
	@Override
	public int delete(int no) throws Exception{
//		Connection conn = null;
//		Statement stmt = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			return stmt.executeUpdate(
//						"DELETE FROM PROJECTS WHERE PNO=" + no
//					);
			int count = sqlSession.delete("prac01.test01.dao.ProjectDao.delete", no);
			sqlSession.commit();
			return  count;
//		}catch(Exception e) {
//			throw e;
		}finally {
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close(); } catch (Exception e) {}
			sqlSession.close();
		}
	}
}