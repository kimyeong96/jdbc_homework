package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Delete {	
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement()	){
			
			
			String sql = "delete from tbl_student where no = 2";
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) {
				System.out.println("데이터 삭제 성공");
			}
			else {
				System.out.println("데이터 삭제 실패");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}








