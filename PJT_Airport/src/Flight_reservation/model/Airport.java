package Flight_reservation.model;

public class Airport {
	private int pno;
	private String pname;
	private String pnation;
	
	public Airport() {}

	public Airport(int pno, String pname, String pnation) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.pnation = pnation;
	}

	@Override
	public String toString() {
		return "Airport [pno=" + pno + ", pname=" + pname + ", pnation=" + pnation + "]";
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPnation() {
		return pnation;
	}

	public void setPnation(String pnation) {
		this.pnation = pnation;
	}
	

}
