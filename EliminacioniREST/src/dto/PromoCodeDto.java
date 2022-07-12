package dto;

import java.util.Date;

public class PromoCodeDto {
	private String id;
	private String validDays;
	private int remainingUses;
	private float discount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValidDays() {
		return validDays;
	}
	public void setValidDays(String validDays) {
		this.validDays = validDays;
	}
	public int getRemainingUses() {
		return remainingUses;
	}
	public void setRemainingUses(int remainingUses) {
		this.remainingUses = remainingUses;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
}
