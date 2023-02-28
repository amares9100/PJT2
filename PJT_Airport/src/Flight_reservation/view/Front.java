package Flight_reservation.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
		System.out.println("메뉴> 1. 로그인 2. 회원가입 3. 아이디찾기 4. 비번 찾기");
		int ch = scanner.nextInt();
		if(ch==1) {login();}
		else if(ch==2) {signup();}
		else if(ch==3) {findid();}
		else if(ch==4) {findpw();}
	}
	
	public void login() {
		user_main();
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