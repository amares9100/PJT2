package Flight_reservation.model;

import java.util.Date;

public class Schedule {
	private int sno;//스케줄번호
	private String lpname;//비행편
	private String dpname;//출발지
	private String apname;//도착지
    private String dtime;//출발시간
    private String atime;//도착시간
    private int price;
    private int rseats;
    
    public Schedule() {}

	public Schedule(int sno, String lpname, String dpname, String apname, String dtime, String atime, int price,
			int rseats) {
		super();
		this.sno = sno;
		this.lpname = lpname;
		this.dpname = dpname;
		this.apname = apname;
		this.dtime = dtime;
		this.atime = atime;
		this.price = price;
		this.rseats = rseats;
	}

	@Override
	public String toString() {
		return "Schedule [sno=" + sno + ", lpname=" + lpname + ", dpname=" + dpname + ", apname=" + apname + ", dtime="
				+ dtime + ", atime=" + atime + ", price=" + price + ", rseats=" + rseats + "]";
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getLpname() {
		return lpname;
	}

	public void setLpname(String lpname) {
		this.lpname = lpname;
	}

	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}

	public String getApname() {
		return apname;
	}

	public void setApname(String apname) {
		this.apname = apname;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRseats() {
		return rseats;
	}

	public void setRseats(int rseats) {
		this.rseats = rseats;
	}

    
    
    
}
