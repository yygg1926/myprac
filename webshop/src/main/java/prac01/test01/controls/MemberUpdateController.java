package prac01.test01.controls;

import java.util.Map;

import prac01.test01.bind.DataBinding;
import prac01.test01.dao.MemberDao;
import prac01.test01.vo.Member;

public class MemberUpdateController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"member", prac01.test01.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
//		MemberDao memberDao = (MemberDao) model.get("memberDao");
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			Integer no = Integer.parseInt(String.valueOf(model.get("no")));
			member = memberDao.selectOne(no);
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
		}else {
			
			memberDao.update((Member) model.get("member"));
			return "redirect:list.do";
		}
	}

}
