package kosta.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dao.ElectronicsDAO;
import kosta.model.dao.ElectronicsDAOImpl;
import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;
/**
 * ��ü�˻�
 * */
public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mv = new ModelAndView();
		try {
			List<Electronics> list = ElectronicsService.selectAll();
			request.setAttribute("list", list);
		} catch (SQLException e) {
			mv.setPath("errorView/error.jsp");
			request.setAttribute("errorMsg", "�˼��մϴ� �����Դϴ�");
		}		
		mv.setPath("elecView/list.jsp");
		return mv;
	}

}
