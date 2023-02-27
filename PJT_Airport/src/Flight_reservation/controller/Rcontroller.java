package Flight_reservation.controller;

import java.util.ArrayList;

import Flight_reservation.model.Rdao;
import Flight_reservation.model.Schedule;

public class Rcontroller {
	private static Rcontroller acontroller = new Rcontroller();
	private Rcontroller() {};
	public static Rcontroller getInstance() {return acontroller;}
	
	Rdao rdao = new Rdao();
	
	//출발하는 공항
	public ArrayList<Schedule> Departure(int dpno) {
		return rdao.Departure(dpno);
	}
	
	//도착하는 공항
	public void Arrival() {
		
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

