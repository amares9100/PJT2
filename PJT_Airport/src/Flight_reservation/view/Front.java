package Flight_reservation.view;

import java.util.Scanner;

public class Front {
	
	private static Front front = new Front();
	private Front() {};
	public static Front getInstance() {return front;}
	
	Scanner scanner = new Scanner(System.in);  
	
	/*
	login
	signup
	findid
	findpw
	---------
	reservation   -> 선택화면 출력 (출/도 선택창)
	Myreser      -> 예약 내역 출력 
	MYcancle      -> 에약 취소
	Departure   -> 공항 목록 출력
	Arrival      -> 공항 목록 출력
	dateSelect   -> 날짜 입력 받기?
	pSelect      -> 인원 입력 받기?
	flightSelect    -> 검색된 비행편 출력
	compelete   -> 선택한 내역 출력 
	payment      -> 결제 방법 출력?

	관리자---------------------------------------------------
	manager      -> 
	scheduleRegister   -> 스케쥴 등록
	scheduleUpdate   -> 스케쥴 수정
	scheduleDelete   -> 스케쥴 삭제
	*/
	
	public void index() {
		System.out.println("메뉴> 1. 로그인 2. 회원가입 3. 아이디찾기 4. 비번 찾기");
		int ch = scanner.nextInt();
		if(ch==1) {login();}
		else if(ch==2) {signup();}
		else if(ch==3) {findid();}
		else if(ch==4) {findpw();}
	}
	
	public void login() {
		
		
		/* -> 로그인 성공 시 사용자 메인이동 // 관리자 아이디 로그인시 관리자 페이지 이동*/
	}
	
	public void signup() {
		
	}
	
	public void findid() {
		
	}
	
	public void findpw() {
		
	}
	
	public void user_main() {
		System.out.println(" 1. 비행편 출력   2. 예약 확인 3. 로그아웃");
		int ch = scanner.nextInt();
		if(ch==1) {reservation();}
		else if(ch==2) {Myreser();}
		else if(ch==3) {return;}
	}
	
	public void reservation() {
		System.out.println(" 1. 출발지 선택    : 김포공항\r\n"
				+ " 2. 도착지 선택    : 제주공항\r\n"
				+ " 3. 비행날짜 선택    : 2023-02-24\r\n"
				+ " 4. 인원선택    : 2명\r\n"
				+ " 5. 비행편 검색    : \r\n"
				+ " 6. 예약하기\r\n"
				+ " 7. 뒤로가기\r\n"
				+ "");
		int ch = scanner.nextInt();
		if(ch==1) {Arrival();}
		else if(ch==2) {Departure();}
		else if(ch==3) {dateSelect();}
		else if(ch==4) {pSelect();}
		else if(ch==5) {flightSelect();}
		else if(ch==6) {selectCompelete();}
		else if(ch==7) {return;}
	}
	
	public void Arrival() {
		
	}
	
	public void Departure() {
		
	}
	
	public void dateSelect() {
		
	}
	
	public void pSelect() {
		
	}
	
	public void flightSelect() {
		
	}
	
	public void selectCompelete() {
		System.out.println("1. 결제방법 선택 2. 뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {payment();} 
		else if(ch==2) {return;}
	}
	
	public void payment() {
		
	}
	
	public void Myreser() {
		System.out.println("1. 예약취소 2. 뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {MYcancle();} 
		else if(ch==2) {return;}
	}
	
	public void MYcancle() {
		
	}
	
	public void admin_main() {
		System.out.println("1. 비행 확인  2. 비행 등록 3. 비행 수정 4. 비행 삭제 5.뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {schedulePrint();}
		else if(ch==2) {scheduleRegister();}
		else if(ch==3) {scheduleUpdate();}
		else if(ch==4) {scheduleDelete();}
		else if(ch==5) {return;}
	}
	
	public void schedulePrint() {
		
	}
	
	public void scheduleRegister() {
		
	}
	
	public void scheduleUpdate() {
		
	}
	
	public void scheduleDelete() {
		
	}
	
}
