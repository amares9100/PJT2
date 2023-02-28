package Flight_reservation.model;

public class LP {
	private int lpno;
	private String lname;
	private int ano;
	private String lpname;
	
	public LP() {}

	public LP(int lpno, String lname, int ano, String lpname) {
		super();
		this.lpno = lpno;
		this.lname = lname;
		this.ano = ano;
		this.lpname = lpname;
	}

	@Override
	public String toString() {
		return "LP [lpno=" + lpno + ", lname=" + lname + ", ano=" + ano + ", lpname=" + lpname + "]";
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getLpname() {
		return lpname;
	}

	public void setLpname(String lpname) {
		this.lpname = lpname;
	}
	
}
