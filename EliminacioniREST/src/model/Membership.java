package model;

import java.util.Date;

import enums.MembershipStatus;
import enums.MembershipType;

public class Membership {
	
	private String id; //10 karaktera
	private MembershipType type;
	private Date paidDate;
	private Date dateValidFrom;
	private Date dateValidTo;  //vreme?
	private double price;
	private String usernameCustomer;
	private MembershipStatus membershipStatus;
	
	private int numberOfTerms; //dnevni broj termina, moze biti neogranicen, mozda bi mogao enum?

	public Membership(String id, MembershipType type, Date paidDate, Date dateValidFrom, Date dateValidTo, double price,
			String usernameCustomer, MembershipStatus membershipStatus, int numberOfTerms) {
		super();
		this.id = id;
		this.type = type;
		this.paidDate = paidDate;
		this.dateValidFrom = dateValidFrom;
		this.dateValidTo = dateValidTo;
		this.price = price;
		this.usernameCustomer = usernameCustomer;
		this.membershipStatus = membershipStatus;
		this.numberOfTerms = numberOfTerms;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MembershipType getType() {
		return type;
	}

	public void setType(MembershipType type) {
		this.type = type;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getDateValidFrom() {
		return dateValidFrom;
	}

	public void setDateValidFrom(Date dateValidFrom) {
		this.dateValidFrom = dateValidFrom;
	}

	public Date getDateValidTo() {
		return dateValidTo;
	}

	public void setDateValidTo(Date dateValidTo) {
		this.dateValidTo = dateValidTo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUsernameCustomer() {
		return usernameCustomer;
	}

	public void setUsernameCustomer(String usernameCustomer) {
		this.usernameCustomer = usernameCustomer;
	}

	public MembershipStatus getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(MembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public int getNumberOfTerms() {
		return numberOfTerms;
	}

	public void setNumberOfTerms(int numberOfTerms) {
		this.numberOfTerms = numberOfTerms;
	}

	public Membership() {
		super();
	}
	
	
	
	

}
