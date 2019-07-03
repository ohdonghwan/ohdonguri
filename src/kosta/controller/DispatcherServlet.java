package kosta.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/elec")
public class DispatcherServlet extends HttpServlet {
	Map<String, Controller> map;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = super.getServletContext();
		map = (Map<String, Controller>)application.getAttribute("map");	
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("command"); // 우리의 키가 된다.
		if(key==null) key="list";//list는 전체검색
		
		
		Controller controller = map.get(key);
		//검색, 수정, 등록
		ModelAndView mv = controller.handleRequest(request, response);
		
		if(mv.isRedirect()) { //redirect로 이동
			response.sendRedirect(mv.getPath());
		}else {
			request.getRequestDispatcher(mv.getPath()).forward(request, response);
		}
		
	}

}
