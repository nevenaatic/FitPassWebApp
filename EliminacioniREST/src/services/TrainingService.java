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

import dao.TrainingDao;
import dao.UserDao;
import model.Training;
import model.User;

@Path("/training")
public class TrainingService {

	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	
	public TrainingService() {}
	
	private TrainingDao getTrainings() {
		TrainingDao trainings = (TrainingDao)context.getAttribute("trainings");
		if(trainings == null) {
			trainings = new TrainingDao();
			context.setAttribute( "trainings",trainings);
		}
			return trainings;
	}
	
	@POST
	@Path("/placeTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getTrainingsForPlace(String id) {
		TrainingDao trainings = getTrainings();
		ArrayList<Training> ret = new ArrayList<Training>();
		for (Training training : trainings.getTrainingsForPlace(Integer.parseInt(id))) {
			if(!training.getDeleted()) {
				ret.add(training);
			}
		}
		return ret;
	}
	
	
	@GET
	@Path("/trainingsForCoach")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getTrainingsForCoach() {
		TrainingDao trainings = getTrainings();
		ArrayList<Training> ret = new ArrayList<Training>();
		User user = (User)request.getSession().getAttribute("loginUser");
		for (Training training : trainings.getValues()) {
			if(!training.getDeleted() && training.getUsernameCoach().equals(user.getUsername())) {
				ret.add(training);
			}
		}
		return ret;
	}
}
