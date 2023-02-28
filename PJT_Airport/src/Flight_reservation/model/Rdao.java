package Flight_reservation.model;

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
	
}