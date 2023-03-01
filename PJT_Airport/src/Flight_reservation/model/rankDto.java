package Flight_reservation.model;

public class rankDto {

	private String  name ;
	private long    count;
	
	public rankDto() {
		// TODO Auto-generated constructor stub
	}

	public rankDto(String name, long count) {
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
}
