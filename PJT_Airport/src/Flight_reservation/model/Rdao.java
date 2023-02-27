package Flight_reservation.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Rdao extends Dao{

	
	
	
	
	public ArrayList<Schedule> Departure(int dpno) {
	ArrayList<Schedule> sList = new ArrayList<>();
	
	String sql = "select * from schedule where ipno=?";
		
		try {
			Rdao.getInstance().ps = conn.prepareStatement(sql);
			ps.setInt(1, dpno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Schedule sch = new Schedule(
				rs.getInt(1),rs.getInt(2), 	rs.getInt(3),rs.getInt(4),
				rs.getDate(5),rs.getDate(6),rs.getInt(7),rs.getInt(8)
				);	
				
				
				sList.add(sch);
			}// while
			
			
			
			return sList;
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;
	}//Departure e
}
