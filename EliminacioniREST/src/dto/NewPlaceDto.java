package dto;

import enums.PlaceType;
import enums.Status;
import model.Address;

public class NewPlaceDto {
	
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

}
