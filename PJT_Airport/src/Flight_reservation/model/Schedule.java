package Flight_reservation.model;

import java.util.Date;

public class Schedule {
	private int sno;
	private int lpno;
	private int spno;
	private int dpno;
    private Date stime;
    private Date dtime;
    private int price;
    private int rseats;
    
    public Schedule() {}

	public Schedule(int sno, int lpno, int spno, int dpno, Date stime, Date dtime, int price, int rseats) {
		super();
		this.sno = sno;
		this.lpno = lpno;
		this.spno = spno;
		this.dpno = dpno;
		this.stime = stime;
		this.dtime = dtime;
		this.price = price;
		this.rseats = rseats;
	}

	@Override
	public String toString() {
		return "Schedule [sno=" + sno + ", lpno=" + lpno + ", spno=" + spno + ", dpno=" + dpno + ", stime=" + stime
				+ ", dtime=" + dtime + ", price=" + price + ", rseats=" + rseats + "]";
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getLpno() {
		return lpno;
	}

	public void setLpno(int lpno) {
		this.lpno = lpno;
	}

	public int getSpno() {
		return spno;
	}

	public void setSpno(int spno) {
		this.spno = spno;
	}

	public int getDpno() {
		return dpno;
	}

	public void setDpno(int dpno) {
		this.dpno = dpno;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
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
