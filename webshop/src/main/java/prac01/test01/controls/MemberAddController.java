package prac01.test01.controls;

import java.util.Map;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.MemberDao;
import prac01.test01.vo.Member;

@Component("memberAddController")
public class MemberAddController implements Controller, DataBinding {

	MemberDao memberDao;

	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Object[] getDataBinders() {
		return new Object[] {
				"member", prac01.test01.vo.Member.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
//		if (model.get("member") == null) {
		if(member.getEmail() == null) {
			return "/member/MemberAddForm.jsp";
		} else {
//			MemberDao memberDao = (MemberDao)model.get("memberDao");
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}

}
