package Flight_reservation.view;

import java.util.ArrayList;
import java.util.Scanner;

import Flight_reservation.controller.Acontroller;
import Flight_reservation.model.Airport;

public class Afront {
	private static Afront admin = new Afront();
	private Afront() {};
	public static Afront getInstance() {return admin;}
	
	Scanner scanner = new Scanner(System.in);  
	// 관리자 로그인시 메인페이지
		public void admin_main() {
			while(true) {
				try {
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("1.스케줄 확인  2. 스케줄 등록 3. 스케줄 수정 4. 스케줄 삭제  5. 결산내역  6. 예약확인  7.뒤로가기"); int ch = scanner.nextInt();
					System.out.println("------------------------------------------------------------------------------------------------");

					if(ch==1) {schedulePrint();}
					else if(ch==2) {scheduleRegister();}
					else if(ch==3) {scheduleUpdate();}
					else if(ch==4) {scheduleDelete();}
					else if(ch==5) {RANK();}
					else if(ch==6) {Aresevation();}
					else if(ch==6) {break;}
				} catch (Exception e) {
					System.out.println(e);
					scanner = new Scanner(System.in);
				}
				
			}// while e
		}
	// 스케줄 목록	10개 출력	
		public void schedulePrint() throws Exception{	
			// 10개만 출력
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-2s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
					"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().schedulePrint();
			System.out.println("------------------------------------------------------------------------------------------------");
			while(true) {
				System.out.print("1.스케줄 검색 2.뒤로가기");
				int ch = scanner.nextInt();
				if(ch==1) {schedulePrint_DP();}
				else if(ch==2) {break;}
			}// while e
		}// schedulePrint e
	// 스케줄 검색
		public void schedulePrint_DP() throws Exception{
			System.out.print("출발지 검색 : [예시:김포공항]		"); 	String pname = scanner.next();
			System.out.print("출발일 검색 : [예시:2023-03-07]	"); 	String ddate = scanner.next();
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-2s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
					"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().schedulePrint_DP(pname,ddate);
			System.out.println("------------------------------------------------------------------------------------------------");
		}// schedulePrint_DP e
	// 스케줄 등록
		public void scheduleRegister () throws Exception{
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
				int check  = Acontroller.getInstance().APcheck(dpname, apname);
				if(check==1) {}
				else if(check==2) {System.out.println("[알림] 존재하지 않는 공항입니다.");break; }
				else if(check==3) {System.out.println("[알림] 동일공항 선택이 불가능합니다.");break; }

				
				System.out.println("[일정 설정]");
				System.out.print("출발일	입력 : [예시:2023-03-07] "); 	String ddate = scanner.next(); 
				System.out.print("출발시간	입력 : [예시:06:15:00] ");		String dtime = scanner.next();
				System.out.print("도착일 	입력 : [예시:2023-03-07] "); 	String adate = scanner.next(); 
				System.out.print("도착시간	입력 : [예시:09:15:00] ");		String atime = scanner.next();
				if(Acontroller.getInstance().dateCheck(ddate, dtime, adate, atime)) {}
				else {System.out.println("[알림] 등록 불가능한 일정입니다.");break; }
				
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
		public void scheduleUpdate() throws Exception{

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
		public void scheduleUpdate_AP(int sno) throws Exception{
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("[경로 재설정]");
			System.out.print("출발지 입력 : [예시:김포공항] "); String dpname = scanner.next();
			System.out.print("도착지 입력 : [예시:인천공항] "); String apname = scanner.next();
			System.out.println("------------------------------------------------------------------------------------------------");
			int check  = Acontroller.getInstance().APcheck(dpname, apname);
			if(check==1) {
				Acontroller.getInstance().scheduleUpdate_AP(sno, dpname, apname);
				System.out.println("[알림] 경로 재설정이 완료되었습니다.");
			}
			else if(check==2) {System.out.println("[알림] 존재하지 않는 공항입니다.");}
			else if(check==3) {System.out.println("[알림] 동일공항 선택이 불가능합니다.");}
			}
	// 일정 재설정
		public void scheduleUpdate_DD(int sno) throws Exception{
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("[일정 재설정]");
			System.out.print("출발일	입력 : [예시:2023-03-07] "); 	String ddate = scanner.next(); 
			System.out.print("출발시간	입력 : [예시:06:15:00] ");		String dtime = scanner.next();
			System.out.print("도착일 	입력 : [예시:2023-03-07] "); 	String adate = scanner.next(); 
			System.out.print("도착시간	입력 : [예시:09:15:00] ");		String atime = scanner.next();
			System.out.println("------------------------------------------------------------------------------------------------");
			boolean result = Acontroller.getInstance().scheduleUpdate_DD(sno, ddate,dtime, adate,atime);			
			if(result) {System.out.println("[알림] 일정 재설정 완료 "); }
			else {System.out.println("[알림] 일정 재설정 실패 ");}
		}
	// 비행편 재설정
		public void scheduleUpdate_LP(int sno) throws Exception{
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
		public void scheduleUpdate_PR(int sno) throws Exception{
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("[가격 재설정]");
			System.out.print("가격 입력  : [예시:110000] "); 		int   price  = scanner.nextInt();	
			System.out.println("------------------------------------------------------------------------------------------------");
			boolean result = Acontroller.getInstance().scheduleUpdate_PR(sno, price);			
			if(result) {System.out.println("[알림] 가격 재설정 완료 "); }
			else {System.out.println("[알림] 가격 재설정 실패 ");}
		}
	// 삭제할 스케줄 번호 선택 / 출력 / 삭제
		public void scheduleDelete() throws Exception{
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("삭제할 스케쥴번호 입력 : ");  int sno = scanner.nextInt();
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().scheduleUpdate(sno);
			System.out.print("해당 스케줄을 삭제하시겠습니까? 1. 삭제  2. 취소		"); int ch = scanner.nextInt();
			System.out.println("------------------------------------------------------------------------------------------------");
			if(ch==1) {
				boolean result = Acontroller.getInstance().scheduleDelete(sno);;			
				if(result) {System.out.println("[알림] 일정 삭제 완료 "); }
				else {System.out.println("[알림] 일정 삭제 실패 ");}
			}else if (ch==2) {System.out.println("[알림] 삭제 취소되었습니다. "); }
		}
	// 결산 내역 메인페이지 
		public void RANK() throws Exception{
			while(true) {
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.print("1.항공사별 매출 결산  2. 공항별 이용객수 결산   3. 뒤로가기    ");	int ch = scanner.nextInt();
				System.out.println("------------------------------------------------------------------------------------------------");
				if(ch==1) {ALRANK();}
				else if(ch==2) {APRANK();}
				else if(ch==3) {break;}
			}// while e
		}
	// 항공사별 매출 결산
		public void ALRANK() throws Exception {
			System.out.printf("%-3s %-14s %-20s\n",
					"순위","항공사명","매출 총액");
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().alRank();		
			}
	// 공항별 이용객수 결산
		public void APRANK() throws Exception {
			System.out.printf("%-3s %-12s %-8s\n",
					"순위","공항명","이용객수");
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().apRank();			
		}
		
	// 예약 확인
		public void Aresevation() throws Exception {			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-5s %-5s %-5s %-5s %-10s \n",
					"예약번호","스케줄번호","회원번호","인원수","가격");		
			System.out.println("------------------------------------------------------------------------------------------------");
			Acontroller.getInstance().Aresevation();
			System.out.println("------------------------------------------------------------------------------------------------");	
			while(true) {
				System.out.print("1.예약 상세내역 확인  2. 뒤로가기   ");
				int ch = scanner.nextInt();
				if(ch==1) {
					System.out.print("예약 번호 : "); int rno = scanner.nextInt();
					AreservView(rno);}
				else if(ch==2) {break;}
			}
		}
	// 예약 상세 / 삭제
		public void AreservView(int rno) throws Exception  {
			System.out.println("------------------------------------------------------------------------------------------------");

			Acontroller.getInstance().AresevView(rno);
			System.out.println("------------------------------------------------------------------------------------------------");	
			System.out.print("해당 예약을 삭제하시겠습니까? 1. 삭제  2. 취소		"); int ch = scanner.nextInt();
			System.out.println("------------------------------------------------------------------------------------------------");
			if(ch==1) {
				boolean result = Acontroller.getInstance().AresevCancle(rno);		
				if(result) {System.out.println("[알림] 일정 삭제 완료 "); }
				else {System.out.println("[알림] 일정 삭제 실패 ");}
			}else if (ch==2) {System.out.println("[알림] 삭제 취소되었습니다. "); }
		}
		
}
