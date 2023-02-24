package Flight_reservation.model;

public class Tier_table {
	private String tier;
	private int Mileage;
	private float discount;
	
	public Tier_table() {}

	public Tier_table(String tier, int mileage, float discount) {
		super();
		this.tier = tier;
		Mileage = mileage;
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Tier_table [tier=" + tier + ", Mileage=" + Mileage + ", discount=" + discount + "]";
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

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
}
