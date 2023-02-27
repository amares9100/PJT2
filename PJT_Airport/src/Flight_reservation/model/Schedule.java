package Flight_reservation.model;

import java.util.Date;

public class Schedule {
	private int sno;//스케줄번호
	private int lpno;//비행편
	private int dpno;//출발지
	private int apno;//도착지
    private Date dtime;//출발시간
    private Date atime;//도착시간
    private int price;
    private int rseats;
    
    private String Kdpno;
    private String Kapno;
    
    public Schedule() {}

    
    
    
	public Schedule(int sno, int lpno, int dpno, int apno, Date dtime, Date atime, int price, int rseats) {
		super();
		this.sno = sno;
		this.lpno = lpno;
		this.dpno = dpno;
		this.apno = apno;
		this.dtime = dtime;
		this.atime = atime;
		this.price = price;
		this.rseats = rseats;
	}

	@Override
	public String toString() {
		return "Schedule [sno=" + sno + ", lpno=" + lpno + ", dpno=" + dpno + ", apno=" + apno + ", dtime=" + dtime
				+ ", atime=" + atime + ", price=" + price + ", rseats=" + rseats + "]";
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

	public int getDpno() {
		return dpno;
	}

	public void setDpno(int dpno) {
		this.dpno = dpno;
	}

	public int getApno() {
		return apno;
	}

	public void setApno(int apno) {
		this.apno = apno;
	}

	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	public Date getAtime() {
		return atime;
	}

	public void setAtime(Date atime) {
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

	
	public void Arrival() {
		
	}
    
    
}
