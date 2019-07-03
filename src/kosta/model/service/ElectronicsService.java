package kosta.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.model.dao.ElectronicsDAO;
import kosta.model.dao.ElectronicsDAOImpl;
import kosta.model.dto.Electronics;
import kosta.util.DbUtil;


public class ElectronicsService {

	private static ElectronicsDAO elecDAO  = new ElectronicsDAOImpl();
	/**
	 * ElectronicsDAOImpl의 모든레코드 검색하는 메소드 호출
	 * */
	public static List<Electronics> selectAll() throws SQLException{
		return elecDAO.selectAll();
	}
	  
	  /**
	   * ElectronicsDAOImpl의 레코드 삽입하는 메소드 호출
	   * */
	public static int insert(Electronics electronics) throws SQLException{
		int result =elecDAO.insert(electronics);
		if(result==0) throw new SQLException("등록되지 않았습니다");		
		return result;
		//깃허브체크
	}
	 
	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 검색하는 메소드 호출
	   * @param : boolean flag - 조회수 증가 여부를 판별하는 매개변수임(true이면 조회수증가 / false이면 조회수 증가 안함)
	   * */
	public static Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		if(flag) {
			int result = elecDAO.increamentByReadnum(modelNum);
			if(result==0)throw new SQLException("조회수 증가에 오류가 발생했습니다.");		
		}
		Electronics electronics = elecDAO.selectByModelNum(modelNum);
		if(electronics==null)throw new SQLException(modelNum+"에 해당하는 정보가 없습니다.");
		return electronics;
		
	}
		
		
		 
		 //글번호에 해당하는 게시물 검색
		
	 /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 삭제 메소드 호출
	   * */
	  public static int delete(String modelNum, String password) throws SQLException{
		  Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		  if(!dbElec.getPassword().equals(password)) {
			  throw new SQLException("비밀번호 오류입니다.");			  
			  }
		  int result = elecDAO.delete(modelNum, password);	
		  if(result==0)throw new SQLException("삭제가 안됐습니다.");
		  return result;
	  }
	  

	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 수정  메소드 호출
	   * */
	  public static int update(Electronics electronics) throws SQLException{
		  Electronics dbElec = elecDAO.selectByModelNum(electronics.getModelNum());
		  
		  if(!dbElec.getPassword().equals(electronics.getPassword())) {
			  throw new SQLException("비밀번호를 다시 확인해주세요");
		  }
		  int result = elecDAO.update(electronics);
		  if(result==0)throw new SQLException("수정 오류 발생");
		  return result;
	  }
	
	  
	  
}



