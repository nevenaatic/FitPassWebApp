package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.CommentDao;
import dao.PlaceDao;
import dto.CommentDto;
import model.Comment;

@Path("/comment")
public class CommentService {
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	
	private CommentDao getComments() {
		CommentDao comments = (CommentDao)context.getAttribute("comments");
		if (comments == null) {
			comments = new CommentDao();
			context.setAttribute("comments", comments);
		}
		return comments; 
	}
	private PlaceDao getPlaces() {
		PlaceDao places = (PlaceDao)context.getAttribute("places");
		if (places == null) {
			places = new PlaceDao("");
			context.setAttribute("places", places);
		}
		return places; 
	}
	
	@GET
	@Path("/getAllComments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CommentDto> getAll() {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
		
		for (Comment comment : commentsDao.getValues()) {
			ret.add(new CommentDto(comment.getIdComment(),comment.getUsernameCustomer(),comment.getIdPlace(), placeDao.getPlaceById(comment.getIdPlace()).getName(),
					comment.getComment(), comment.getGrade(), comment.getApproved(), comment.getDeleted()));
		}
		
		return ret;
	
}
	
	@GET
	@Path("/getUnapproved")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CommentDto> getUnapproved() {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
		
		for (Comment comment : commentsDao.getValues()) {
			if(!comment.getApproved()&& !comment.getDeleted()) {
			ret.add(new CommentDto(comment.getIdComment(),comment.getUsernameCustomer(),comment.getIdPlace(), placeDao.getPlaceById(comment.getIdPlace()).getName(),
					comment.getComment(), comment.getGrade(), comment.getApproved(), comment.getDeleted()));
		}
		}
		return ret;
	
}
	
	@GET
	@Path("/getUnaccepted")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CommentDto> getUnaccepted() {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
		
		for (Comment comment : commentsDao.getValues()) {
			if(comment.getDeleted()) {
			ret.add(new CommentDto(comment.getIdComment(),comment.getUsernameCustomer(),comment.getIdPlace(), placeDao.getPlaceById(comment.getIdPlace()).getName(),
					comment.getComment(), comment.getGrade(), comment.getApproved(), comment.getDeleted()));
		}
		}
		return ret;
	
}
	
	@POST
	@Path("/getCommentsForPlace")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CommentDto> getCommentsForPlace(String id) {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
		
		for (Comment comment : commentsDao.getValues()) {
			if(comment.getApproved()&& !comment.getDeleted() && comment.getIdPlace() == Integer.parseInt(id)) {
			ret.add(new CommentDto(comment.getIdComment(),comment.getUsernameCustomer(),comment.getIdPlace(), placeDao.getPlaceById(comment.getIdPlace()).getName(),
					comment.getComment(), comment.getGrade(), comment.getApproved(), comment.getDeleted()));
		}
		}
		return ret;
	
}
	@POST
	@Path("/getAllCommentsForPlace")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CommentDto> getAllCommentsForPlace(String id) {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
		
		for (Comment comment : commentsDao.getValues()) {
			if(  comment.getIdPlace() == Integer.parseInt(id)) {
				System.out.println(comment.getIdComment());
			ret.add(new CommentDto(comment.getIdComment(),comment.getUsernameCustomer(),comment.getIdPlace(), placeDao.getPlaceById(comment.getIdPlace()).getName(),
					comment.getComment(), comment.getGrade(), comment.getApproved(), comment.getDeleted()));
		}
		}
		return ret;
	
}
	
	@POST
	@Path("/approve")
	public void approve(String id) {
		CommentDao commentsDao = getComments();
		PlaceDao placeDao = getPlaces();
	 commentsDao.updateStatus(Integer.parseInt(id), "accept");
	 Comment c= commentsDao.getById(Integer.parseInt(id));
	  placeDao.updateGrade(c.getIdPlace(), commentsDao.updateGrade(c.getGrade(), c.getIdPlace()));
	 System.out.println(c.getApproved());
	
}
	
	@POST
	@Path("/reject")
	//@Produces(MediaType.APPLICATION_JSON)
	public void reject(String id) {
		ArrayList<CommentDto> ret = new ArrayList<CommentDto>();
		CommentDao commentsDao = getComments();
		 commentsDao.updateStatus(Integer.parseInt(id), "reject");
}
}
