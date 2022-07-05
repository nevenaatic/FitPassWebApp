package model;

import java.util.Date;
import java.util.List;

import enums.CustomerType;
import enums.Gender;
import enums.Role;

public class User {
	
	private String username; //jedinstveni
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Date birthday;
	private Role role;
	
	private Boolean deleted;
	private Boolean blocked;
	
	private Address address;
	
	//trener
	private List<Integer> trainings;  //idjevi treninga
	
	//kupac
	private List<Integer> memberships; //idjevi clanarina
	private List<Integer> visitedPlaces;
	private double poens;
	private Customer customerInfo;
	
	//menadzer
	private int placeId; //moze ceo objekat, moze id

	public User(String username, String password, String name, String surname, Gender gender, Date birthday, Role role,
			Boolean deleted, Boolean blocked, Address address, List<Integer> trainings, List<Integer> memberships,
			List<Integer> visitedPlaces, double poens, Customer customerInfo, int place) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.deleted = deleted;
		this.blocked = blocked;
		this.address = address;
		this.trainings = trainings;
		this.memberships = memberships;
		this.visitedPlaces = visitedPlaces;
		this.poens = poens;
		this.customerInfo = customerInfo;
		this.placeId = place;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Integer> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Integer> trainings) {
		this.trainings = trainings;
	}

	public List<Integer> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Integer> memberships) {
		this.memberships = memberships;
	}

	public List<Integer> getVisitedPlaces() {
		return visitedPlaces;
	}

	public void setVisitedPlaces(List<Integer> visitedPlaces) {
		this.visitedPlaces = visitedPlaces;
	}

	public double getPoens() {
		return poens;
	}

	public void setPoens(double poens) {
		this.poens = poens;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Integer getPlace() {
		return placeId;
	}

	public void setPlace(int place) {
		this.placeId = place;
	}

	
	

}
