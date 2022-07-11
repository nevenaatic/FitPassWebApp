package dto;

import enums.TrainingType;

public class NewTrainingDto {

	
	public String name;
	public TrainingType type;
	public String image;
	public String description;
	public int duration;
	public String coachUsername;
	public int idPlace;
	public int price; 
	
	public NewTrainingDto(String name, TrainingType type, String image, String description, int duration,
			String coachUsername, int idPlace, int price) {
		super();
		this.name = name;
		this.type = type;
		this.image = image;
		this.description = description;
		this.duration = duration;
		this.coachUsername = coachUsername;
		this.idPlace = idPlace;
		this.price = price;
	}
	public NewTrainingDto() {
		super();
	}
	
	
	
	
	
}
