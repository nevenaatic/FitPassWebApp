package dto;

import java.util.Date;

import enums.Gender;
import enums.PlaceType;
import enums.Status;

public class NewPlaceWithManagerDto {
	
	public String name;
	public PlaceType type;
	public String description; //sadrzaj koji objekat ima u ponudi
	public Status status;  //otvoren-zatvoren
	public String workingTime;
	public String street; 
	public int number;
	public String city;
	public double longitude;
	public double latitude;
	public int zipCode;
	public String logo;
	public String managerName;
	public String managerSurname;
	public String managerUsername;
	public Date managerBirthday;
	public Gender managerGender;
	public String managerStreet;
	public int managerNumber;
	public String managerCity;
	public int managerZipCode;

}


