package dto;

import enums.TrainingType;

public class TrainingViewDto {
	public int idTraining;
	public String name;
	public TrainingType type;
	public int idPlace; //objekat kom pripada 
	public int duration; //minuti ili sati
	public String coachName; //username jedinstven
	public String coachUsername;
	public String placeName;
	public String description;
	public String image;
	public Boolean deleted;
	public int price;
	public TrainingViewDto(int idTraining, String name, TrainingType type, int idPlace, int duration, String coachName,
			String coachUsername, String placeName, String description, String image, Boolean deleted, int price) {
		super();
		this.idTraining = idTraining;
		this.name = name;
		this.type = type;
		this.idPlace = idPlace;
		this.duration = duration;
		this.coachName = coachName;
		this.coachUsername = coachUsername;
		this.placeName = placeName;
		this.description = description;
		this.image = image;
		this.deleted = deleted;
		this.price = price;
	}
	public TrainingViewDto() {
		super();
	}
	
	
	
}
