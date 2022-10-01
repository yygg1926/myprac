package prac01.test01.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prac01.test01.bind.DataBinding;
import prac01.test01.bind.ServletRequestDataBinder;
import prac01.test01.controls.Controller;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			
			HashMap<String, Object> model = new HashMap<String,Object>();
			//model.put("memberDao", sc.getAttribute("memberDao"));
			//Listener에서 ServletContext에 각 URL별로 컨트롤러를 선언하여 Dao inject 해
			model.put("httpSession", request.getSession());
			
//			Controller pageController = null;
//			String pageControllerPath = null;
			Controller pageController = (Controller)sc.getAttribute(servletPath);

//			if ("/member/list.do".equals(servletPath)) {
////				pageControllerPath = "/member/list";
////				pageController = new MemberListController();
//			} else if ("/member/add.do".equals(servletPath)) {
////				pageControllerPath = "/member/add";
////				pageController = new MemberAddController();
//				if (request.getParameter("email") != null) {
//					model.put("member", new Member()
//							.setEmail(request.getParameter("email"))
//							.setPassword(request.getParameter("password"))
//							.setName(request.getParameter("name")));
//				}
//			} else if ("/member/update.do".equals(servletPath)) {
////				pageControllerPath = "/member/update";
////				pageController = new MemberUpdateController();
//				if (request.getParameter("email") != null) {
//					model.put("member", new Member()
//							.setNo(Integer.parseInt(request.getParameter("no")))
//							.setEmail(request.getParameter("email"))
//							.setName(request.getParameter("name")));
//				}else {
//					model.put("no", request.getParameter("no"));
//				}
//			} else if ("/member/delete.do".equals(servletPath)) {
////				pageControllerPath = "/member/delete";
////				pageController = new MemberDeleteController();
//				model.put("no", request.getParameter("no"));
//			} else if ("/auth/login.do".equals(servletPath)) {
////				pageControllerPath = "/auth/login";
////				pageController = new LogInController();
//				if(request.getParameter("email") != null) {
//				model.put("member", new Member()
//						.setEmail(request.getParameter("email"))
//						.setPassword(request.getParameter("password")));
//				}
//			} else if ("/auth/logout.do".equals(servletPath)) {
////				pageControllerPath = "/auth/logout";
////				pageController = new LogOutController();
//			}
//
////			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
////			rd.include(request, response);
////
////			String viewUrl = (String) request.getAttribute("viewUrl");
//			
			// DataBinding 구현으로 조건문 제거
			
			if(pageController instanceof DataBinding) {
				// instanceof == 뒤에 것을 구현한 클래스인 경우에만 실행 
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;

			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
	
	private void prepareRequestData(HttpServletRequest req, HashMap<String, Object> model, DataBinding dataBinding) throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		for(int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i+1];
			dataObj = ServletRequestDataBinder.bind(req, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}

}
