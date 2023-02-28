package Flight_reservation.controller;

import java.util.ArrayList;

import Flight_reservation.model.Rdao;
import Flight_reservation.model.Schedule;

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
		
		//출발 날짜 선택
		public void dateSelect() {
			
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

