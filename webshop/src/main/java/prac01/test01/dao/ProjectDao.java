package prac01.test01.dao;

import java.util.List;

import prac01.test01.vo.Project;

public interface ProjectDao {

	List<Project> selectList() throws Exception;
	
//	int insert(Project project) throws Exception;
//	
//	Project selectOne(int no) throws Exception;
//	
//	int udpate(Project project) throws Exception;
//	
//	int delete(int no) throws Exception;
	
}
