package prac01.test01.controls;

import java.util.Map;

import prac01.test01.dao.MemberDao;

public class MemberListController implements Controller {

	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		//MemberDao memberDao = (MemberDao)model.get("memberDao");
		// setter 메소드로 DI 받기때문에 더이상 Map객체에서 받아올 필요 없음 
		model.put("members", memberDao.selectList());
		return "/member/MemberList.jsp";
	}

}
