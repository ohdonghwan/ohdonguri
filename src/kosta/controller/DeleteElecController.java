package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.service.ElectronicsService;

public class DeleteElecController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String password = request.getParameter("password");
		String modelNum = request.getParameter("modelNum");
		
		ModelAndView mv = new ModelAndView();
		try {
			ElectronicsService.delete(modelNum, password);
			mv.setPath("elec");
			mv.setRedirect(true);
		} catch (SQLException e) {
			mv.setPath("errorView/error.jsp");
			request.setAttribute("errorMsg", "비밀번호가 틀렸습니다.");
		}				
		return mv;
	}

}
