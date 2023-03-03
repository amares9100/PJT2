package Flight_reservation.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


import Flight_reservation.controller.Acontroller;
import Flight_reservation.controller.Mcontroller;
import Flight_reservation.controller.Rcontroller;
import Flight_reservation.model.Airport;
import Flight_reservation.model.Reservation;
import Flight_reservation.model.Schedule;
import Flight_reservation.model.LP;
import Flight_reservation.model.Member;

public class Front {
	
	private static Front front = new Front(); 
	private Front() {};
	public static Front getInstance() {return front;}
	
	Scanner scanner = new Scanner(System.in);  

	public void index() {
		while(true) {
			System.out.println("======================메인화면=====================");
			System.out.println("메뉴> 1. 로그인 2. 회원가입 3. 아이디찾기 4. 비번 찾기 5. 회원탈퇴");
			try {
				int ch = scanner.nextInt();
				if(ch==1) {login();}
				else if(ch==2) {signup();}
				else if(ch==3) {findid();}
				else if(ch==4) {findpw();}
				else if(ch==5) {Afront.getInstance().admin_main();}
				else if(ch==5) {deleteId();}
			} catch (Exception e) {
				System.out.println(e);
				scanner = new Scanner(System.in);
			}
		
		}

	}
	
	// 로그인
	public void login() throws Exception{
		/* -> 로그인 성공 시 사용자 메인이동 // 관리자 아이디 로그인시 관리자 페이지 이동*/
		System.out.println("======================로그인======================");
		System.out.println("로그인 아이디 : ");
		String mid = scanner.next();
		System.out.println("비밀번호 : ");
		String mpw = scanner.next();
		
		int result = Mcontroller.getInstance().login(mid, mpw);
		if(result == 1) { // 관리자 로그인 id :adimn / pw : 1234 
			System.out.println("관리자 로그인"); // 확인용
			Afront.getInstance().admin_main();
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
	
	// 회원가입  0:회원가입 1:아이디중복 2:핸드폰입력이 잘못됨 3:DB오류 4:아아디 입력 오류
	public void signup() throws Exception{
		System.out.println("======================회원가입======================");
		System.out.println("회원가입할 아이디 : (영문대소문자만)");
		String mid = scanner.next();
		System.out.println("비밀번호 : ");
		String mpw = scanner.next();
		System.out.println("이름 : ");
		String mname = scanner.next();
		System.out.println("휴대폰 번호 : xxx-xxxx-xxxx입력 ");
		String mphone = scanner.next();
		System.out.println("주민등록번호 : xxxxxx-xxxxxxx");
		String rrn = scanner.next();
		System.out.println("성별 : 남자/여자");
		String gender = scanner.next();
		
		int result = Mcontroller.getInstance().signup(mid , mpw ,  mname , mphone , rrn , gender);
		
		if(result == 0) {
			System.out.println("가입되었습니다.");
		}
		else if(result == 1){System.out.println("등록된 아이디입니다.");}
		else if(result == 2) {System.out.println("핸드폰 입력이 잘못되었습니다.");}
		else if(result == 4) {System.out.println("아이디는 영문대소문자만 입력하세요.");}
		else {System.out.println("DB오류");}
	}
	
	// 아이디찾기
	public void findid() throws Exception{
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
	public void findpw() throws Exception{
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
	public void deleteId() throws Exception{
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
		while (true) {
			Member m = Mcontroller.getInstance().memberTier();
			System.out.println("안녕하세요 "+m.getMname()+"님 회원님의 등급은 "+m.getTier()+"["+m.getMileage()+"점]"+"입니다. ");
			System.out.println(" 1. 비행편 출력   2. 예약 확인 3. 로그아웃");
			try {
				int ch = scanner.nextInt();
				if(ch==1) {Rfront.getInstance().reservation();}
				else if(ch==2) {Myreser();}
				else if(ch==3) {return;}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	
	public void Myreser() throws Exception{ // 이경석
		ArrayList<Reservation> rlist = Mcontroller.getInstance().Myreser();
		
		System.out.println("============ 예약내역 출력 ===========");
		for(Reservation re : rlist) { // 비행표는 예약번호
			System.out.printf("예약번호: %d \n항공사 : %s \n항공편 : %s -> %s \n"
							+ "비행일 : %s \n인원 : %d 명 \n"
							+ "결제 가격 : %d -> 등급 할인 가격 %d\n"
							+ "예상적립 마일리지 : %d\n",
							re.getRno(), re.getLname() , re.getDeparture(), re.getArrival()
							,re.getDate() ,re.getMen(), re.getTprice(), 
							//할인 가격 = 표 가격 - (표가격 * 할인가)
							re.getTprice() -  (int)(Math.floor(re.getDiscount()*re.getTprice()))   , 
							//마일리지 적립 = 표가격 * 마일리지비율
							(int)(Math.floor(re.getTprice() * re.getArate()))
							);
			
			//비행표가 2표면 보기쉽게 분리
			System.out.println("-----------------------------");
		}
		
		System.out.println("1. 예약취소 2. 뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {MYcancle();} 
		else if(ch==2) {return;}
	}
	
	public void MYcancle() throws Exception{ // 이경석
		System.out.println("------------ 예약취소 ------------");
		System.out.println("몇번째 비행표를 취소하시겠습니까?");
		int ch2 = scanner.nextInt();
		
		boolean result = Mcontroller.getInstance().MYcancle(ch2);
		if(result) {System.out.println("[알림] 예약을 취소했습니다.");}
		else {System.out.println("[알림] 예약을 취소하지 못했습니다!.");}
		
	}
	

	
}