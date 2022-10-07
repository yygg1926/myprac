package prac01.test01.controls;

import java.util.HashMap;
import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {

	ProjectDao projectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"orderCond", String.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("projects", projectDao.selectList(paramMap));
		return "/project/ProjectList.jsp";
	}

}
