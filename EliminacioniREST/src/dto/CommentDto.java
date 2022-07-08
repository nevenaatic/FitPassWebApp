package dto;

public class CommentDto {
	public int idComment;
	public String usernameCustomer;
	public int idPlace;
	public String placeName;
	public String comment;
	public double grade; 
	public Boolean approved;
	public Boolean deleted;
	public CommentDto(int idComment, String usernameCustomer, int idPlace, String placeName, String comment,
			double grade, Boolean approved, Boolean deleted) {
		super();
		this.idComment = idComment;
		this.usernameCustomer = usernameCustomer;
		this.idPlace = idPlace;
		this.placeName = placeName;
		this.comment = comment;
		this.grade = grade;
		this.approved = approved;
		this.deleted = deleted;
	}
	public CommentDto() {
		super();
	}

	
	
}
