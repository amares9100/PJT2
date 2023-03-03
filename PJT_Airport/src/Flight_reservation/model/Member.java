package Flight_reservation.model;

public class Member {
	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private String mphone;
	private String tier;
	private int Mileage;
	private String rrn;
	private String gender;
	
	public Member() {}
	
	
	




	public Member(String mid, String mpw, String mname, String mphone) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphone = mphone;
	}


	
	
	
	
	public Member(String mid, String mpw, String mname, String mphone, String rrn, String gender) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphone = mphone;
		this.rrn = rrn;
		this.gender = gender;
	}







	public Member(String mname, String tier, int mileage) {
		super();
		this.mname = mname;
		this.tier = tier;
		Mileage = mileage;
	}

	public Member(int mno, String mid, String mpw, String mname, String mphone, String tier, int mileage) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mphone = mphone;
		this.tier = tier;
		Mileage = mileage;
	}

	@Override
	public String toString() {
		return "Member [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mphone=" + mphone
				+ ", tier=" + tier + ", Mileage=" + Mileage + "]";
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public int getMileage() {
		return Mileage;
	}

	public void setMileage(int mileage) {
		Mileage = mileage;
	}
	public String getRrn() {
		return rrn;
	}



	public void setRrn(String rrn) {
		this.rrn = rrn;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
