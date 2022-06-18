package model;

public class Comment {

	private int idComment;
	private String usernameCustomer;
	private int idPlace;
	private String comment;
	private double grade; 
	private Boolean approved;
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public String getUsernameCustomer() {
		return usernameCustomer;
	}
	public void setUsernameCustomer(String usernameCustomer) {
		this.usernameCustomer = usernameCustomer;
	}
	public int getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Comment(int idComment, String usernameCustomer, int idPlace, String comment, double grade,
			Boolean approved) {
		super();
		this.idComment = idComment;
		this.usernameCustomer = usernameCustomer;
		this.idPlace = idPlace;
		this.comment = comment;
		this.grade = grade;
		this.approved = approved;
	}
	public Comment() {
		super();
	} 
	
	
}
