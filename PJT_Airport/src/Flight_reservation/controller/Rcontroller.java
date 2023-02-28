package Flight_reservation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import Flight_reservation.model.Airport;
import Flight_reservation.model.Rdao;

public class Rcontroller {
	private static Rcontroller acontroller = new Rcontroller();
	private Rcontroller() {};
	public static Rcontroller getInstance() {return acontroller;}
	
	// 국가선택
	public String pnation() {
		return Rdao.getInstance().pnation();
	}
	
	//출발지 출력
	public ArrayList<Airport> Departure(String pnation) {
		return Rdao.getInstance().Departure(pnation);
	}
	
	//공항 이름 출력
	public String airportName(int pno) {
		return Rdao.getInstance().airportName(pno);
	}
	
	//도착지 출력
	public ArrayList<Airport> Arrival(String pnation, int pno) {
		return Rdao.getInstance().Arrival(pnation, pno);
	}
	
	//출발 날짜 선택 (달력출력)
	public void dateSelect() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int nowmonth = month;
		int nowyear = year;
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.printf("======================= %d년 %d월 =======================\n",year,month);
			System.out.println("일\t|월\t|화\t|수\t|목\t|금\t|토\t|");
			
			cal.set(year, month-1, 1);
			int sweek = cal.get(Calendar.DAY_OF_WEEK);
			
			int eday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			
			
			for(int i = 1 ; i<sweek ; i++) {
				System.out.print("\t|");
			}
			if(nowmonth>month && nowyear>=year) {
				for(int i = 1 ; i <=eday ; i++) {
					System.out.printf("%2d[x]\t|",i);
					if(sweek%7 == 0)System.out.println( );sweek++;
				}
			}
			else if(nowmonth==month && nowyear==year) {
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
			}
			else if(nowmonth<month && nowyear<=year) {
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
	
	//인원 선택
	public void pSelect() {
		
	}
	
	//비행편 선택
	public void flightSelect() {
		
	}
	
	//결제
	public void payment() {
		
	}
	
}