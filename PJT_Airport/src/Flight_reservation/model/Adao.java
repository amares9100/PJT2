package Flight_reservation.model;

import java.sql.SQLException;
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
		// 검색된 스케줄 목록		
		public ArrayList<Schedule> schedulePrint_DP(String pname,String ddate) {		
			ArrayList<Schedule> splist = new ArrayList<>();
			String sql = "select s.sno,s.dtime,s.atime,s.price,s.rseats,l.lpname,a.pname,a2.pname from schedule s, LP l, airport a, airport a2 where s.lpno = l.lpno and s.dpno = a.pno and s.apno = a2.pno and a.pname = ? and DATE(s.dtime) = ?;";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, pname);
				ps.setString(2, ddate);
				rs = ps.executeQuery();
				while(rs.next()) {
					Schedule sdto = new Schedule(rs.getInt(1), rs.getString(6), rs.getString(7), rs.getString(8), 
							rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5)) ;
					splist.add(sdto);
				}
			}catch (Exception e) {System.out.println(e);}
			
			return splist;
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
		// 선택 스케쥴 삭제
		public boolean scheduleDelete(int sno) {
			String sql = "delete from schedule where sno = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, sno);
				ps.executeUpdate();
				return true;
			}catch (Exception e) {System.out.println(e);}
			return false;
		}
		// 항공사별 매출 결산
		public ArrayList<rankDto> ALRank() {
			ArrayList<rankDto> alrlist = new ArrayList<>();
			String sql = "select al.lname, sum(((ap.amax-s.rseats) *s.price)) as total from airplane ap, schedule s, airline al, LP where lp.ano = ap.ano and al.lno = lp.lno group by al.lname order by total desc ;";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();			
				while(rs.next()) {
					rankDto rankDto = new rankDto(rs.getString(1), rs.getLong(2));
					alrlist.add(rankDto);				
				}// while e	
				return alrlist;
			}catch (Exception e) {System.out.println(e);}	
			return null;
		}
		// 공항별 이용객수 결산
		public ArrayList<rankDto> APRANK() {
			ArrayList<rankDto> aprlist = new ArrayList<>();
			String sql = "select p.pname, sum(ap.amax-s.rseats) as total from schedule s,LP , airport p, airplane ap where s.dpno = p.pno and s.lpno = LP.lpno and lp.ano = ap.ano group by p.pname order by total desc;";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();			
				while(rs.next()) {
					rankDto rankDto = new rankDto(rs.getString(1), rs.getLong(2));
					aprlist.add(rankDto);				
				}// while e	
				return aprlist;
			}catch (Exception e) {System.out.println(e);}	
			return null;
		}
		// 예약 확인
		public ArrayList<Reservation> Aresevation() {
			ArrayList<Reservation> rlist = new ArrayList<>();
			String sql = "select * from reservation";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();			
				while(rs.next()) {
					Reservation reservation = new Reservation(rs.getInt(1),rs.getInt(2) ,rs.getInt(3),rs.getInt(4), rs.getInt(5),
							null,null,null,null,null,0, 0);
					rlist.add(reservation);				
				}// while e	
				return rlist;
			}catch (Exception e) {System.out.println(e);}	
			return null;
		}
		// 예약 상세보기
		public Reservation AresevView(int rno) {
			Reservation re = new Reservation();
			String sql = "select r.rno , r.sno, r.mno, r.tprice,r.men, m.mid, al.lname, lp.lpname, ap.pname, ap1.pname, s.dtime, s.atime, s.rseats , t.arate from reservation r, schedule s , LP lp ,  airline al , airport ap , airport ap1 , member m,tier_table t where r.sno = s.sno and r.mno = m.mno and s.lpno = lp.lpno and lp.lno = al.lno and ap.pno = s.dpno and ap1.pno = s.apno and m.tier = t.tier and rno= ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rno);
				rs = ps.executeQuery();			
				while(rs.next()) {
					re.setRno(rs.getInt(1));
					re.setSno(rs.getInt(2));
					re.setTprice(rs.getInt(4)); 
					re.setMno(rs.getInt(3));
					re.setMen(rs.getInt(5));
					re.setLname(rs.getString(7));
					re.setAname(rs.getString(8));
					re.setDeparture(rs.getString(9));
					re.setDate(rs.getString(11));
					re.setArrival(rs.getString(10));
					re.setAdate(rs.getString(12));
					re.setTier(rs.getString(6));
					re.setRseats(rs.getInt(13));
					re.setArate(rs.getInt(14));
				}// while e	
				return re;
			}catch (Exception e) {System.out.println(e);}	
			return null;
		}
		// 예약 취소
		public boolean AresevCancle(int rno) {
			String sql = "delete from reservation where rno = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rno);
				ps.executeUpdate();
				return true;		
			}catch (Exception e) {System.out.println(e);}	
			return false;
		}
		// 예약 취소시 마일리지 반환 
		public boolean mileagPoints(int mno, int Mileage) {
			String sql ="select Mileage from member where mno= ? ";		
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, mno);
				rs = ps.executeQuery();	
				while(rs.next()) {
					String sql2 = "update  member  set  Mileage ="+ (rs.getInt(1)-Mileage) +" where mno="+mno ;
					ps=conn.prepareStatement(sql2);
					ps.executeUpdate();
					return true;
				}// while e	
			} catch (SQLException e) {System.out.println(e);}
			return false;
		}
		
		// 예약 취소시 좌석 반환
		public boolean rseatsUpdate(int sno , int men  ) {
			String sql ="select rseats from schedule where sno="+sno;	
			try {
				ps=conn.prepareStatement(sql);
				rs = ps.executeQuery();						
				while(rs.next()) {
					String sql2 = "update schedule set  rseats ="+ (rs.getInt(1)+men) +" where sno="+sno ;
					ps=conn.prepareStatement(sql2);
					ps.executeUpdate();
				}// while e				
			} catch (SQLException e) {System.out.println(e);}		
			return true;
		}
		
	}
