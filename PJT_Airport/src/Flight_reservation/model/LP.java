package Flight_reservation.model;

public class LP {
	private int lpno;
	private int lno;
	private int ano;
	
	public LP() {}

	public LP(int lpno, int lno, int ano) {
		super();
		this.lpno = lpno;
		this.lno = lno;
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "LP [lpno=" + lpno + ", lno=" + lno + ", ano=" + ano + "]";
	}

	public int getLpno() {
		return lpno;
	}

	public void setLpno(int lpno) {
		this.lpno = lpno;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
}
