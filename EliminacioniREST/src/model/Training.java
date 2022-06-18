package model;

import enums.TrainingType;

public class Training {

	private int idTraining;
	private String name;
	private TrainingType type;
	private int idPlace; //objekat kom pripada 
	private int duration; //minuti ili sati
	private String usernameCoach; //username jedinstven
	private String description;
	private String image;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainingType getType() {
		return type;
	}
	public void setType(TrainingType type) {
		this.type = type;
	}
	public int getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getUsernameCoach() {
		return usernameCoach;
	}
	public void setUsernameCoach(String usernameCoach) {
		this.usernameCoach = usernameCoach;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Training(String name, TrainingType type, int idPlace, int duration, String usernameCoach, String description,
			String image) {
		super();
		this.name = name;
		this.type = type;
		this.idPlace = idPlace;
		this.duration = duration;
		this.usernameCoach = usernameCoach;
		this.description = description;
		this.image = image;
	}
	public Training() {
		super();
	} 
	
	
	
	
}
