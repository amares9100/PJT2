package Flight_reservation.model;

public class Airplane {
	private int ano;
	private String aname;
	private int amax;
	
	public Airplane() {}

	public Airplane(int ano, String aname, int amax) {
		super();
		this.ano = ano;
		this.aname = aname;
		this.amax = amax;
	}

	@Override
	public String toString() {
		return "Airplane [ano=" + ano + ", aname=" + aname + ", amax=" + amax + "]";
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
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
	};
	
	
}
