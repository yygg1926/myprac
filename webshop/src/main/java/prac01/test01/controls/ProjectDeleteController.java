package prac01.test01.controls;

import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.ProjectDao;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {

	ProjectDao projectDao;
	
	public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer no = (Integer)model.get("no");
		projectDao.delete(no);
		return "redirect:list.do";
	}

}
