package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class ReadElecController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String modelNum = request.getParameter("modelNum");
		String flag = request.getParameter("flag");
		boolean state = flag==null ? true : false;
		
		ModelAndView mv = new ModelAndView();
		try {
			Electronics elec = ElectronicsService.selectByModelnum(modelNum, state);
			request.setAttribute("elec", elec);
			mv.setPath("elecView/read.jsp");
		} catch (SQLException e) {
			mv.setPath("errorView/error.jsp");
			request.setAttribute("errorMsg", "오류다이자슥아");
		}		
		return mv;
	}

}
