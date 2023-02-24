package Flight_reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static Dao dao = new Dao();
	private Dao() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flight_reservation","root","1234");
		}catch (Exception e) { System.out.println( e ); }
	}
	public static Dao getInstance() { return dao; }
	
}
