package prac01.test01.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import prac01.test01.annotation.Component;

@Component("/auth/logout.do")
public class LogOutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession httpSession = (HttpSession) model.get("httpSession");
		httpSession.invalidate();
		return "redirect:login.do";
	}

}
