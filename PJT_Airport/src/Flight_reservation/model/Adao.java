package Flight_reservation.model;

import java.util.ArrayList;

public class Adao extends Dao{

	// 1. 싱글톤
	private static Adao dao = new Adao();
	private Adao() { }
	public static Adao getInstance() {return dao;}
	// 스케줄 목록	10개
	public ArrayList<Schedule> schedulePrint() {		
		ArrayList<Schedule> slist = new ArrayList<>();
		String sql = "select s.sno,s.dtime,s.atime,s.price,s.rseats,l.lpname,a.pname,a2.pname from schedule s, LP l, airport a, airport a2 where s.lpno = l.lpno and s.dpno = a.pno and s.apno = a2.pno order by s.sno asc;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Schedule sdto = new Schedule(rs.getInt(1), rs.getString(6), rs.getString(7), rs.getString(8), 
						rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5)) ;
				slist.add(sdto);
			}
		}catch (Exception e) {System.out.println(e);}
		
		return slist;
	}
	// 특정 출발지 스케줄 목록		
	public ArrayList<Schedule> schedulePrint_DP(String pname) {		
		ArrayList<Schedule> splist = new ArrayList<>();
		String sql = "select s.sno,s.dtime,s.atime,s.price,s.rseats,l.lpname,a.pname,a2.pname from schedule s, LP l, airport a, airport a2 where s.lpno = l.lpno and s.dpno = a.pno and s.apno = a2.pno and a.pname = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			rs = ps.executeQuery();
			while(rs.next()) {
				Schedule sdto = new Schedule(rs.getInt(1), rs.getString(6), rs.getString(7), rs.getString(8), 
						rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5)) ;
				splist.add(sdto);
			}
		}catch (Exception e) {System.out.println(e);}
		
		return splist;
	}
	// 특정 출발일 스케줄 목록	
	public ArrayList<Schedule> schedulePrint_DD(String ddate) {		
		ArrayList<Schedule> sdlist = new ArrayList<>();
		String sql = "select s.sno,s.dtime,s.atime,s.price,s.rseats,l.lpname,a.pname,a2.pname from schedule s, LP l, airport a, airport a2 where s.lpno = l.lpno and s.dpno = a.pno and s.apno = a2.pno and DATE(s.dtime) = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ddate);
			rs = ps.executeQuery();
			while(rs.next()) {
				Schedule sdto = new Schedule(rs.getInt(1), rs.getString(6), rs.getString(7), rs.getString(8), 
						rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5)) ;
				sdlist.add(sdto);
			}
		}catch (Exception e) {System.out.println(e);}		
		return sdlist;
	}
	// 전채 공항 목록
	public ArrayList<Airport> Airport() {
		ArrayList<Airport> ListAP = new ArrayList<>();
		String sql  = "select * from Airport order by pno asc;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Airport apDto = new Airport(rs.getInt(1), rs.getString(2), rs.getString(3));
				ListAP.add(apDto);				
			}// while e			
			return ListAP;
		}catch(Exception e) { }		
		return null;
	}
	// 전채 비행기 목록
	public ArrayList<LP> LP() {
		ArrayList<LP> ListLP = new ArrayList<>();
		String sql  = "select LP.lpno, LP.lpname, AL.lname, AP.aname, AP.amax from  LP ,airline AL,airplane AP where LP.lno = AL.lno and LP.ano = AP.ano order by lpno asc;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LP lpDto = new LP(rs.getInt(1),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(2));
				ListLP.add(lpDto);				
			}// while e			
			return ListLP;
		}catch(Exception e) { }		
		return null;
	}	
	// 스케줄 등록
	public boolean scheduleRegister(Schedule scdto) {
		String sql  = 
				"insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) "
				+ "values((select lpno from lp where lpname = ?) ,"
				+ "(select pno from airport where pname = ?), "
				+ "(select pno from airport where pname = ?), "
				+ " ?,?,?,"
				+ "(select a.amax from airplane a,LP l where a.ano = l.ano and l.lpname = ?));";
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, scdto.getLpname());
			ps.setString(2, scdto.getDpname());
			ps.setString(3, scdto.getApname());
			ps.setString(4, scdto.getDtime());
			ps.setString(5, scdto.getAtime());
			ps.setInt   (6, scdto.getPrice());
			ps.setString(7, scdto.getLpname());
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}

		return false;
	}
	// 경로 재설정
	public boolean scheduleUpdate_AP(int sno,String dpname,String apname) {
		String sql = "update schedule set dpno = (select pno from airport where pname = ?) , "
				+ "apno =  (select pno from airport where pname = ?) where sno = ? ;";
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dpname);
			ps.setString(2, apname);
			ps.setInt(3, sno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 일정 재설정
	public boolean scheduleUpdate_DD(int sno,String ddate,String dtime,String adate,String atime) {
		String sql = "update schedule set dtime = ? ,atime = ? where sno = ? ;";
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, ddate+" "+dtime);
			ps.setString(2, adate+" "+atime);
			ps.setInt(3, sno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 비행편 재설정
	public boolean scheduleUpdate_LP(int sno,String lpname) {
		String sql = "update schedule set lpno = (select lpno from LP where lpname = ?) where sno = ? ;";
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, lpname);
			ps.setInt(2, sno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 가격 재설정
	public boolean scheduleUpdate_PR(int sno,int price) {
		String sql = "update schedule set price = ? where sno = ? ;";
		try {			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, sno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 특정 스케쥴 삭제
	public boolean scheduleDelete(int sno) {
		String sql = "delete * from schedule where sno = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 항공사별 매출 결산
	public ArrayList<rankDto> alRank() {
		ArrayList<rankDto> alrlist = new ArrayList<>();
		String sql = "select al.lname, sum(((ap.amax-s.rseats) *s.price)) as total from airplane ap, schedule s, airline al, LP where lp.ano = ap.ano and al.lno = lp.lno group by al.lname order by total desc ;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();			
			while(rs.next()) {
				rankDto rankDto = new rankDto(rs.getString(1), rs.getInt(2));
				alrlist.add(rankDto);				
			}// while e			
			return alrlist;
		}catch(Exception e) { }		
		return alrlist;
	}
	// 공항별 이용객수 결산
	public void APRANK() {
			
	}
	
}
