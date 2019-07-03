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
		
		//넘어오는 값 받기
		String modelNum = m.getParameter("model_num");
		String modelName = m.getParameter("model_name");
		int price = Integer.parseInt(m.getParameter("price"));
		String description = m.getParameter("description"); 
		String password = m.getParameter("password");		
		
		Electronics elec = new Electronics(modelNum, modelName, price, description, password);
		
		//파일첨부가 되었다면 ... 파일이름, 파일크기를 저장
		if(m.getFilesystemName("file")!=null) {
			//파일 이름
			elec.setFname(m.getFilesystemName("file"));
			//파일 크기
			elec.setFsize((int) m.getFile("file").length());
		}
		
		//
		try {
			ElectronicsService.insert(elec);
			mv.setPath("elec"); //list.jsp로 간다는 말이다.
			mv.setRedirect(true);
		} catch (SQLException e) {
			mv.setPath("errorView/error.jsp");
			request.setAttribute("errorMsg", e.getMessage());
		}		
		return mv;
	}

}
