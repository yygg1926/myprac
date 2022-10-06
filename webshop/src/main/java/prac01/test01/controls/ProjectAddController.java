package prac01.test01.controls;

import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.ProjectDao;
import prac01.test01.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding{

	ProjectDao projectDao;
	
	public ProjectAddController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"project", prac01.test01.vo.Project.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception{
		Project prj = (Project)model.get("project");
		if(prj.getTitle() == null) {
			return "/project/ProjectForm.jsp";
		}else {
			projectDao.insert(prj);
			return "redirect:list.do";
		}
	}
	
}
