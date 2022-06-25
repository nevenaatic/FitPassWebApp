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
	@Path("/populateDatabase")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void populateDatabase() {
		PlaceDao places = getPlaces();
		places.populateDatabase();											
	}
	
	
	@POST
	@Path("/newPlace")
	//@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNewPlace(NewPlaceDto newPlace) {
		System.out.println("DODAJEM");
		PlaceDao places = getPlaces();
		places.createPlace(newPlace);											
	}
}
