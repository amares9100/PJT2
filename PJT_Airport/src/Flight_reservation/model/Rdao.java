package Flight_reservation.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Rdao extends Dao{
	private static Rdao rdao = new Rdao();
	private Rdao() {}
	public static Rdao getInstance() {return rdao;}
	
	
	//공항 국가 출력
	public String pnation() {
		String pnationList = "";
		int cnt = 1;
		String sql = "select DISTINCT pnation from airport;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				pnationList += cnt+ ". " + rs.getString(1) +"  ";
				cnt++;
			}
			return pnationList;
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return null;
	}
		
	// 선택된 나라의 출발지 공항 출력
	public ArrayList<Airport> Departure(String pnation) {
		ArrayList<Airport> airportList = new ArrayList<>();
		String sql = "select * from airport where  pnation ='"+pnation+"';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Airport airport = new Airport(rs.getInt(1), rs.getString(2), rs.getString(3));
				airportList.add(airport);
			}
			return airportList;
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return null;
	}
	
	// 공항이름 출력
	public String airportName(int pno) {
		String sql = "select pname,pnation from airport where  pno ="+pno+";";
		String pname = "";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				 pname = rs.getString(1)+"["+rs.getString(2)+"]";
			}
			return pname;
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return null;
	}
	
	// 선택된 나라의 도착지 공항 출력
	public ArrayList<Airport> Arrival(String pnation, int pno) {
		ArrayList<Airport> airportList = new ArrayList<>();
		String sql = "select * from airport where  pnation ='"+pnation+"'and pno != "+pno+";";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Airport airport = new Airport(rs.getInt(1), rs.getString(2), rs.getString(3));
				airportList.add(airport);
			}
			return airportList;
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return null;
	}
	
	// 나라선택 유효성 검사
	public boolean pnationCheck(String pnation) {
		String sql = "select DISTINCT pnation from airport where pnation='"+pnation+"';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {	return true;}
			else {return false;}
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return false;
	}
	
	// 공항선택 유효성 검사
	public boolean airportCheck(int dpno,String pnation) {
		String sql = "select * from airport where pnation='"+pnation+"' and pno="+dpno+";";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {	return true;}
			else {return false;}
		} catch (Exception e) {
			System.out.println("DB오류"+e);
		}return false;
	}
	
	// -------------------- 이경석 -----------------
	//비행편 검색 화면	
	public ArrayList<Reservation> flightSelect(int dpno,int apno,String dtime,int men) {
		ArrayList<Reservation> rlist = new ArrayList<>();
		
		String sql = "select s.sno '비행번호' ,al.lname '항공사명' , ap.aname '비행기명' , ap1.pname'출발지' , ap2.pname '도착지'  , s.dtime '비행일' , s.atime '도착일' , s.price  "
				+ "from schedule s, airport ap1 , airport ap2 ,LP lp , airline al , airplane ap "
				+ "where  ap1.pno = s.dpno and ap2.pno = s.apno  and lp.lno = al.lno and s.lpno = lp.lpno and lp.ano = ap.ano   "
				+ "and dpno = ? and apno = ?  and  rseats>="+men+" and dtime like '"+dtime+"%'";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dpno);
			ps.setInt(2, apno);
	
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation reservation = new Reservation(
				rs.getInt(1) , rs.getString(2) , rs.getString(3) , 
				rs.getString(4), rs.getString(5) , rs.getString(6) ,
				rs.getString(7) , rs.getInt(8) ,men );
				rlist.add(reservation);
				}
			return rlist;
		} catch (SQLException e) {System.out.println("DB오류"+e);}
		return null;
	}
	
	// 티켓예매1 (출력용)
	public Reservation ticketReservation1(int sno) {
		
		String sql = "select s.sno '스케쥴 번호' ,  al.lname '항공사명' , ap.aname '비행기명' ,  ap1.pname'출발지' , ap2.pname '도착지' , s.dtime '비행일' , s.atime '도착일' , s.price '가격' , s.rseats '남은좌석'  "
				+ "from schedule s , LP lp ,   airline al , airplane ap , airport ap1 , airport ap2 "
				+ "where s.sno = ? "
				+ "and s.lpno = lp.lpno and lp.lno = al.lno  and lp.ano = ap.ano and ap1.pno = s.dpno and ap2.pno = s.apno;";
		
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, sno);
			rs =  ps.executeQuery();
			
			while(rs.next()) {
				Reservation ticket = new Reservation(
				rs.getInt(1), rs.getString(2) , rs.getString(3),
				rs.getString(4), rs.getString(5), rs.getString(6),
				rs.getString(7), rs.getInt(8) );
				return ticket;
			}
		} catch (SQLException e) {System.out.println(e);}
		return null;
	}
	
	// 티켓예매2 (데이터 입력용)
	public boolean ticketReservation2(int men , int mno, Reservation ticket) {
		String sql = "insert into reservation (sno,mno,men,tprice) values(?,?,?,?);";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ticket.getSno());
			ps.setInt(2, mno);
			ps.setInt(3, men);
			ps.setInt(4, ticket.getTprice());
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {System.out.println(e);}
		return false;
	}
	
	
}