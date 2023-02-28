package Flight_reservation.model;

public class LP {
	private int lpno;
	private String lname;
	private String aname;
	private int amax;
	private String lpname;
	
	public LP() {}

	public LP(int lpno, String lname, String aname, int amax, String lpname) {
		super();
		this.lpno = lpno;
		this.lname = lname;
		this.aname = aname;
		this.amax = amax;
		this.lpname = lpname;
	}

	@Override
	public String toString() {
		return "LP [lpno=" + lpno + ", lname=" + lname + ", aname=" + aname + ", amax=" + amax + ", lpname=" + lpname
				+ "]";
	}

	public int getLpno() {
		return lpno;
	}

	public void setLpno(int lpno) {
		this.lpno = lpno;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public int getAmax() {
		return amax;
	}

	public void setAmax(int amax) {
		this.amax = amax;
	}

	public String getLpname() {
		return lpname;
	}

	public void setLpname(String lpname) {
		this.lpname = lpname;
	}

	
	
}