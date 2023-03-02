package Flight_reservation.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

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
		int dpno = 0;
		int apno = 0;
		String dtime = null;
		int men = 0;
		while (true) {
			System.out.println("1. 츨발지 선택" + (dpno != 0 ? " : " + Rcontroller.getInstance().airportName(dpno) : ""));
			System.out.println("2. 도착지 선택" + (apno != 0 ? " : " + Rcontroller.getInstance().airportName(apno) : ""));
			System.out.println("3. 출발날짜 선택" + (dtime != null ? " : " + dtime : ""));
			System.out.println("4. 인원 선택" + (men != 0 ? " : " + men : ""));
			System.out.println("5. 비행편 검색");
			System.out.println("6. 뒤로가기");

			try {
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
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();

		ArrayList<Airport> airportList = Rcontroller.getInstance().Departure(pnation);
		if (airportList == null) {
			System.out.println("잘못 입력하셨습니다.");
			return 0;
		} else {
			System.out.println("공항번호\t공항이름\t나라");
			for (Airport a : airportList) {
				System.out.println(a.getPno() + "\t" + a.getPname() + "\t" + a.getPnation());
			}
			System.out.println("공항 번호를 입력하세요.");
			int dpno = scanner.nextInt();
			if (apno != dpno && Rcontroller.getInstance().airportCheck(dpno, pnation)) {
				return dpno;
			} else {
				System.out.println("잘못 입력하셨습니다.");
				return 0;
			}
		}

	}

	// 도착지 입력
	public int Arrival(int dpno) throws Exception {
		System.out.println("국가선택");
		String pnationlist = Rcontroller.getInstance().pnation();
		System.out.println(pnationlist);
		System.out.println("국가명을 입력해 주세요.");
		String pnation = scanner.next();

		ArrayList<Airport> airportList = Rcontroller.getInstance().Arrival(pnation, dpno);
		if (airportList == null) {
			System.out.println("잘못 입력하셨습니다.");
			return 0;
		} else {
			System.out.println("공항번호\t공항이름\t나라");
			for (Airport a : airportList) {
				System.out.println(a.getPno() + "\t" + a.getPname() + "\t" + a.getPnation());
			}
			System.out.println("공항 번호를 입력하세요.");
			int apno = scanner.nextInt();
			if (apno != dpno && Rcontroller.getInstance().airportCheck(apno, pnation)) {
				return apno;
			} else {
				System.out.println("잘못 입력하셨습니다.");
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
			System.out.printf("======================= %d년 %d월 =======================\n", year, month);
			System.out.println("일\t|월\t|화\t|수\t|목\t|금\t|토\t|");

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
						System.out.println();
					sweek++;
				}
			} else if (nowyear == year) {
				if (nowmonth > month) {
					for (int i = 1; i <= eday; i++) {
						System.out.printf("%2d[x]\t|", i);
						if (sweek % 7 == 0)
							System.out.println();
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
							System.out.println();
						sweek++;
					}
				} else if (nowmonth < month) {
					for (int i = 1; i <= eday; i++) {
						System.out.printf("%2d\t|", i);
						if (sweek % 7 == 0)
							System.out.println();
						sweek++;
					}
				}
			} else if (nowyear < year) {
				for (int i = 1; i <= eday; i++) {
					System.out.printf("%2d\t|", i);
					if (sweek % 7 == 0)
						System.out.println();
					sweek++;
				}
			}

			System.out.println("\n=========================================================");
			System.out.println("1.이전달 2.다음달 3.검색 4.날짜선택");
			int ch = scanner.nextInt();
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
				System.out.println("연도 : ");
				int inputY = scanner.nextInt();
				System.out.println("월 : ");
				int inputM = scanner.nextInt();
				if (inputY >= 1900 && inputY <= 9999 && inputM >= 1 && inputM <= 12) {
					year = inputY;
					month = inputM;
				} else {
					System.out.println("출력할 수 없는 달력입니다.");
				}
			} else if (ch == 4) {
				break;
			}
		}
	}

	// 날짜 입력
	public String dateSelect() throws Exception {
		calendar();
		System.out.println("출발 날짜를 입력해 주세요[입력예시 : 2023-03-07] =>");
		String dtime = scanner.next();

		if (Rcontroller.getInstance().dateSelect(dtime)) {
			return dtime;
		} else {
			System.out.println("잘못 입력하셨습니다.");
			return null;
		}
	}

	// 인원수 입력
	public int pSelect() {
		System.out.println("탑승 인원을 입력해 주십시오.");
		int men = 0;
		try {
			men = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다.");
			scanner = new Scanner(System.in);
		}
		if (men <= 0) {
			System.out.println("0이하의 숫자는 입력할 수 없습니다.");
			return 0;
		} else if (men > 11) {
			System.out.println("한번에 예약 가능한 수는 10장 입니다. 그 이상을 원하시면 관리자에게 문의주세요.");
			return 0;
		}
		return men;
	}

	// 비행편 검색 화면 출발지 , 도착지 , 날짜 , 인원 /예시 : 김포 -> 인천 2023-03-07
	public void flightSelect(int dpno, int apno, String dtime, int men) { // 이경석
		System.out.println("======== 비행편 검색 화면 =========");

		ArrayList<Schedule> slist = Rcontroller.getInstance().flightSelect(dpno, apno, dtime, men);
		System.out.printf("현재 선택 \n항공편:%s -> %s \n비행일:%s \n인원:%d명 \n\n",
				 slist.get(0).getDpname() , slist.get(0).getApname() ,slist.get(0).getDtime()
				 , men);
		
		System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf("%-2s %-8s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
					"번호","항공사","비행기","출발지","출발일정","도착지","도착일정","가격","잔여좌석");		
		System.out.println("------------------------------------------------------------------------------------------------");
		
		for (Schedule dto : slist) {
			System.out.printf("%2d %-10s %-10s %-8s %-20s %-8s %-20s %-8d %-3d \n",
					dto.getSno(),dto.getLname(),dto.getAname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
			System.out.println("------------------------------------------------------------------------------------------------");
		}
		
		// 검색화면은 하나만 필요함, 이미 중복되므로 하나만 뽑아서 출력
		ticketReservation(men);
	}

	// 새함수 추가 (비행확인후 -> 티켓예매)
	public void ticketReservation(int men) {
		System.out.println("몇번째 스케줄 번호를 선택하시겠습니까?");
		int sno = scanner.nextInt();
		Reservation ticket = Rcontroller.getInstance().ticketReservation1(sno);

		System.out.printf(
				"스케줄 번호: %d \n항공사 : %s \n비행기 : %s \n항공편 : %s -> %s \n" + "비행일 : %s \n인원 : %d 명 \n"
						+ "결제 가격 : %d -> 등급 할인 가격 %d\n" + "예상적립 마일리지 : %d\n",
				ticket.getSno(), ticket.getLname(), ticket.getAname(), ticket.getDeparture(), ticket.getArrival(),
				ticket.getDate(), men, ticket.getTprice(), ticket.getAdate());

		System.out.printf("%10s %15s %15s %15s %15s %20s \t\t %20s \n ", "스케쥴 번호", "항공사명", "비행기명", "출발지", "도착지", "비행일",
				"도착일");
		System.out.printf("%10d \t %15s %15s %15s %15s \t\t %20s \t\t %20s \n ", ticket.getSno(), ticket.getLname(),
				ticket.getAname(), ticket.getDeparture(), ticket.getArrival(), ticket.getDate(), ticket.getAdate());

		System.out.println("이 비행표를 선택하시겠습니까? 1.Yes 2.no");
		int ch00 = scanner.nextInt();
		if (ch00 == 1) {
			selectCompelete(ticket, men);
		} else if (ch00 == 2) {
		}
	}

	public void selectCompelete(Reservation ticket, int men) { // 이경석
		System.out.println("1. 결제방법 선택 2. 뒤로가기");
		int ch = scanner.nextInt();
		if (ch == 1) {
			payment(ticket, men);
		} else if (ch == 2) {
			return;
		}
	}

	public void payment(Reservation ticket, int men) {
		Rcontroller.getInstance().ticketReservation2(men, ticket);
	}

}
