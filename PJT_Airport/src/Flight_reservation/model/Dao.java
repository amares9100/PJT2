package Flight_reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	protected static Dao dao = new Dao();
	protected Dao() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flight_reservation","root","1234");
		}catch (Exception e) { System.out.println( e ); }
	}
	public static Dao getInstance() { return dao; }
	
}
