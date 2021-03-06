package com.kh.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "kh";
	private String password = "kh";
	
	public int insert(StudentDTO dto) throws Exception {
		
		String sql = "insert into tbl_student values (seq_std.nextval, ? , ? , ?)";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setDate(3, dto.getBirth_date());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public int update(StudentDTO dto) throws Exception {
		
		String sql = "update tbl_student set name = ?, phone = ?, birth_date = ? where no = ?";
		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setDate(3, dto.getBirth_date());
			pstmt.setInt(4, dto.getNo());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public int delete(int number) throws Exception {
		
		String sql = "delete from tbl_student where no = ?";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, number);
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public StudentDTO select(int number) throws Exception {
		
		String sql = "select * from tbl_student where no = ?";

		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
		
			pstmt.setInt(1, number);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date birth_date = rs.getDate(4);
				StudentDTO dto = new StudentDTO(no,name,phone,birth_date);
				return dto; // ????????? ?????? ?????? return?????? ??????
			}
			
		}
		return null; // rs.next()??? ????????? return ?????? null??? ??????
	}
	
	// return ?????? list????????? ????????? ArrayList ?????? (????????? ???????????? ????????? ????????? ????????? ??????)
	public ArrayList<StudentDTO> selectAll() throws Exception{
		
		String sql = "select * from tbl_student";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<StudentDTO> list = new ArrayList<>();
			
			while(rs.next()) { // ???????????? ????????? ????????? ??????
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date birth_date = rs.getDate(4);
				list.add(new StudentDTO(no,name,phone,birth_date));
			}
			return list; // ????????? return?????? while??? ????????? ???????????? list?????? ????????? ????????? -> while ?????? ?????? 
		}
	}
	
}
