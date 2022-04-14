package com.kh.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "kh";
	private String password = "kh";
	
	public int insert(StudentDTO dto) throws Exception {
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement();){
			
			String sql ="insert into tbl_student values(seq_std.nextval,'" + 
					dto.getName() + "'," + 
					dto.getPhone() + ",'" + 
					dto.getBirth_date() + "')";
			
			int rs = stmt.executeUpdate(sql); // 변경된 횟수의 값
			return rs;
		}
	}
	
	public int update(StudentDTO dto) throws Exception {
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement()){
			
			String sql ="update tbl_student set name ='" + 
					dto.getName() + "'," + " phone = '" + 
					dto.getPhone() +"', " + "birth_date = '" + 
					dto.getBirth_date() + "' where no =" + 
					dto.getNo();
			
			int rs = stmt.executeUpdate(sql);
			return rs;
			
		}
	}
	
	public int delete(int number) throws Exception {
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement();){
			
			String sql = "delete from tbl_student where no = "+ number ;
			int rs = stmt.executeUpdate(sql);
			return rs;
			
		}
	}
	
	public StudentDTO select(int number) throws Exception {
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement();){
			
			String sql = "select * from tbl_student where no = " + number;
			ResultSet rs = stmt.executeQuery(sql);
			
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
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement();){
			
			String sql = "select * from tbl_student";
			ResultSet rs = stmt.executeQuery(sql);
			
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
