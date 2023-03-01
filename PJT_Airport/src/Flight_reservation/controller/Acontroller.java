 package Flight_reservation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Flight_reservation.model.Adao;
import Flight_reservation.model.Airport;
import Flight_reservation.model.LP;
import Flight_reservation.model.Schedule;
import Flight_reservation.model.rankDto;

public class Acontroller {
	private static Acontroller controller = new Acontroller();
	private Acontroller() {};
	public static Acontroller getInstance() {return controller;}
	// 스케줄 목록	10개 출력	-> 현재 sno 오름차순 기준 추후 변경 예정 / 날짜 지나면 자동 스케줄 삭제 기능 필요?
	public void schedulePrint() {	
		ArrayList<Schedule> slist  =  Adao.getInstance().schedulePrint();
		
		for(int i=0; i< 11 ; i++) {
			System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %-3d \n",
					slist.get(i).getSno(),slist.get(i).getLpname(),slist.get(i).getDpname(),slist.get(i).getDtime(),
					slist.get(i).getApname(),slist.get(i).getAtime(),slist.get(i).getPrice(),slist.get(i).getRseats());
		}

	}
	// 검색된 스케줄 목록	
	public void schedulePrint_DP(String pname,String ddate) {
		ArrayList<Schedule> splist  =  Adao.getInstance().schedulePrint_DP(pname,ddate);
		for(Schedule dto:splist) {
			System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %-3d \n",
					dto.getSno(),dto.getLpname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
		}	
	}
	// 전채 공항 목록
	public ArrayList<Airport> Airport() {
		return Adao.getInstance().Airport();
	}
	// 전채 비행기 목록
	public void LP() {
		ArrayList<LP> lplist = Adao.getInstance().LP();
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("%-2s %-8s %-12s %-8s %-8s\n",
							"번호","비행편명","소속 항공사","비행기종","최대수용인원");		
		System.out.println("------------------------------------------------------------------------------------------------");
		
		for(LP lp: lplist) {
			System.out.printf("%-2d %-10s %-13s %-10s %-5d\n",
					lp.getLpno(),lp.getLpname(),lp.getLname(),lp.getAname(),lp.getAmax());
		}
	}
	// 경로 설정 유효성 검사
	public int APcheck(String dpname,String apname) {
		// 공항 목록에 있는 공항인지 확인
		int check = 0;
		ArrayList<Airport> aplist = Adao.getInstance().Airport();
		for(Airport ap : aplist) {
			if(ap.getPname().equals(dpname)) { check++; }
			if(ap.getPname().equals(apname)) { check++; }
		}
		if(check != 2) {return 2;}
		// 출발지 도착지 동일 여부 검사 
		if(dpname.equals(apname)) {return 3;}
		return 1;
	}
	// 출발일 도착일 순서 확인 검사 ( 출발일이 도착일보다 빠른 날짜가 맞는지 )
	public boolean dateCheck(String ddate,String dtime,String adate,String atime){	
		// 날짜와 시간 합쳐서 등록
		ddate = ddate+" "+dtime;
		adate = adate+" "+atime;
		// 출발일이 도착일보다 빠른 날짜가 맞는지 확인 + 입력양식 유효성 검사
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date1 = formatter.parse(ddate);       
	        Date date2 = formatter.parse(adate);     
	        if(date2.after(date1)) {return true;}	        
		}catch (Exception e) {System.out.println(e);}

		return false;
	}
	// 스케줄 등록
	public boolean scheduleRegister(String dpname,String apname,String ddate,String dtime,String adate,String atime,String lpname,int price) {
		// 날짜와 시간 합쳐서 등록
		ddate = ddate+" "+dtime;
		adate = adate+" "+atime;
		
		Schedule scdto = new Schedule(0,lpname, dpname, apname, ddate, adate,price,0);
		
		boolean result = Adao.getInstance().scheduleRegister(scdto);
		
		return result;
	}
	// 선택한 스케줄 출력
	public void scheduleUpdate(int sno) {
		ArrayList<Schedule> slist  =  Adao.getInstance().schedulePrint();
		for(Schedule dto:slist) {
			if(dto.getSno()==sno) {
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.printf("%-2s %-8s %-8s %-19s %-8s %-19s %-8s %-3s \n",
						"번호","비행편명","출발지","출발일정","도착지","도착일정","가격","잔여좌석");		
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.printf("%2d %-10s %-8s %-20s %-8s %-20s %-8d %-3d \n",
						dto.getSno(),dto.getLpname(),dto.getDpname(),dto.getDtime(),dto.getApname(),dto.getAtime(),dto.getPrice(),dto.getRseats());
				System.out.println("------------------------------------------------------------------------------------------------");
			}
		}
	}
	// 경로 재설정
	public boolean scheduleUpdate_AP(int sno,String dpname,String apname) {
		return Adao.getInstance().scheduleUpdate_AP(sno, dpname, apname);
	}
	// 일정 재설정
	public boolean scheduleUpdate_DD(int sno,String ddate,String dtime,String adate,String atime) {
		return Adao.getInstance().scheduleUpdate_DD(sno, ddate,dtime, adate,atime);
	}
	// 비행편 재설정
	public boolean scheduleUpdate_LP(int sno,String lpname) {
		return Adao.getInstance().scheduleUpdate_LP(sno, lpname);
	}
	// 가격 재설정
	public boolean scheduleUpdate_PR(int sno,int price) {
		return Adao.getInstance().scheduleUpdate_PR(sno, price);
	}
	// 특정 스케쥴 삭제
	public boolean scheduleDelete(int sno) {
		boolean result = Adao.getInstance().scheduleDelete(sno);		
		return result;
	}
	// 항공사별 매출 결산
	public void alRank() {
		ArrayList<rankDto> alrlist = Adao.getInstance().alRank();
		for(int i=0 ; i < alrlist.size(); i++) {
			System.out.printf("%3d %-15s %-20d \n",
					(i+1),alrlist.get(i).getName(),alrlist.get(i).getCount());		
		}
	}	
	// 공항별 이용객수 결산
	public void apRank() {
			
	}
}
