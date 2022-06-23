package services;

import javax.ws.rs.Path;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.UserDao;
import dto.ChangeUserProfileDto;

import model.User;

@Path("/profile")
public class UserProfileService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext context;
	
	public UserProfileService() {}
	
	
	
	@GET
	@Path("/profileUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInformations() {
		User userSesion = (User)request.getSession().getAttribute("loginUser");	
		UserDao users = getUsers();
		User user = users.getUserByUsername(userSesion.getUsername());
		return user;
	}

	//izmena profila
	@POST
	@Path("/changeProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeUser(ChangeUserProfileDto user) {
		UserDao users = getUsers();
		users.changeUserProfile(user);	
	} 
	
	private UserDao getUsers() {
		UserDao users = (UserDao)context.getAttribute("users");
		
		if (users == null) {
			String contextPath = context.getRealPath("");
			users = new UserDao(contextPath);
			context.setAttribute("users", users);
		}
	
		return users;
	}
}
	

