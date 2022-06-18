package services;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.UserDao;
import dto.UserLoginDto;
import dto.UserRegistrationDto;
import enums.Role;
import model.User;

@Path("/user")
public class UserService {

	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public UserService() {
		
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
	
	
	@GET
	@Path("/getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAll() {
		UserDao users = getUsers();
		ArrayList<User> ret = new ArrayList<User>();
		for (User user : users.getValues()) {
			if(!user.getDeleted()) {
				ret.add(user);
			}
		}
		return ret;
	}
	
	
	
	@POST
	@Path("/registration")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registration(UserRegistrationDto user) {
		UserDao users = getUsers();

		if (users.getUserByUsername(user.username) != null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Uneto korisnicko ime je vec zauzeto.Molimo unesite drugo.").build();
		}
	
		users.registerUser(user);
		return Response.status(Response.Status.ACCEPTED).entity("/EliminacioniREST/index.html").build();	//redirekcija na logovanje																			
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserLoginDto user) {
		UserDao users = getUsers();
		
		User userForLogin = users.getUserByUsername(user.username);
		
		if(userForLogin == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Korisnicko ime je pogresno!Probajte ponovo!!").build();
		}
		
		if(!userForLogin.getPassword().equals(user.password)) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Lozinka koju ste uneli je pogresna!Probajte ponovo!!").build();
		}
		
		if (userForLogin.getBlocked()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Blokirani ste!").build();
		}
		
		request.getSession().setAttribute("loginUser", userForLogin); //kacimo sesiju za korisnika
		
		if(userForLogin.getRole().equals(Role.ADMIN)) {
			return Response.status(Response.Status.ACCEPTED).entity("/EliminacioniREST/html/admin_profil.html").build();
		}
		
		if(userForLogin.getRole().equals(Role.MENADZER)) {
			return Response.status(Response.Status.ACCEPTED).entity("/EliminacioniREST/html/menadzer_profil.html").build();
		}
		
		if(userForLogin.getRole().equals(Role.TRENER)) {
			return Response.status(Response.Status.ACCEPTED).entity("/EliminacioniREST/html/trener_profil.html").build();
		}
		
		if(userForLogin.getRole().equals(Role.KUPAC)) {
			
			return Response.status(Response.Status.ACCEPTED).entity("/EliminacioniREST/html/kupac_profil.html").build();
		}
		
		return Response.status(Response.Status.ACCEPTED).entity("/WebShopREST/index.html").build();
	}
	
}
