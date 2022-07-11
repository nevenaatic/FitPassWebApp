package dto;

import java.util.Date;

import enums.MembershipStatus;
import enums.MembershipType;

public class MembershipViewDto {
	public String id;
	public MembershipType type;
	public Date paidDate;
	public Date validFrom;
	public Date validTo;
	public double price;
	public MembershipStatus status;
	public String placeName;
	public int placeId;
	public MembershipViewDto(String id, MembershipType type, Date paidDate, Date validFrom, Date validTo, double price,
			MembershipStatus status, String placeName, int placeId) {
		super();
		this.id = id;
		this.type = type;
		this.paidDate = paidDate;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.price = price;
		this.status = status;
		this.placeName = placeName;
		this.placeId = placeId;
	}
	public MembershipViewDto() {
		super();
	}
	
	
}
