package Flight_reservation.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Flight_reservation.controller.Mcontroller;
import Flight_reservation.controller.Rcontroller;
import Flight_reservation.model.Airport;
import Flight_reservation.model.Schedule;

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
		while(true) {
		System.out.println("======================메인화면=====================");
		System.out.println("메뉴> 1. 로그인 2. 회원가입 3. 아이디찾기 4. 비번 찾기 5. 회원탈퇴");
		int ch = scanner.nextInt();
		if(ch==1) {login();}
		else if(ch==2) {signup();}
		else if(ch==3) {findid();}
		else if(ch==4) {findpw();}
		else if(ch==5) {deleteId();}
		}
	}
	
	// 로그인
	public void login() {
		/* -> 로그인 성공 시 사용자 메인이동 // 관리자 아이디 로그인시 관리자 페이지 이동*/
		System.out.println("======================로그인======================");
		System.out.println("로그인 아이디 : ");
		String mid = scanner.next();
		System.out.println("비밀번호 : ");
		String mpw = scanner.next();
		
		int result = Mcontroller.getInstance().login(mid, mpw);
		if(result == 1) { // 관리자 로그인 id :adimn / pw : 1234 
			System.out.println("관리자 로그인"); // 확인용
		}
		else if(result == 2) { // 일반로그인
			System.out.println("로그인되었습니다.");
			user_main(); // 사용자 메인페이지로 이동
		}
		else if(result == 0){ // 없는 아이디
			System.out.println("존재하지 않는 아이디 입니다.");
		}
		else {System.out.println("DB오류");} // 확인용
	}
	
	// 회원가입
	public void signup() {
		System.out.println("======================회원가입======================");
		System.out.println("회원가입할 아이디 : ");
		String mid = scanner.next();
		System.out.println("비밀번호 : ");
		String mpw = scanner.next();
		System.out.println("이름 : ");
		String mname = scanner.next();
		System.out.println("휴대폰 번호 : ");
		String mphone = scanner.next();
		
		boolean result = Mcontroller.getInstance().signup(mid , mpw ,  mname , mphone);
		
		if(result) {
			System.out.println("가입되었습니다.");
		}
		else {System.out.println("등록된 아이디입니다.");}
	}
	
	// 아이디찾기
	public void findid() {
		// 샘플 'qweqwe','1234','유재석','010-1111-1111'
		System.out.println("======================아이디 찾기======================");
		System.out.println("이름 : ");
		String mname = scanner.next();
		System.out.println("휴대폰 번호 : ");
		String mphone = scanner.next();
		
		String mid = Mcontroller.getInstance().findid(mname , mphone);
		if(mid != null) {
			System.out.print("찾으시는 아이디는 : ");
			System.err.print(mid);
			System.out.print(" 입니다.");
		}
		else {System.out.println("가입된 회원이 아닙니다.");}
	}
	
	// 비밀번호 찾기
	public void findpw() {
	// 샘플 'qweqwe','1234','유재석','010-1111-1111'
		System.out.println("======================비밀번호 찾기======================");
		System.out.println("아이디 : ");
		String mid = scanner.next();
		System.out.println("이름 : ");
		String mname = scanner.next();
		System.out.println("휴대폰 번호 : ");
		String mphone = scanner.next();
				
		String mpw = Mcontroller.getInstance().findpw(mid ,mname, mphone);
		if(mpw != null) {
			System.out.print("찾으시는 비밀번호는 : ");
			System.err.print(mpw);
			System.out.print(" 입니다.");
		}
		else {System.out.println("가입된 회원이 아닙니다.");}
	}
	
	// 회원탈퇴
	public void deleteId() {
		// 샘플 'qweqwe','1234','유재석','010-1111-1111'
		System.out.println("======================회원탈퇴======================");
		System.out.println("회원탈퇴할 아이디 : ");
		String mid = scanner.next();
		System.out.println("비밀번호 : ");
		String mpw = scanner.next();
		System.out.println("이름 : ");
		String mname = scanner.next();
		System.out.println("휴대폰 번호 : ");
		String mphone = scanner.next();
		
		boolean result = Mcontroller.getInstance().deleteId(mid , mpw ,  mname , mphone);
		
		if(result) {
			System.out.println("탈퇴되었습니다.");
		}
		else {System.out.println("잘못입력하셨습니다.");}
	}
	
	public void user_main() {
		System.out.println(" 1. 비행편 출력   2. 예약 확인 3. 로그아웃");
		int ch = scanner.nextInt();
		if(ch==1) {reservation();}
		else if(ch==2) {Myreser();}
		else if(ch==3) {return;}
	}
	
	public void reservation() {
		int dpno = 0;
		int apno = 0;
		Date dtime = null;
		int men = 0;
		Schedule schedule = null;
		while (true) {
			System.out.println("1. 츨발지 선택"+(dpno!=0 ?" : "+Rcontroller.getInstance().airportName(dpno) : ""));
			System.out.println("2. 도착지 선택"+(apno!=0 ? " : "+Rcontroller.getInstance().airportName(apno) : ""));
			System.out.println("3. 출발날짜 선택"+(dtime!=null ? dtime : ""));
			System.out.println("4. 인원 선택"+ (men!=0 ? men : ""));
			System.out.println("5. 비행편 검색"+(schedule!=null ? dtime : ""));
			System.out.println("6. 예약하기");
			System.out.println("7. 뒤로가기");
			
			int ch = scanner.nextInt();
			if(ch==1) {dpno = Departure();}
			else if(ch==2) {apno = Arrival(dpno);}
			else if(ch==3) {dateSelect();}
			else if(ch==4) {pSelect();}
			else if(ch==5) {flightSelect(dpno,apno,dtime,men);}
			else if(ch==6) {selectCompelete();}
			else if(ch==7) {return;}
		}
	}
	
	
	public int Departure() {
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();
		
		System.out.println("공항번호\t공항이름\t나라");
		ArrayList<Airport> airportList = Rcontroller.getInstance().Departure(pnation);
		for(Airport a : airportList) {
			System.out.println(a.getPno() +"\t"+ a.getPname() + "\t" +a.getPnation());
		}
		System.out.println("공항 번호를 입력하세요.");
		int dpno = scanner.nextInt();
		return dpno;
	}
	
	public int Arrival(int dpno) {
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();
		
		System.out.println("공항번호\t공항이름\t나라");
		ArrayList<Airport> airportList = Rcontroller.getInstance().Arrival(pnation,dpno);
		for(Airport a : airportList) {
			System.out.println(a.getPno() +"\t"+ a.getPname() + "\t" +a.getPnation());
		}
		System.out.println("공항 번호를 입력하세요.");
		int apno =scanner.nextInt();
		return apno;
	}
	public void dateSelect() {
		
	}
	
	public void pSelect() {
		
	}
	
	public void flightSelect(int dpno,int apno,Date dtime,int men) {
		
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