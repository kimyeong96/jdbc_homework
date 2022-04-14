package com.kh.student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;


public class Run {
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		
		while(true) {
		System.out.println("\n========= 학생 관리 프로그램 =========");
		System.out.println("1. 학생 등록"); // insert
		System.out.println("2. 학생 수정"); // update
		System.out.println("3. 학생 삭제"); // delete
		System.out.println("4. 학생 조회"); // select 
		System.out.println("5. 프로그램 종료");
		System.out.print(">> "); 
		int menu = Integer.parseInt(sc.nextLine()); // 메뉴 선택을 받음 
		
		if(menu == 1) {
			System.out.println("등록할 학생 이름을 입력하세요");
			System.out.print(">> ");
			String name = sc.nextLine();
			
			System.out.println("등록할 학생의 핸드폰 번호를 입력하세요");
			System.out.print(">> ");
			String phone = sc.nextLine();
			
			System.out.println("등록할 학생의 생일을 입력하세요(yyyy-mm-dd)");
			System.out.print(">> ");
			Date birth_date = Date.valueOf(sc.nextLine()); // String -> Date 변환
			
			StudentDTO dto = new StudentDTO(0,name,phone,birth_date);
			
			try {
				int rs = dao.insert(dto); 
				
				// 제대로 값을 받아 왔는지 확인
				if(rs > 0)  
					System.out.println("학생 추가 완료");
				else 
					System.out.println("학생 추가 실패");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정합니다.");
			}
		}
		
		else if(menu == 2) {
			System.out.println("수정할 학생의 번호를 입력하세요");
			System.out.print(">> ");
			int no =Integer.parseInt(sc.nextLine()); 
			
			System.out.println("수정할 학생 이름을 입력하세요");
			System.out.print(">> ");
			String name = sc.nextLine();
			
			System.out.println("수정할 학생의 전화번호를 입력하세요");
			System.out.print(">> ");
			String phone = sc.nextLine();
			
			System.out.println("수정할 학생의 생일을 입력하세요(yyyy-mm-dd)");
			System.out.print(">> ");
			Date birth_date = Date.valueOf(sc.nextLine());
			
			StudentDTO dto = new StudentDTO(no,name,phone,birth_date);
			
			try {
				int rs = dao.update(dto);
				
				// 제대로 값을 받아 왔는지 확인
				if(rs > 0)
					System.out.println("학생 수정 완료");
				else 
					System.out.println("학생 수정 실패");
			} catch (Exception e) {
				System.out.println("DB 접속이 불안정합니다.");
			}
		}
		
		else if(menu == 3) {
			// 단순하기에 객체 사용 x
			System.out.println("삭제할 학생의 번호를 입력하세요");
			System.out.print(">> ");
			int number = Integer.parseInt(sc.nextLine());
			
			try {
				int rs = dao.delete(number);
				if(rs > 0)
					System.out.println("학생 삭제 완료");
				else 
					System.out.println("학생 삭제 실패");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB 접속이 불안정합니다.");
			}
		}
		
		else if(menu == 4) {
			// 개별조회를 할지 전체 조회를 할지 선택
			System.out.println("\n1) 개별 조회");
			System.out.println("2) 전체 조회");
			System.out.print(">> ");
			int select = Integer.parseInt(sc.nextLine());
			
			if(select == 1) { // 개별조회 
				
				System.out.println("조회할 학생 번호를 입력");
				System.out.print(">> ");
				int no = Integer.parseInt(sc.nextLine());
				
				try {
					StudentDTO rs = dao.select(no);
					if(rs != null) // 객체가 null이 아니면 즉 객체의 내용이 있다면
						System.out.println(rs.toString());
					else 
						System.out.println("학생 조회 실패");
				} 
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("DB 접속이 불안정합니다.");
				}
			}
			else if(select == 2) { // 전체 조회를 선택
				
				try {
					// 반복문을 돌리기 위해 list생성 이때 타입은 selectAll()에서 반환되는 list 타입
					ArrayList<StudentDTO> list = dao.selectAll(); 
					
					if(list != null) { // list에 값이 존재한다면 
						for(StudentDTO dto : list) {
							System.out.println(dto.toString());
						}
					}
					else { // list에 값이 존재하지 않는다면
						System.out.println("데이터 조회 실패");
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("DB 접속이 불안정합니다.");
				}
			}
		}
		else if(menu == 5) {
			System.out.println("프로그램을 종료합니다");
			break;
		}
	}
}
}
