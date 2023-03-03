package Flight_reservation.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Flight_reservation.model.Mdao;
import Flight_reservation.model.Reservation;
import Flight_reservation.model.Member;

public class Mcontroller {
	private static Mcontroller mcontroller = new Mcontroller();
	private Mcontroller() {};
	public static Mcontroller getInstance() {return mcontroller;}
	
	
	private static int loginsession = 0;
	
	public static int getLoginsession() {
		return loginsession;
	}
	public static void setLoginsession(int loginsession) {
		Mcontroller.loginsession = loginsession;
	}
	
	
	//로그인
	public int login( String mid , String mpw  ) {
		/* -> 로그인 성공 시 사용자 메인이동 // 관리자 아이디 로그인시 관리자 페이지 이동*/
		loginsession = Mdao.getInstance().login(mid, mpw);
		if(loginsession == 1) { 
			return 1; // 관리자 반환
		}
		else if(loginsession > 1) {
			return 2; // 일반 로그인
		}
		else if(loginsession == 0) {
			return 0;// 해당없으면 0 반환
		}
		else {return -1;}
		
	}
	
	//회원가입 0:회원가입 1:아이디중복 2:핸드폰입력이 잘못됨 3:DB오류
	public int signup(String mid , String mpw , String mname , String mphone , String rrn , String gender) {
		
		boolean result = Mdao.getInstance().idCheck(mid); // 중복 아이디 체크
		 if(result) { // 아이디가 중복일때
			 return 1;
		 }
		 
		 else if(!check_id(mid)){return 4;}
		 else if(!check_phone(mphone)) {
			 Member memberdto =  new Member(mid , mpw , mname , mphone , rrn , gender); // 입력값 객체화
			 	return Mdao.getInstance().signup(memberdto); // 리턴값 그대로 리턴}
		 }
		 else {return 2;}
	}
	
	//아이디 찾기
	public String findid(String mname , String mphone) {
		
		return Mdao.getInstance().findid(mname, mphone);
	}
	
	//비밀번호 찾기
	public String findpw(String mid , String mname , String mphone) {
		
		return Mdao.getInstance().findpw(mid, mname , mphone);
	}
	
	//회원탈퇴
	public boolean deleteId(String mid , String mpw , String mname , String mphone) {
		Member memberdto =  new Member(mid , mpw , mname , mphone);
		return Mdao.getInstance().deleteId(memberdto);
		
	}
	
	
	//예약 내역 확인
	public ArrayList<Reservation> Myreser() {
		//등록된 회원의 예약내역을 출력해야 함으로.
		return Mdao.getInstance().Myreser(loginsession);
	}
	
	//예약 취소
	public boolean MYcancle(int rno) {
		return Mdao.getInstance().MYcancle(loginsession, rno);	
	}
	
	//회원 등급안내
	public Member memberTier() {
		return Mdao.getInstance().memberTier(loginsession);
	}
	
	// -------------------------------------------------------------//
	// 유효성검사
	
	public boolean check_phone(String mphone) {
	   return Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", mphone);
	}
	
	public boolean check_id(String id) {
		return Pattern.matches("^[a-zA-Z]*$", id);
	}
	

	
}





















