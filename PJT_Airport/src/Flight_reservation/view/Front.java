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

public class Front {
	
	private static Front front = new Front(); 
	private Front() {};
	public static Front getInstance() {return front;}
	
	Scanner scanner = new Scanner(System.in);  

	public void index() {
		while(true) {
		System.out.println("======================메인화면=====================");
		System.out.println("메뉴> 1. 로그인 2. 회원가입 3. 아이디찾기 4. 비번 찾기 5. 회원탈퇴");
		int ch = scanner.nextInt();
		if(ch==1) {login();}
		else if(ch==2) {signup();}
		else if(ch==3) {findid();}
		else if(ch==4) {findpw();}
		else if(ch==5) {admin_main();}
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
			admin_main();
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
		String dtime = null;
		int men = 0;
		Schedule schedule = null;
		while (true) {
			System.out.println("1. 츨발지 선택"+(dpno!=0 ?" : "+Rcontroller.getInstance().airportName(dpno) : ""));
			System.out.println("2. 도착지 선택"+(apno!=0 ? " : "+Rcontroller.getInstance().airportName(apno) : ""));
			System.out.println("3. 출발날짜 선택"+(dtime!=null ? " : "+dtime : ""));
			System.out.println("4. 인원 선택"+ (men!=0 ? " : "+men : ""));
			System.out.println("5. 비행편 검색"+(schedule!=null ? " : "+schedule : ""));
			System.out.println("6. 예약하기");
			System.out.println("7. 뒤로가기");
			
			try {
				int ch = scanner.nextInt();
				if(ch==1) {dpno = Departure(apno);}
				else if(ch==2) {apno = Arrival(dpno);}
				else if(ch==3) {dtime = dateSelect();}
				else if(ch==4) {men=pSelect();}
				else if(ch==5) {flightSelect(dpno,apno,dtime,men);}
				else if(ch==6) {selectCompelete();}
				else if(ch==7) {return;}
			} catch (Exception e) {
				scanner = new Scanner(System.in);
			}
		}
	}
	
	
	//출발지 입력
	public int Departure(int apno) throws Exception{
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();
		
		
		ArrayList<Airport> airportList = Rcontroller.getInstance().Departure(pnation);
		if(airportList==null) {
			System.out.println("잘못 입력하셨습니다.");
			return 0;
		}else {
			System.out.println("공항번호\t공항이름\t나라");
			for(Airport a : airportList) {
				System.out.println(a.getPno() +"\t"+ a.getPname() + "\t" +a.getPnation());
			}
			System.out.println("공항 번호를 입력하세요.");
			int dpno = scanner.nextInt();
			if(apno!=dpno && Rcontroller.getInstance().airportCheck(dpno, pnation)) {
				return dpno;
			}else {
				System.out.println("잘못 입력하셨습니다.");
				return 0;
			}
		}
		
	}
	
	//도착지 입력
	public int Arrival(int dpno) throws Exception{
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();
		
		ArrayList<Airport> airportList = Rcontroller.getInstance().Arrival(pnation,dpno);
		if(airportList==null) {
			System.out.println("잘못 입력하셨습니다.");
			return 0;
		}else {
			System.out.println("공항번호\t공항이름\t나라");
			for(Airport a : airportList) {
				System.out.println(a.getPno() +"\t"+ a.getPname() + "\t" +a.getPnation());
			}
			System.out.println("공항 번호를 입력하세요.");
			int apno =scanner.nextInt();
			if(apno!=dpno && Rcontroller.getInstance().airportCheck(apno, pnation)) {
				return apno;
			}else {
				System.out.println("잘못 입력하셨습니다.");
				return 0;
			}
		}
	}
	
	//달력 출력
	public void calendar() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int nowmonth = month;
		int nowyear = year;
		
		while (true) {
			System.out.printf("======================= %d년 %d월 =======================\n",year,month);
			System.out.println("일\t|월\t|화\t|수\t|목\t|금\t|토\t|");
			
			cal.set(year, month-1, 1);
			int sweek = cal.get(Calendar.DAY_OF_WEEK);
			
			int eday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			
			
			for(int i = 1 ; i<sweek ; i++) {
				System.out.print("\t|");
			}
			if(nowyear>year) {
				for(int i = 1 ; i <=eday ; i++) {
					System.out.printf("%2d[x]\t|",i);
					if(sweek%7 == 0)System.out.println( );sweek++;
				}
			}else if(nowyear==year) {
				if(nowmonth>month) {
					for(int i = 1 ; i <=eday ; i++) {
						System.out.printf("%2d[x]\t|",i);
						if(sweek%7 == 0)System.out.println( );sweek++;
					}
				}else if(nowmonth==month) {
					for(int i = 1 ; i <=eday ; i++) {
						if(i<day) {
							System.out.printf("%2d[x]\t|",i);
						}else if(i==day){
							System.err.printf("[%2d]\t",i);
							System.out.print("|");
						}else {
							System.out.printf("%2d\t|",i);
						}
						
						if(sweek%7 == 0)System.out.println( );sweek++;
					}
				}else if(nowmonth<month) {
					for(int i = 1 ; i <=eday ; i++) {
						System.out.printf("%2d\t|",i);
						if(sweek%7 == 0)System.out.println( );sweek++;
					}
				}
			}else if(nowyear<year) {
				for(int i = 1 ; i <=eday ; i++) {
					System.out.printf("%2d\t|",i);
					if(sweek%7 == 0)System.out.println( );sweek++;
				}
			}

			
			System.out.println("\n=========================================================");
			System.out.println("1.이전달 2.다음달 3.검색 4.날짜선택"); int ch = scanner.nextInt();
			if(ch==1) {
				month--;
				if(month<1) {
					month=12;
					year--;
				}
			}else if(ch==2) {
				month++;
				if(month>12) {
					month = 1;
					year++;
				}
			}else if(ch==3) {
				System.out.println("연도 : "); int inputY = scanner.nextInt();
				System.out.println("월 : "); int inputM = scanner.nextInt();
				if( inputY>=1900 && inputY<=9999 && inputM>=1 && inputM<=12) {
					year = inputY; month = inputM;
				}else{
					System.out.println("출력할 수 없는 달력입니다.");
				}
			}else if(ch==4) {
				break;
			}
		}
	}
	
	//날짜 입력
	public String dateSelect() throws Exception{
		calendar();
		System.out.println("출발 날짜를 입력해 주세요[입력예시 : 2023-03-07] =>");
		String dtime = scanner.next();
		
		if(Rcontroller.getInstance().dateSelect(dtime)) {
			return dtime;
		}else {
			System.out.println("잘못 입력하셨습니다.");
			return null;
		}
	}
	
	//인원수 입력
	public int pSelect(){
		System.out.println("탑승 인원을 입력해 주십시오.");
		int men = 0;
		try {
			men = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			scanner = new Scanner(System.in);
		}
		if(men<=0) {
			System.out.println("0이하의 숫자는 입력할 수 없습니다.");
			return 0;
		}else if(men>11) {
			System.out.println("한번에 예약 가능한 수는 10장 입니다. 그 이상을 원하시면 관리자에게 문의주세요.");
			return 0;
		}
		return men;
	}
	
	public void flightSelect(int dpno,int apno,String dtime,int men) {
		System.out.println(dpno+"/"+apno+"/"+dtime+"/"+men);
	}
	
	public void selectCompelete() {
		System.out.println("1. 결제방법 선택 2. 뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {payment();} 
		else if(ch==2) {return;}
	}
	
	public void payment() {
		
	}
	
	public void Myreser() { // 이경석
		ArrayList<Reservation> rlist = Mcontroller.getInstance().Myreser();
		
		System.out.println("============ 예약내역 출력 ===========");
		for(Reservation re : rlist) {
			System.out.printf("항공사 : %s \n 항공편 : %s -> %s \n "
							+ "비행일 : %s \n 인원 : %d 명 \n "
							+ "결제 가격 : %d -> 등급 할인 가격 (미정)\n "
							+ "예상적립 마일리지 : (미정)",
							re.getLname() , re.getDeparture(), re.getArrival()
							,re.getMen(), re.getTprice() );
		}
		
		System.out.println("1. 예약취소 2. 뒤로가기");
		int ch = scanner.nextInt();
		if(ch==1) {MYcancle();} 
		else if(ch==2) {return;}
	}
	
	public void MYcancle() {
		
	}
	
// 관리자 로그인시 메인페이지
	public void admin_main() {
		while(true) {
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("1.스케줄 확인  2. 스케줄 등록 3. 스케줄 수정 4. 스케줄 삭제  5. 결산내역  6.뒤로가기"); int ch = scanner.nextInt();
			System.out.println("------------------------------------------------------------------------------------------------");

			if(ch==1) {schedulePrint();}
			else if(ch==2) {scheduleRegister();}
			else if(ch==3) {scheduleUpdate();}
			else if(ch==4) {scheduleDelete();}
			else if(ch==5) {RANK();}
			else if(ch==5) {break;}
		}// while e
	}
// 스케줄 목록	10개 출력	
	public void schedulePrint() {	
		// 10개만 출력
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%-2s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
				"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");
		System.out.println("------------------------------------------------------------------------------------------------");
		Acontroller.getInstance().schedulePrint();
		System.out.println("------------------------------------------------------------------------------------------------");
		while(true) {
			System.out.println("1.출발지 기준 검색 2. 출발일정 기준 검색  3.뒤로가기");
			int ch = scanner.nextInt();
			if(ch==1) {schedulePrint_DP();}
			else if(ch==2) {schedulePrint_DD();}
			else if(ch==3) {break;}
		}// while e
	}// schedulePrint e
// 출발지 기준 스케줄 목록 검색 / 출력 
	public void schedulePrint_DP() {
		System.out.print("출발지 검색 : [예시:김포공항]  "); String pname = scanner.next();
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%-2s %-8s %-8s %-16s %-8s %-16s %-8s %-3s \n",
				"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");
		System.out.println("------------------------------------------------------------------------------------------------");
		Acontroller.getInstance().schedulePrint_DP(pname);
		System.out.println("------------------------------------------------------------------------------------------------");
	}// schedulePrint_DP e
// 출발일 기준 스케줄 목록 검색 / 출력 	
	public void schedulePrint_DD() {
		System.out.print("출발일 검색 : [예시:2023-03-07] "); String ddate = scanner.next();
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%-2s %-8s %-8s %-16s %-8s %-16s %-8s %-3s \n",
				"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");		
		System.out.println("------------------------------------------------------------------------------------------------");
		Acontroller.getInstance().schedulePrint_DD(ddate);
		System.out.println("------------------------------------------------------------------------------------------------");
	}// schedulePrint_DD e
// 스케줄 등록
	public void scheduleRegister () {
		while(true) {
			ArrayList<Airport> aplist = Acontroller.getInstance().Airport();
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-2s %-8s %-12s\n",
					"번호","공항명","소속 국가");
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Airport ap: aplist) {
				System.out.printf("%-2s %-8s %-12s\n",
						ap.getPno(),ap.getPname(),ap.getPnation());		
			}
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("[경로 설정]");
			System.out.print("출발지 입력 : [예시:김포공항] "); String dpname = scanner.next();
			System.out.print("도착지 입력 : [예시:인천공항] "); String apname = scanner.next();
			if(Acontroller.getInstance().APcheck(dpname, apname)) {}
			else {System.out.println("[알림] 동일공항 선택이 불가능합니다.");break; }
				
			System.out.println("[일정 설정]");
			System.out.print("출발일 입력 : [예시:2023-03-07 06:15:00] "); String ddate = scanner.next(); String dtime = scanner.next();
			System.out.print("도착일 입력 : [예시:2023-03-07 09:15:00] "); String adate = scanner.next(); String atime = scanner.next();
				
			System.out.println("[비행편 설정]");
			Acontroller.getInstance().LP();
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("비행편 입력 : [예시:MA123456] ");  String lpname = scanner.next();
				
			System.out.println("[가격 설정]");
			System.out.print("가격 입력  : [예시:110000] "); 		int   price  = scanner.nextInt();
			System.out.println("------------------------------------------------------------------------------------------------");			
			boolean result = Acontroller.getInstance().scheduleRegister(dpname,apname,ddate,dtime,adate,atime,lpname,price);			
			if(result) {System.out.println("[알림] 일정 등록 완료 "); break;}
			else {System.out.println("[알림] 일정 등록 실패 "); break;}
		}// while e
	}
// 수정할 스케줄 번호 선택 / 출력	
	public void scheduleUpdate() {

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("수정할 스케줄 번호 선택 : ");      int sno = scanner.nextInt();
		System.out.println("------------------------------------------------------------------------------------------------");
		while(true) {
			Acontroller.getInstance().scheduleUpdate(sno);
			System.out.print("1.경로 수정 2. 일정 수정  3. 비행편 수정  4. 가격 수정  5.뒤로가기   ");
			int ch = scanner.nextInt();
			if(ch==1) {scheduleUpdate_AP(sno);}
			else if(ch==2) {scheduleUpdate_DD(sno);}
			else if(ch==3) {scheduleUpdate_LP(sno);}
			else if(ch==4) {scheduleUpdate_PR(sno);}
			else if(ch==5) {break;}
		}// while e
	}// scheduleUpdate e
// 경로 재설정
	public void scheduleUpdate_AP(int sno) {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("[경로 재설정]");
		System.out.print("출발지 입력 : [예시:김포공항] "); String dpname = scanner.next();
		System.out.print("도착지 입력 : [예시:인천공항] "); String apname = scanner.next();
		System.out.println("------------------------------------------------------------------------------------------------");
		if(Acontroller.getInstance().APcheck(dpname, apname)) {
			Acontroller.getInstance().scheduleUpdate_AP(sno, dpname, apname);
			System.out.println("[알림] 경로 재설정이 완료되었습니다.");
		}
		else {System.out.println("[알림] 동일공항 선택이 불가능합니다.");}
		}
// 일정 재설정
	public void scheduleUpdate_DD(int sno) {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("[일정 재설정]");
		System.out.print("출발일 입력 : [예시:2023-03-07 06:15:00] "); String ddate = scanner.next(); String dtime = scanner.next();
		System.out.print("도착일 입력 : [예시:2023-03-07 09:15:00] "); String adate = scanner.next(); String atime = scanner.next();
		System.out.println("------------------------------------------------------------------------------------------------");
		boolean result = Acontroller.getInstance().scheduleUpdate_DD(sno, ddate,dtime, adate,atime);			
		if(result) {System.out.println("[알림] 일정 재설정 완료 "); }
		else {System.out.println("[알림] 일정 재설정 실패 ");}
	}
// 비행편 재설정
	public void scheduleUpdate_LP(int sno) {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("[비행편 재설정]");
		Acontroller.getInstance().LP();
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("비행편 입력 : [예시:MA123456] ");  String lpname = scanner.next();
		System.out.println("------------------------------------------------------------------------------------------------");
		boolean result = Acontroller.getInstance().scheduleUpdate_LP(sno, lpname);		
		if(result) {System.out.println("[알림] 비행편 재설정 완료 "); }
		else {System.out.println("[알림] 비행편 재설정 실패 ");}
	}
// 가격 재설정
	public void scheduleUpdate_PR(int sno) {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("[가격 재설정]");
		System.out.print("가격 입력  : [예시:110000] "); 		int   price  = scanner.nextInt();	
		System.out.println("------------------------------------------------------------------------------------------------");
		boolean result = Acontroller.getInstance().scheduleUpdate_PR(sno, price);			
		if(result) {System.out.println("[알림] 가격 재설정 완료 "); }
		else {System.out.println("[알림] 가격 재설정 실패 ");}
	}
// 삭제할 스케줄 번호 선택 / 출력	
	public void scheduleDelete() {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("삭제할 스케쥴번호 입력 : ");  int sno = scanner.nextInt();
		System.out.println("------------------------------------------------------------------------------------------------");
		Acontroller.getInstance().scheduleUpdate(sno);
		System.out.print("해당 스케줄을 삭제하시겠습니까? 1. 삭제  2. 취소"); int ch = scanner.nextInt();
		System.out.println("------------------------------------------------------------------------------------------------");
		if(ch==1) {
			boolean result = Acontroller.getInstance().scheduleDelete(sno);;			
			if(result) {System.out.println("[알림] 일정 삭제 완료 "); }
			else {System.out.println("[알림] 일정 삭제 실패 ");}
		}else if (ch==2) {System.out.println("[알림] 삭제 취소되었습니다. "); }
	}
// 결산 내역 메인페이지
	public void RANK() {
		while(true) {
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("1.항공사별 매출 결산  2. 공항별 이용객수 결산   3. 뒤로가기    ");	int ch = scanner.nextInt();
			if(ch==1) {ALRANK();}
			else if(ch==2) {APRANK();}
			else if(ch==3) {break;}
			System.out.println("------------------------------------------------------------------------------------------------");
		}// while e
	}
// 항공사별 매출 결산
	public void ALRANK() {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%-2s %-15s %-20s\n",
				"순위","공항명","매출 총액");
		System.out.println("------------------------------------------------------------------------------------------------");
		Acontroller.getInstance().alRank();		
		}
// 공항별 이용객수 결산
		public void APRANK() {
			
		}
	
}