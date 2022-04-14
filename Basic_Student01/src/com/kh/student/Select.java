package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url,username,password);
			Statement stmt = con.createStatement()){
			
			/*
			// jordy 만 뽑아오기
			String sql = "select * from tbl_student where no = 4";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getDate(4));
			}
			*/
			
			// 모두 뽑아보기 
			String sql = "select * from tbl_student";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getDate(4));
				System.out.println("");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
