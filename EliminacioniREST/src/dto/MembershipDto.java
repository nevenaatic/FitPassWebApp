package dto;

public class MembershipDto {
	
	private String placeId;
	private String membershipType;
	private String price;
	private String usernameCustomer;
	
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUsernameCustomer() {
		return usernameCustomer;
	}
	public void setUsernameCustomer(String usernameCustomer) {
		this.usernameCustomer = usernameCustomer;
	}
	
	

}
