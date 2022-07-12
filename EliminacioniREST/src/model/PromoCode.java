package model;

import java.util.Date;

public class PromoCode {

	private String id;
	private Date validUntil;
	private int remainingUses;
	private float discount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
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
	
	public PromoCode() {}
	
	public PromoCode(String id, Date validUntil, int remainingUses, float discount) {
		super();
		this.id = id;
		this.validUntil = validUntil;
		this.remainingUses = remainingUses;
		this.discount = discount;
	}
	
	
}
