package prac01.test01.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import prac01.test01.annotation.Component;
import prac01.test01.bind.DataBinding;
import prac01.test01.dao.MemberDao;
import prac01.test01.vo.Member;

@Component("loginController")
public class LogInController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
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
		
		if(member.getEmail() == null) {
			return "/auth/LogInForm.jsp";
		}else {
			//MemberDao memberDao = (MemberDao) model.get("memberDao");

			member = memberDao.exist(member.getEmail(), member.getPassword());
			if(member != null) {
				HttpSession httpSession = (HttpSession) model.get("httpSession");
				httpSession.setAttribute("member", member);
				return "redirect:../member/list.do";
			}else {
				return "/auth/LogInFail.jsp";
			}
			
		}
	}

}
