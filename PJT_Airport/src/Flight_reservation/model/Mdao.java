package Flight_reservation.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Mdao extends Dao{
	private static Mdao mdao = new Mdao();
	private Mdao() {}
	public static Mdao getInstance() { return mdao;}
	
	Member memberDto = new Member();
	
	
	// 아이디 체크
	public boolean idCheck( String mid ) {
		String sql = "select * from member where mid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			rs = ps.executeQuery();
			if( rs.next() ) { return true; } // 중복이면 true
			else { return false; }			// 중복없으면 false
		}
		catch (Exception e) {System.out.println(e);}
		return true; 
	}
	
	// 로그인
	public int login( String mid , String mpw  ) {
		String sql = "select * from member where mid = ? and mpw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			ps.setString( 2 , mpw );
			rs = ps.executeQuery();
			if( rs.next() ) {
				if(rs.getInt(1)== 1) {
					return 1; // 관리자 mno
				}
				else if(rs.getInt(1)>1) {
					return rs.getInt( 1 ); // 검색한 회원이 있으면 mno반환
				}
			}
			else { return 0;}// 검색한 회원이 없으면 0 반환
		}catch (Exception e) {System.out.println(e);}
		
		return -1; // 오류 반환
	}
	
	//회원가입
	public boolean signup( Member memberDto ) {
		String sql ="insert into member( mid , mpw , mname , mphone )"
				+ " values(  ? , ? , ? , ? )";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , memberDto.getMid() );		
			ps.setString( 2 , memberDto.getMpw() );		
			ps.setString( 3 , memberDto.getMname() );	
			ps.setString( 4 , memberDto.getMphone() );	
			ps.executeUpdate();
			return true;  
		}catch (Exception e) {System.out.println(e);}
		return false; 
	}
	
	//아이디 찾기
		public String findid(String mname , String mphone) {
			String sql = "select * from member where mname=? and mphone=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, mname);
				ps.setString(2, mphone);
				rs = ps.executeQuery();
				String mid = null;
				while(rs.next()) {
					 mid = rs.getString(2);
					
				}
				return mid;
				
			}catch (Exception e) {System.out.println(e);}
		return null;
		}
		
		
		// 비밀번호 찾기
		public String findpw(String mid , String mname , String mphone) {
			String sql = "select * from member where mid=? and mname=? and mphone=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, mid);
				ps.setString(2, mname);
				ps.setString(3, mphone);
				rs = ps.executeQuery();
				String mpw = null;
				while(rs.next()) {
					mpw = rs.getString(3);
					
				}
				return mpw;
			}catch (Exception e) {System.out.println(e);}
		return null;
		}
		
		//회원탈퇴
		public boolean deleteId(Member memberDto) {
			
			// 입력한 정보로 회원찾기
			String sql ="select * from member where mid=? and mpw=? and mname=? and mphone=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString( 1 , memberDto.getMid() );		
				ps.setString( 2 , memberDto.getMpw() );		
				ps.setString( 3 , memberDto.getMname() );	
				ps.setString( 4 , memberDto.getMphone() );
				rs = ps.executeQuery();
				
				int mno = 0;
				while(rs.next()) {
					mno = rs.getInt(1); // 회원번호
				}
				// 해당 회원번호 레코드 삭제
				String sql2 = "delete from member where mno=?";
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, mno);
				ps.executeUpdate();		
				return true; 
			}catch (Exception e) {System.out.println(e);}
			return false;
		}
	
	// --------------------- 이경석-------------------
	//예약내역 출력 
	public ArrayList<Reservation> Myreser(int loginsession) {
		//비행표 리스트
		ArrayList<Reservation> rlist = new ArrayList<>();
		
		String sql = "select r.rno'예약번호', r.sno '스케쥴번호' ,r.mno'회원번호' ,r.men '인원수', r.tprice '총가격' ,al.lname '항공사' , ap.pname '출발지', ap2.pname '도착지', s.dtime '비행일' ,  m.tier '등급' , t_t.discount '할인가' , t_t.arate '마일리지적립' "
				+ "from reservation r , schedule s  , airline al , Lp lp , airport ap , airport ap2 , member m , tier_table t_t "
				+ "where r.sno = s.sno and al.lno = lp.lno and s.dpno = ap.pno and s.apno = ap2.pno and s.lpno = lp.lpno and r.mno = m.mno and m.tier = t_t.tier and r.mno = ? ";
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginsession);
			rs = ps.executeQuery();
			
			while(rs.next()) { //표를 2개이상 끊는다면?
				Reservation reservation = new Reservation(
				rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),
				rs.getInt(5),rs.getString(6), rs.getString(7),
				rs.getString(8),rs.getString(9),rs.getString(10),
				rs.getFloat(11),rs.getFloat(12) );
				
				rlist.add(reservation);
			}
			return rlist;
			
		} catch (SQLException e) {System.out.println(e);}
		return null;
	}
	
	//예약 취소
	public boolean MYcancle(int loginsession, int rno) {
		String sql = "delete from reservation where mno = ? and rno =?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, loginsession);
			ps.setInt(2,rno);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {System.out.println(e);}
			return false;
	}

	
	
}




























