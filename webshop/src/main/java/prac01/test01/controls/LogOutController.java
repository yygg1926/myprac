package prac01.test01.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class LogOutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession httpSession = (HttpSession) model.get("httpSession");
		httpSession.invalidate();
		return "redirect:login.do";
	}

}
