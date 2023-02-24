package Flight_reservation.model;

public class Airline {
	private int lno;
	private String lname;
	
	public Airline() {}

	public Airline(int lno, String lname) {
		super();
		this.lno = lno;
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "Airline [lno=" + lno + ", lname=" + lname + "]";
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
	
}
