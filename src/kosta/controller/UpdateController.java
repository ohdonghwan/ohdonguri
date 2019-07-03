package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class UpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		ModelAndView mv = new ModelAndView();
		
		String url="errorView/error.jsp";
		
		String modelName = request.getParameter("model_name");		
		int price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		String modelNum = request.getParameter("modelNum");
		String password = request.getParameter("password");
		
		Electronics elec = new Electronics(modelNum, modelName, price, description, password);
				
		try {
			ElectronicsService.update(elec);
			url = "elec?command=read&modelNum="+modelNum+"&flag=1";
			mv.setPath(url);
			mv.setRedirect(true);
		} catch (SQLException e) {			
			request.setAttribute("errorMsg", e.getMessage());
		}
		mv.setPath(url);
		return mv;
	}

}
