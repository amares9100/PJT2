package Flight_reservation.controller;

import java.util.ArrayList;

import Flight_reservation.model.Adao;
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
	
	public ArrayList<LP> LP() {
		return Adao.getInstance().LP();
	}
	public boolean scheduleRegister(String dpname,String apname,String ddate,String adate,String lpname) {
		
		Schedule scdto = new Schedule(0,lpname, dpname, apname, ddate, adate,0,0);
		
		boolean result = Adao.getInstance().scheduleRegister(scdto);
		
		return result;
	}
	
	public void scheduleUpdate() {
		
	}
	
	public void scheduleDelete() {
		
	}
	
}
