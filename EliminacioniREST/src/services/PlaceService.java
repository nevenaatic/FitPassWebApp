package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PlaceDao;
import dao.UserDao;
import dto.NewPlaceDto;
import dto.NewPlaceWithManagerDto;
import dto.UserRegistrationDto;
import model.Place;
import model.User;

@Path("/place")
public class PlaceService {

	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	
	private PlaceDao getPlaces() {
		PlaceDao places = (PlaceDao)context.getAttribute("places");
		
		if (places == null) {
		
			String contextPath = context.getRealPath("");
			places = new PlaceDao(contextPath);
			context.setAttribute("places", places);
			
		}
	
		return places;
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
	@Path("/getAllPlaces")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Place> getAll() {
		PlaceDao places = getPlaces();
		ArrayList<Place> ret = new ArrayList<Place>();
		for (Place place : places.getValues()) {
//			if(!place.getDeleted()) {
				ret.add(place);
//			}
		}
		return ret;
	}
	

	
	
	@POST
	@Path("/newPlace")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNewPlace(NewPlaceDto newPlace) {
		PlaceDao places = getPlaces();
		UserDao users = getUsers();
		User user = users.getUserByUsername(newPlace.managerId);
		Place  place = places.createPlace(newPlace);
		user.setPlace(place.getId());
		users.saveUsers();
	}
	
	
	@POST
	@Path("/newPlaceWithNewManager")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewPlace(NewPlaceWithManagerDto newPlace) {
		PlaceDao places = getPlaces();
		UserDao users = getUsers();
		if(users.getUserByUsername(newPlace.managerUsername) != null) {
			 return Response.status(Response.Status.BAD_REQUEST)
					 .build();
		}else {
			User user = users.saveNewManager(newPlace);
			Place place = places.createPlaceWithManager(newPlace, user);
			users.updateManagerPlace(user, place.getId());
			 return Response.status(Response.Status.CREATED)
					 .build();
		}												
	}
	
	@POST
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public Place findById(String id) {
		PlaceDao places = getPlaces();
		return places.getPlaceById(Integer.parseInt(id));											
	}
}
