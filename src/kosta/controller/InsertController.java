package kosta.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kosta.model.dao.ElectronicsDAO;
import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class InsertController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ModelAndView mv = new ModelAndView();
		
		ServletContext application = request.getServletContext();
		String saveDir = application.getRealPath("/save");
		int maxSize = 1024*1024*100; //100mb
		String encoding = "UTF-8";
		
		MultipartRequest m = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		
		//�Ѿ���� �� �ޱ�
		String modelNum = m.getParameter("model_num");
		String modelName = m.getParameter("model_name");
		int price = Integer.parseInt(m.getParameter("price"));
		String description = m.getParameter("description"); 
		String password = m.getParameter("password");		
		
		Electronics elec = new Electronics(modelNum, modelName, price, description, password);
		
		//����÷�ΰ� �Ǿ��ٸ� ... �����̸�, ����ũ�⸦ ����
		if(m.getFilesystemName("file")!=null) {
			//���� �̸�
			elec.setFname(m.getFilesystemName("file"));
			//���� ũ��
			elec.setFsize((int) m.getFile("file").length());
		}
		
		//
		try {
			ElectronicsService.insert(elec);
			mv.setPath("elec"); //list.jsp�� ���ٴ� ���̴�.
			mv.setRedirect(true);
		} catch (SQLException e) {
			mv.setPath("errorView/error.jsp");
			request.setAttribute("errorMsg", e.getMessage());
		}		
		return mv;
	}

}
