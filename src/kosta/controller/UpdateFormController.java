package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class UpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String modelNum = request.getParameter("modelNum");
		
		
		try {
			Electronics elec = ElectronicsService.selectByModelnum(modelNum, false);
			request.setAttribute("elec", elec);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setPath("elecView/update.jsp");
		
		return mv;
	}

}
