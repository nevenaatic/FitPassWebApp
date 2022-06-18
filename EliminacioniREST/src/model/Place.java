package model;

import enums.PlaceType;
import enums.Status;

public class Place {

	private int id;
	private String name;
	private PlaceType type;
	private String description; //sadrzaj koji objekat ima u ponudi
	private Status status;  //otvoren-zatvoren
	private String workingTime;
	private Address address;
	private String logo;
	private double grade;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlaceType getType() {
		return type;
	}
	public void setType(PlaceType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public Place(int id,String name, PlaceType type, String description, Status status, String workingTime, Address address,
			String logo, double grade) {
		super();
		this.id= id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.status = status;
		this.workingTime = workingTime;
		this.address = address;
		this.logo = logo;
		this.grade = grade;
	}
	public Place() {
		super();
	}
	
	
	
	
}
