package model;

import java.util.Date;

public class TrainingHistory {

	private Date startDate; //da li Date ukljucuje i vreme?
	private int idTraining;
	private String usernameCustomer; //kupac
	private String usernameCoach; //trener ako ga ima
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getIdTraining() {
		return idTraining;
	}
	public void setIdTraining(int idTraining) {
		this.idTraining = idTraining;
	}
	public String getUsernameCustomer() {
		return usernameCustomer;
	}
	public void setUsernameCustomer(String usernameCustomer) {
		this.usernameCustomer = usernameCustomer;
	}
	public String getUsernameCoach() {
		return usernameCoach;
	}
	public void setUsernameCoach(String usernameCoach) {
		this.usernameCoach = usernameCoach;
	}
	public TrainingHistory(Date startDate, int idTraining, String usernameCustomer, String usernameCoach) {
		super();
		this.startDate = startDate;
		this.idTraining = idTraining;
		this.usernameCustomer = usernameCustomer;
		this.usernameCoach = usernameCoach;
	}
	public TrainingHistory() {
		super();
	}
	
	
}
