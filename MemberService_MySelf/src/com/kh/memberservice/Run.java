package com.kh.memberservice;

import java.util.ArrayList;
import java.util.Scanner;


public class Run {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MemberDAO dao = new MemberDAO();
		
		while(true) {
		System.out.println("\n**** 멤버 서비스 프로그램 **** ");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 회원탈퇴");
		System.out.println("4. 프로그램 종료");
		System.out.print(">> "); int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1) {
			System.out.print("\n아이디를 입력하세요 >> "); String id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 >> "); String pw = sc.nextLine();
				try {
					ArrayList<MemberDTO> list = dao.loginCheck();
					if(list != null) { // list에 값이 존재한다면 
						for(MemberDTO dto : list) {
							if(dto.getId().equals(id)&&dto.getPw().equals(pw)) { 
								System.out.println("\n" + dto.getNickname() +"님 환영합니다");
								System.out.println("오늘의 날씨는 흐림, 평균 온도는 12.5도입니다.");
								continue;
							}else {
								System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
							}
						}
					}
					else {
						System.out.println("데이터 조회 실패");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
		}else if(menu == 2) {
			System.out.print("생성할 아이디 입력 >> "); String id = sc.nextLine();
			try {
				ArrayList<MemberDTO> list = dao.loginCheck();
				if(list != null) { 
					for(MemberDTO dto : list) {
						if(!(dto.getId().equals(id))) { 
							System.out.println("사용가능한 아이디입니다.");
							System.out.print("비밀번호 입력 >> "); String pw = sc.nextLine();
							System.out.print("닉네임 입력 >> "); String nickname = sc.nextLine();
							MemberDTO signUpdto = new MemberDTO(id,pw,nickname);
							try {
								int rs = dao.singUp(signUpdto);
								if(rs > 0 )
									System.out.println("\n회원가입 성공");
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("서버 불안");
							}
						}else if(dto.getId().equals(id)) {
							System.out.println("중복된 아이디입니다. 다시 입력하세요.");
							break;
						}
					}
				}
				else {
					System.out.println("데이터 조회 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 


		}else if(menu == 3) {
			System.out.print("탈퇴할 회원의 아이디 입력 >> "); String id = sc.nextLine();
			System.out.print("탈퇴할 회원의 비밀번호 입력 >> "); String pw = sc.nextLine();
			try {
				ArrayList<MemberDTO> list = dao.loginCheck();
				if(list != null) { 
					for(MemberDTO dto : list) {
						if(dto.getId().equals(id)&&dto.getPw().equals(pw)) {
							System.out.print(dto.getNickname() + "님 정말 탈퇴하시겠습니까?(Y/N 입력)");
							String menu2 = sc.nextLine();
							if(menu2.equals("Y")) {
								System.out.println("회원 삭제 완료");
								dao.delete(id);
								System.out.println("그동안 사용해주셔서 감사합니다!");
							}else if(menu2.equals("N")) {
								System.out.println("남아주셔서 감사합니다!");
								break;
							}
						}else if(!(dto.getId().equals(id)&&dto.getPw().equals(pw))) {
							System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
							break;
						}
					}
				}
				else {
					System.out.println("데이터 조회 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		}else if(menu == 4) {
			System.out.println("프로그램을 종료합니다");
		
		}
		}
	
	}
}
