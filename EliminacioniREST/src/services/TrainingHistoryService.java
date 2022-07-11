package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import dao.UserDao;
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
	
	private UserDao getUsers() {
		UserDao users = (UserDao)context.getAttribute("users");	
		if (users == null) {	
			String contextPath = context.getRealPath("");
			users = new UserDao(contextPath);
			context.setAttribute("users", users);	
		}
		return users;
	}
	
	@POST
	@Path("/checkInForTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public void checkInForTraining(TrainingHistory th) {
		UserDao userDao = getUsers();
		
		getTrainingHistory().checkInForTraining(th, userDao.getUserByUsername(th.getUsernameCoach()));
	}
	
	@GET
	@Path("/getTrainingsByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<KupacTrainingDto> getTrainingsByUser() {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		User userSession = (User)request.getSession().getAttribute("loginUser");
		Collection<KupacTrainingDto> ret = new ArrayList<>();
		System.out.println("TRAIZM");
		for(TrainingHistory th: getTrainingHistory().getMyTrainingHistory(userSession.getUsername())) {
			String placeName = placeDao.getPlaceById(th.getPlaceId()).getName();
			String trainingName = trainingDao.getById(th.getIdTraining()).getName();
			ret.add(new KupacTrainingDto(th.getStartDate(), placeName, trainingName, canICancel(th),placeDao.getPlaceById(th.getPlaceId()).getType(),trainingDao.getById(th.getIdTraining()).getType(), th.isCanceled()));
		}
		
		return ret;
	}
	
	
	@GET
	@Path("/getTrainingsForCoach")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<KupacTrainingDto> getTrainingsByCoach() {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		User userSession = (User)request.getSession().getAttribute("loginUser");//trener
		Collection<KupacTrainingDto> ret = new ArrayList<>();
		System.out.println("Trazi za trenera");
		for(TrainingHistory th: getTrainingHistory().getCoachTrainingHistory(userSession.getUsername())) {
			String placeName = placeDao.getPlaceById(th.getPlaceId()).getName();
			String trainingName = trainingDao.getById(th.getIdTraining()).getName();
			ret.add(new KupacTrainingDto(th.getStartDate(), placeName, trainingName, getTrainingHistory().canCoachCancel(th,trainingDao.getById(th.getIdTraining()).getType() ) , placeDao.getPlaceById(th.getPlaceId()).getType(),trainingDao.getById(th.getIdTraining()).getType(), th.isCanceled() ));
		}
		
		return ret;
	}
	
	@POST
	@Path("/cancelCoach")
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.TEXT_HTML)
	public void cancelCoach(KupacTrainingDto dto) {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		String thTime = Long.toString(dto.getDate().getTime());
		User userSession = (User)request.getSession().getAttribute("loginUser");//trener
		Collection<TrainingHistory> ret = getTrainingHistory().getCoachTrainingHistory(userSession.getUsername());
		getTrainingHistory().cancelCoach(userSession, dto);
	
	
	}
	
	@POST
	@Path("/cancelCoach2")
	@Produces(MediaType.APPLICATION_JSON)
	public void cancelCoach(String date) {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		//String thTime = Long.toString(d.getTime());
		User userSession = (User)request.getSession().getAttribute("loginUser");//trener
		Collection<TrainingHistory> ret = getTrainingHistory().getCoachTrainingHistory(userSession.getUsername());
		//getTrainingHistory().cancelCoach(userSession, dto);
		getTrainingHistory().cancelCoach2(userSession, date);
	
	}
	
	@POST
	@Path("/cancelUser2")
	@Produces(MediaType.APPLICATION_JSON)
	public void cancelUser2(String date) {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		//String thTime = Long.toString(d.getTime());
		User userSession = (User)request.getSession().getAttribute("loginUser");//trener
		Collection<TrainingHistory> ret = getTrainingHistory().getCoachTrainingHistory(userSession.getUsername());
		//getTrainingHistory().cancelCoach(userSession, dto);
		getTrainingHistory().cancelUser2(userSession, date);
	
	}
	
	@POST
	@Path("/cancelUser")
	@Consumes(MediaType.APPLICATION_JSON)
	
	public void cancelUser(KupacTrainingDto dto) {
		PlaceDao placeDao = getPlaces();
		TrainingDao trainingDao = getTrainings();
		String thTime = Long.toString(dto.getDate().getTime());
		User userSession = (User)request.getSession().getAttribute("loginUser");//user
		Collection<TrainingHistory> ret = getTrainingHistory().getMyTrainingHistory(userSession.getUsername());
		getTrainingHistory().cancelUser(userSession, dto);
	
	
	}
	
	@POST
	@Path("/checkCancelling")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean canICancel(TrainingHistory th) {
		return getTrainingHistory().canICancel(th);
	}
}


