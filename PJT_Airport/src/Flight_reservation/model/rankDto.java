package Flight_reservation.model;

public class rankDto {

	private String name ;
	private int    count;
	
	public rankDto() {
		// TODO Auto-generated constructor stub
	}

	public rankDto(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}

	@Override
	public String toString() {
		return "rankDto [name=" + name + ", count=" + count + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	
	
}
