package Flight_reservation.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Mdao extends Dao{
	private static Mdao mdao = new Mdao();
	private Mdao() {}
	public static Mdao getInstance() { return mdao;}
	
	Member memberDto = new Member();
	
	public boolean idCheck( String mid ) {
		String sql = "select * from member where mid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			rs = ps.executeQuery();
			if( rs.next() ) { return true; } 
			else { return false; }
		}
		catch (Exception e) {System.out.println(e);}
		return true; 
	}
	
	public int login( String mid , String mpw  ) {
		String sql = "select * from member where mid = ? and mpw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			ps.setString( 2 , mpw );
			rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt( 1 );
			}else { 
				return 0; 
			}
		}catch (Exception e) {System.out.println(e);}
		
		return 0;
	}
	
	public int signup( Member memberDto ) {
		String sql ="insert into member( mid , mpw , mname , mphone )"
				+ " values(  ? , ? , ? , ? )";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , memberDto.getMid() );		
			ps.setString( 2 , memberDto.getMpw() );		
			ps.setString( 3 , memberDto.getMname() );	
			ps.setString( 4 , memberDto.getMphone() );	
			ps.executeUpdate();
			return 1;  
		}catch (Exception e) {System.out.println(e);}
		return 3; 
	}
	
	
	// --------------------- 이경석-------------------
	//예약내역 출력 
	public ArrayList<Reservation> Myreser(int loginsession) {
		//비행표 리스트
		ArrayList<Reservation> rlist = new ArrayList<>();
		
		String sql = "select r.rno'예약번호', r.sno '스케쥴번호' ,r.mno'회원번호' , "
				   + "r.men '인원수', r.tprice '총가격' ,al.lname '항공사' , "
				   + "ap.pname '출발지', ap2.pname '도착지', s.dtime '비행일'   "
				   + "from reservation r , schedule s  , airline al , Lp lp , airport ap , airport ap2 "
				   + "where r.sno = s.sno and al.lno = lp.lno and s.dpno = ap.pno and s.apno = ap2.pno and s.lpno = lp.lpno "
				   + "r.mno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginsession);
			rs = ps.executeQuery();
			
			while(rs.next()) { //표를 2개이상 끊는다면?
				Reservation reservation = new Reservation(
				rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),
				rs.getInt(5),rs.getString(6), rs.getString(7),
				rs.getString(8),rs.getString(9));
				
				rlist.add(reservation);
			}
			return rlist;
			
		} catch (SQLException e) {System.out.println(e);}
		return null;
	}
	
	//예약 취소
	public boolean MYcancle(int loginsession) {
		String sql = "delete from reservation where mno = ";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, loginsession);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {System.out.println(e);}
			return false;
	}
	
	
}
