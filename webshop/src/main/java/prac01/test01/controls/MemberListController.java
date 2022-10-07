package prac01.test01.controls;

import java.util.HashMap;
import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.MemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
		"orderCond", String.class		
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		//MemberDao memberDao = (MemberDao)model.get("memberDao");
		// setter 메소드로 DI 받기때문에 더이상 Map객체에서 받아올 필요 없음 
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("members", memberDao.selectList(paramMap));
		return "/member/MemberList.jsp";
	}

}
