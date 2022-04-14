package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		

		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			String sql ="insert into tbl_student values(seq_std.nextval,'jordy','010-6543-7456','1979/03/24')";
		
			// String sql ="insert into tbl_student values(seq_std.nextval,'sam','010-5555-4444',to_date('1990/05/05')";
			
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) { 
				System.out.println("데이터 추가 성공");
			}
			else {
				System.out.println("데이터 추가 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}


