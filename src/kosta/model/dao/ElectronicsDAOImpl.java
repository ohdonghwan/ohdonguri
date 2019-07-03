package kosta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.model.dto.Electronics;
import kosta.util.DbUtil;

public class ElectronicsDAOImpl implements ElectronicsDAO {

	@Override
	public List<Electronics> selectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Electronics> list = new ArrayList<>();
		String sql = "select * from electronics";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Electronics elec = new Electronics(
						rs.getString(1),rs.getString(2),rs.getInt(3),
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getInt(7),rs.getString(8),rs.getInt(9));
				
				list.add(elec);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}		
		return list;		
	}

	@Override
	public Electronics selectByModelNum(String modelNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Electronics elec = null;
		String sql = "select * from electronics where model_num = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
			elec = new Electronics(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}		
		
		return elec;
	}

	@Override
	public int increamentByReadnum(String modelNum) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		
		String sql = "update electronics set readnum = (readnum+1) where model_num = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(ps, con);
		}
		
		return result;
	}

	@Override
	public int insert(Electronics electronics) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;		
		int result = 0;
		
		String sql = "insert into electronics values(?, ?, ?, ?, ?, sysdate, ?, ?, ?)"; // 6번째 날짜니까 null
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelNum());
			ps.setString(2, electronics.getModelName());
			ps.setInt(3, electronics.getPrice());
			ps.setString(4, electronics.getDescription());
			ps.setString(5, electronics.getPassword());
			ps.setInt(6, electronics.getReadnum());
			ps.setString(7, electronics.getFname());
			ps.setInt(8, electronics.getFsize());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(ps, con);
		}		
		return result;	
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;		
		int result = 0;
		String sql = "delete from electronics where model_num = ? and password = ?";
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);			
			ps.setString(2, password);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(ps, con);
		}		
		return result;	
	}

	@Override
	public int update(Electronics electronics) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;		
		int result = 0;
		
		String sql = "update electronics set model_name = ?, price = ?, description = ? where model_num = ? and password = ?"; // 6번째 날짜니까 null
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, electronics.getModelName());
			ps.setInt(2, electronics.getPrice());
			ps.setString(3, electronics.getDescription());
			
			ps.setString(4, electronics.getModelNum());
			ps.setString(5, electronics.getPassword());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(ps, con);
		}		
		return result;	
		
	}

}
