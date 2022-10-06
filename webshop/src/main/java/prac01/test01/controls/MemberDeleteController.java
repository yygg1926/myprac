package prac01.test01.controls;

import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
//		MemberDao memberDao = (MemberDao) model.get("memberDao");
		memberDao.delete(Integer.parseInt(String.valueOf(model.get("no"))));
		return "redirect:list.do";
	}

}
