package Flight_reservation.controller;

import Flight_reservation.model.Mdao;

public class Mcontroller {
	private static Mcontroller mcontroller = new Mcontroller();
	private Mcontroller() {};
	public static Mcontroller getInstance() {return mcontroller;}
	
	
	int loginsession = 0;
	//로그인
	public int login( String mid , String mpw  ) {
		/* -> 로그인 성공 시 사용자 메인이동 // 관리자 아이디 로그인시 관리자 페이지 이동*/
		int loginsession = Mdao.getInstance().login(mid, mpw);
		
		
		
		
		
		return 0;
	}
	
	//회원가입
	public void signup() {
		
	}
	
	//아이디 찾기
	public void findid() {
		
	}
	
	//비밀번호 찾기
	public void findpw() {
		
	}
	
	//예약 내역 확인
	public void Myreser() {
	}
	
	//예약 취소
	public void MYcancle() {
		
	}
}
