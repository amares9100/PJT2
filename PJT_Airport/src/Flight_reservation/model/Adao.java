package Flight_reservation.model;

import java.util.ArrayList;

public class Adao extends Dao{

	// 1. 싱글톤
	private static Adao dao = new Adao();
	private Adao() { }
	public static Adao getInstance() {return dao;}
	
	public ArrayList<Schedule> schedulePrint() {
		
		ArrayList<Schedule> slist = new ArrayList<>();
		String sql = "select s.sno,s.dtime,s.atime,s.price,s.rseats,l.lpname,a.pname,a2.pname from schedule s, LP l, airport a, airport a2 where s.lpno = l.lpno and s.dpno = a.pno and s.apno = a2.pno order by a.pname asc limit 10 ;";
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
	
	
	public boolean scheduleRegister(Schedule scdto) {
		String sql  = "insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats)";
		return false;
	}
	
	public void scheduleUpdate() {
		
	}
	
	public void scheduleDelete() {
		
	}
	
}
