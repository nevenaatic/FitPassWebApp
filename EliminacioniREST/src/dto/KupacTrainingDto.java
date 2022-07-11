package dto;

import java.util.Date;

public class KupacTrainingDto {

	private Date date;
	private String place;
	private String name;
	public Boolean canICancel;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public KupacTrainingDto(Date date, String place, String name) {
		super();
		this.date = date;
		this.place = place;
		this.name = name;
	}
	public KupacTrainingDto(Date date, String place, String name, Boolean type) {
		super();
		this.date = date;
		this.place = place;
		this.name = name;
		this.canICancel = type;
	}
	
}
