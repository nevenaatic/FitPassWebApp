package model;

import enums.CustomerType;

public class Customer {

	
	private CustomerType type;
	private double points; //bodovi do zlatnog
	private double sale; //popust na osnovu clanarine
	public Customer(CustomerType type, double points, double sale) {
		super();
		this.type = type;
		this.points = points;
		this.sale = sale;
	}
	public CustomerType getType() {
		return type;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double sale) {
		this.sale = sale;
	}
	public Customer() {
		super();
	}
	
	
}
