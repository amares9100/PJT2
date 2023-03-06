package Flight_reservation.view;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

import Flight_reservation.controller.Mcontroller;
import Flight_reservation.controller.Rcontroller;
import Flight_reservation.model.Airport;
import Flight_reservation.model.Reservation;
import Flight_reservation.model.Schedule;

public class Rfront {
	private static Rfront reservation = new Rfront();

	private Rfront() {
	};

	public static Rfront getInstance() {
		return reservation;
	}

	Scanner scanner = new Scanner(System.in);

	// 예약하기
	public void reservation() {
		System.out.println("\t\t\t\t\t==============================================예약하기==============================================");
		int dpno = 0;
		int apno = 0;
		String dtime = null;
		int men = 0;
		while (true) {
			System.out.println("\t\t\t\t\t 1. 츨발지 선택" + (dpno != 0 ? " : " + Rcontroller.getInstance().airportName(dpno) : ""));
			System.out.println("\t\t\t\t\t 2. 도착지 선택" + (apno != 0 ? " : " + Rcontroller.getInstance().airportName(apno) : ""));
			System.out.println("\t\t\t\t\t 3. 출발날짜 선택" + (dtime != null ? " : " + dtime : ""));
			System.out.println("\t\t\t\t\t 4. 인원 선택" + (men != 0 ? " : " + men : ""));
			System.out.println("\t\t\t\t\t 5. 비행편 검색");
			System.out.println("\t\t\t\t\t 6. 뒤로가기");

			try {
				System.out.print("\t\t\t\t\t ");
				int ch = scanner.nextInt();
				if (ch == 1) {
					dpno = Departure(apno);
				} else if (ch == 2) {
					apno = Arrival(dpno);
				} else if (ch == 3) {
					dtime = dateSelect();
				} else if (ch == 4) {
					men = pSelect();
				} else if (ch == 5) {
					flightSelect(dpno, apno, dtime, men);
				} else if (ch == 6) {
					return;
				}
			} catch (Exception e) {
				scanner = new Scanner(System.in);
			}
		}
	}

	// 출발지 입력
	public int Departure(int apno) throws Exception {
		System.out.println("\t\t\t\t\t==============================================국가선택==============================================");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println("\t\t\t\t\t "+pnationlist);
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 국가선택 : ");
		String pnation = scanner.next();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		
		ArrayList<Airport> airportList = Rcontroller.getInstance().Departure(pnation);
		if (airportList == null) {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 잘못 입력하셨습니다.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			return 0;
		} else {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 공항번호\t공항이름\t나라");
			for (Airport a : airportList) {
				System.out.println("\t\t\t\t\t "+a.getPno() + "\t" + a.getPname() + "\t" + a.getPnation());
			}
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.print("\t\t\t\t\t 공항 번호: ");
			int dpno = scanner.nextInt();
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			if (apno != dpno && Rcontroller.getInstance().airportCheck(dpno, pnation)) {
				return dpno;
			} else {
				System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				System.out.println("\t\t\t\t\t 잘못 입력하셨습니다.");
				System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				return 0;
			}
		}

	}

	// 도착지 입력
	public int Arrival(int dpno) throws Exception {
		System.out.println("\t\t\t\t\t==============================================국가선택==============================================");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println("\t\t\t\t\t "+pnationlist);
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 국가선택 : ");
		String pnation = scanner.next();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");

		ArrayList<Airport> airportList = Rcontroller.getInstance().Arrival(pnation, dpno);
		if (airportList == null) {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 잘못 입력하셨습니다.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			return 0;
		} else {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 공항번호\t공항이름\t나라");
			for (Airport a : airportList) {
				System.out.println("\t\t\t\t\t "+a.getPno() + "\t" + a.getPname() + "\t" + a.getPnation());
			}
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.print("\t\t\t\t\t 공항 번호 : ");
			int apno = scanner.nextInt();
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			if (apno != dpno && Rcontroller.getInstance().airportCheck(apno, pnation)) {
				return apno;
			} else {
				System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				return 0;
			}
		}
	}

	// 달력 출력
	public void calendar() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int nowmonth = month;
		int nowyear = year;

		while (true) {
			System.out.printf( "\t\t\t\t\t=========================================== %d년 %d월 ============================================\n", year, month);
			System.out.print("\t\t\t\t\t\t\t 일\t|월\t|화\t|수\t|목\t|금\t|토\t|\n\t\t\t\t\t\t\t ");

			cal.set(year, month - 1, 1);
			int sweek = cal.get(Calendar.DAY_OF_WEEK);

			int eday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			for (int i = 1; i < sweek; i++) {
				System.out.print("\t|");
			}
			
			if (nowyear > year) {
				for (int i = 1; i <= eday; i++) {
					System.out.printf("%2d[x]\t|", i);
					if (sweek % 7 == 0)
						System.out.print("\n\t\t\t\t\t\t\t ");
					sweek++;
				}
			} else if (nowyear == year) {
				if (nowmonth > month) {
					for (int i = 1; i <= eday; i++) {
						System.out.printf("%2d[x]\t|", i);
						if (sweek % 7 == 0)
							System.out.print("\n\t\t\t\t\t\t\t ");
						sweek++;
					}
				} else if (nowmonth == month) {
					for (int i = 1; i <= eday; i++) {
						if (i < day) {
							System.out.printf("%2d[x]\t|", i);
						} else if (i == day) {
							System.err.printf("[%2d]\t", i);
							System.out.print("|");
						} else {
							System.out.printf("%2d\t|", i);
						}

						if (sweek % 7 == 0)
							System.out.print("\n\t\t\t\t\t\t\t ");
						sweek++;
					}
				} else if (nowmonth < month) {
					for (int i = 1; i <= eday; i++) {
						System.out.printf("%2d\t|", i);
						if (sweek % 7 == 0)
							System.out.print("\n\t\t\t\t\t\t\t ");
						sweek++;
					}
				}
			} else if (nowyear < year) {
				for (int i = 1; i <= eday; i++) {
					System.out.printf("%2d\t|", i);
					if (sweek % 7 == 0)
						System.out.print("\n\t\t\t\t\t\t\t ");
					sweek++;
				}
			}
			System.out.println();
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 1.이전달 2.다음달 3.검색 4.날짜선택");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.print("\t\t\t\t\t "); int ch = scanner.nextInt();
			
			if (ch == 1) {
				month--;
				if (month < 1) {
					month = 12;
					year--;
				}
			} else if (ch == 2) {
				month++;
				if (month > 12) {
					month = 1;
					year++;
				}
			} else if (ch == 3) {
				System.out.print("\t\t\t\t\t 연도 : ");
				int inputY = scanner.nextInt();
				System.out.print("\t\t\t\t\t 월 : ");
				int inputM = scanner.nextInt();
				System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				if (inputY >= 1900 && inputY <= 9999 && inputM >= 1 && inputM <= 12) {
					year = inputY;
					month = inputM;
				} else {
					System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
					System.out.println("\t\t\t\t\t 출력할 수 없는 달력입니다.");
					System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
				}
			} else if (ch == 4) {
				break;
			}
		}
	}

	// 날짜 입력
	public String dateSelect() throws Exception {
		System.out.println("\t\t\t\t\t==============================================날짜선택==============================================");
		calendar();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 출발 날짜를 입력해 주세요[입력예시 : 2023-03-07] : ");
		String dtime = scanner.next();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		
		if (Rcontroller.getInstance().dateSelect(dtime)) {
			return dtime;
		} else {
			System.out.println(Rcontroller.getInstance().dateSelect(dtime));
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 잘못 입력하셨습니다.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			return null;
		}
	}

	// 인원수 입력
	public int pSelect() {
		System.out.println("\t\t\t\t\t==============================================인원선택==============================================");
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 탑승인원 : ");
		int men = 0;
		try {
			men = scanner.nextInt();
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 잘못입력하셨습니다.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			scanner = new Scanner(System.in);
		}
		if (men <= 0) {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 0이하의 숫자는 입력할 수 없습니다.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			return 0;
		} else if (men > 11) {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t 한번에 예약 가능한 수는 10장 입니다. 그 이상을 원하시면 관리자에게 문의주세요.");
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			return 0;
		}
		return men;
	}

	// 비행편 검색 화면 출발지 , 도착지 , 날짜 , 인원 /예시 : 김포 -> 인천 2023-03-07
	public void flightSelect(int dpno, int apno, String dtime, int men) { // 이경석
		System.out.println("\t\t\t\t\t=============================================비행편 선택=============================================");

		ArrayList<Schedule> slist = Rcontroller.getInstance().flightSelect(dpno, apno, dtime, men);
		System.out.printf("\t\t\t\t\t 현재 선택 \n\t\t\t\t\t 항공편:%s -> %s \n\t\t\t\t\t 비행일:%s \n\t\t\t\t\t 인원:%d명 \n\n",
				 slist.get(0).getDpname() , slist.get(0).getApname() ,slist.get(0).getDtime()
				 , men);
		
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.printf("\t\t\t\t\t %-2s %-8s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
					"번호","항공사","비행기","출발지","출발일정","도착지","도착일정","가격","잔여좌석");		
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		
		for (Schedule dto : slist) {
			System.out.printf("\t\t\t\t\t %2d %-10s %-10s %-8s %-20s %-8s %-20s %-8d %-3d \n",
					dto.getSno(),dto.getLname(),dto.getAname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		}
		
		// 검색화면은 하나만 필요함, 이미 중복되므로 하나만 뽑아서 출력
		ticketReservation(men);
	}

	// 새함수 추가 (비행확인후 -> 티켓예매)
	public void ticketReservation(int men) {
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 예약하려는 스케줄 번호 선택 : ");
		int sno = scanner.nextInt();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		Reservation ticket = Rcontroller.getInstance().ticketReservation1(sno);
		
		System.out.printf(
				"\t\t\t\t\t 스케줄 번호: %d \n\t\t\t\t\t 항공사 : %s \n\t\t\t\t\t 비행기 : %s \n\t\t\t\t\t 항공편 : %s -> %s \n\t\t\t\t\t " + "비행일 : %s \n\t\t\t\t\t 인원 : %d 명 \n\t\t\t\t\t "
						+ "결제 가격 : %d -> 등급 할인 가격 %d\n\t\t\t\t\t " + "예상적립 마일리지 : %d\n",
				ticket.getSno(), ticket.getLname(), ticket.getAname(), ticket.getDeparture(), ticket.getArrival(),
				ticket.getDate(), men, ticket.getTprice(), ticket.getTprice() -(int)(ticket.getArate()*ticket.getDiscount()) , (int)(ticket.getTprice() * ticket.getArate() )   );
		
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 이 스케줄로 예약하시겠습니까? 1.Yes 2.no ");
		int ch00 = scanner.nextInt();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		if (ch00 == 1) {
			selectCompelete(ticket, men );
		} else if (ch00 == 2) {
		}
	}

	public void selectCompelete(Reservation ticket, int men) { // 이경석
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t 1. 결제방법 선택 2. 뒤로가기 ");
		int ch = scanner.nextInt();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		if (ch == 1) {
			payment(ticket, men);
		} else if (ch == 2) {
			return;
		}
	}
   
	
	public void payment(Reservation ticket, int men) {
		System.out.println("\t\t\t\t\t==============================================결제화면==============================================");
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t 1.신용카드  2.무통장 입금  3.결제취소 ");
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.print("\t\t\t\t\t ");
		int ch02 = scanner.nextInt();
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		
		if(ch02==1 || ch02==2) {
		Rcontroller.getInstance().ticketReservation2(men, ticket);
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t [알림] 예약이 완료되었습니다.");
		System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
		Mcontroller.getInstance().tierUpdate();
		}
		else if(ch02==3) {}
		else {
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t [알림] 잘못된 입력입니다!");}
			System.out.println("\t\t\t\t\t-------------------------------------------------------------------------------------------------");
	}

}
