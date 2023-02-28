package Flight_reservation.controller;

import java.util.ArrayList;
import java.util.Date;

import Flight_reservation.model.Adao;
import Flight_reservation.model.Airport;
import Flight_reservation.model.LP;
import Flight_reservation.model.Schedule;

public class Acontroller {
	private static Acontroller controller = new Acontroller();
	private Acontroller() {};
	public static Acontroller getInstance() {return controller;}
	
	public void schedulePrint() {	
		ArrayList<Schedule> slist  =  Adao.getInstance().schedulePrint();
		for(Schedule dto:slist) {
			System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %3d \n",
					dto.getSno(),dto.getLpname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
		}
	}
	public void schedulePrint_DP(String pname) {
		ArrayList<Schedule> splist  =  Adao.getInstance().schedulePrint_DP(pname);
		for(Schedule dto:splist) {
			System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %3d \n",
					dto.getSno(),dto.getLpname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
		}	
	}
	public void schedulePrint_DD(String ddate) {
		ArrayList<Schedule> sdlist  =  Adao.getInstance().schedulePrint_DD(ddate);
		for(Schedule dto:sdlist) {
			System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %3d \n",
					dto.getSno(),dto.getLpname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
		}
	}
	public ArrayList<Airport> Airport() {
		return Adao.getInstance().Airport();
	}

	public ArrayList<LP> LP() {
		return Adao.getInstance().LP();
	}
	public boolean APcheck(String dpname,String apname) {
		if(dpname.equals(apname)) {return false;}
		return true;
	}
	public boolean dateCheck(String ddate,String adate) {
		
		if(ddate.equals(adate)) {return false;}
		return true;
	}
	public boolean scheduleRegister(String dpname,String apname,String ddate,String adate,String lpname,int price) {
		// 1. 출발지와 도착지 동일 공항 불가
		if(dpname.equals(apname)) { return false ;}
		// 2. 도착시간이 출발시간보다 빠르면 불가
		
		Schedule scdto = new Schedule(0,lpname, dpname, apname, ddate, adate,price,0);
		
		boolean result = Adao.getInstance().scheduleRegister(scdto);
		
		return result;
	}
	
	public void scheduleUpdate() {
		
	}
	
	public void scheduleDelete() {
		
	}
	
}
