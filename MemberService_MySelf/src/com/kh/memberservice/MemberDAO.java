package com.kh.memberservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class MemberDAO {
	
	private BasicDataSource bds = new BasicDataSource();
	
	public MemberDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(200);
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	
	
	public int singUp(MemberDTO dto) throws Exception {
		
		String sql = "insert into member_tbl values(?,?,?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getNickname());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public void delete(String id) throws Exception {
		
		String sql = "delete from member_tbl where id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}
	}
	
	
	public ArrayList<MemberDTO> loginCheck() throws Exception {
		String sql = "select * from member_tbl";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ArrayList<MemberDTO> list = new ArrayList();
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String nickname = rs.getString(3);
				list.add(new MemberDTO(id,pw,nickname));
			}
			return list;
			
		}
	}
	
	
	
	
	
}
