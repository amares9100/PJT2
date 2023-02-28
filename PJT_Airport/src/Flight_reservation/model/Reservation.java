package Flight_reservation.model;

public class Reservation{
	private int rno;//예약 번호
	private int sno;//스케줄 번호
	private int mno;//회원번호 
	private int men;//인원수
	private int tprice;//총 가격
	
	public Reservation() {}

	public Reservation(int rno, int sno, int mno, int men, int tprice) {
		super();
		this.rno = rno;
		this.sno = sno;
		this.mno = mno;
		this.men = men;
		this.tprice = tprice;
	}

	@Override
	public String toString() {
		return "Reservation [rno=" + rno + ", sno=" + sno + ", mno=" + mno + ", men=" + men + ", tprice=" + tprice
				+ "]";
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getMen() {
		return men;
	}

	public void setMen(int men) {
		this.men = men;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}
	
	
}
