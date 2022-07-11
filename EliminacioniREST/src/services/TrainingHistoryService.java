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

import dao.MembershipDao;
import dao.PlaceDao;
import dao.TrainingDao;
import dao.TrainingHistoryDao;
import dto.KupacTrainingDto;
import dto.MembershipViewDto;
import model.Place;
import model.TrainingHistory;
import model.User;

@Path("/trainingHistory")
public class TrainingHistoryService {

	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	private TrainingHistoryDao getTrainingHistory() {
		TrainingHistoryDao thd = (TrainingHistoryDao)context.getAttribute("trainingHistory");
		
		if (thd == null) {
			String contextPath = context.getRealPath("");
			thd = new TrainingHistoryDao(contextPath);
			context.setAttribute("trainingHistory", thd);
		}
		return thd;
	}

	private PlaceDao getPlaces() {
		PlaceDao places = (PlaceDao)context.getAttribute("places");
		
		if (places == null) {
			String contextPath = context.getRealPath("");
			places = new PlaceDao(contextPath);
			context.setAttribute("places", places);
		}
		return places;
	}
	
	private TrainingDao getTrainings() {
		TrainingDao trainings = (TrainingDao)context.getAttribute("trainings");
		if(trainings == null) {
			trainings = new TrainingDao();
			context.setAttribute( "trainings",trainings);
		}
			return trainings;
	}
	
	@POST
	@Path("/checkInForTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public void checkInForTraining(TrainingHistory th) {
		getTrainingHistory().checkInForTraining(th);
	}
	
	@GET
	@Path("/getTrainingsByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<KupacTrainingDto> getTrainingsByUser() {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		User userSession = (User)request.getSession().getAttribute("loginUser");
		Collection<KupacTrainingDto> ret = new ArrayList<>();
		
		for(TrainingHistory th: getTrainingHistory().getMyTrainingHistory(userSession.getUsername())) {
			String placeName = placeDao.getPlaceById(th.getPlaceId()).getName();
			String trainingName = trainingDao.getById(th.getIdTraining()).getName();
			ret.add(new KupacTrainingDto(th.getStartDate(), placeName, trainingName));
		}
		
		return ret;
	}
}
