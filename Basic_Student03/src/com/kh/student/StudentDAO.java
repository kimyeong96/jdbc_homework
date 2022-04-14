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
				return dto; // 객체에 값을 담고 return으로 전달
			}
			
		}
		return null; // rs.next()가 없다면 return 으로 null을 반환
	}
	
	// return 값을 list형으로 주려고 ArrayList 사용 (전체를 출력해야 하기에 반복문 사용시 필요)
	public ArrayList<StudentDTO> selectAll() throws Exception{
		
		String sql = "select * from tbl_student";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<StudentDTO> list = new ArrayList<>();
			
			while(rs.next()) { // 데이터가 존재할 때까지 반복
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Date birth_date = rs.getDate(4);
				list.add(new StudentDTO(no,name,phone,birth_date));
			}
			return list; // 여기서 return값이 while문 안으로 들어가면 list값이 초기화 되버림 -> while 밖에 선언 
		}
	}
	
}
