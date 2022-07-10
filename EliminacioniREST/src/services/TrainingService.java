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
import javax.ws.rs.core.Response;

import dao.TrainingDao;
import dao.UserDao;
import dto.NewTrainingDto;
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
		try {
			ret= (ArrayList<Training>) trainings.getTrainingsForPlace(Integer.parseInt(id));
		} catch (Exception e) {
			
		}
		return  ret;
	
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
	
	@POST
	@Path("/createTraining")
	@Produces(MediaType.TEXT_HTML)
	public Response createTraining(NewTrainingDto training) {
		TrainingDao trainingDao = getTrainings();

		if(trainingDao.checkName(training.name,training.idPlace)) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Sadrzaj sa ovim nazivom vec postoji! ").build();
		}
		trainingDao.createTraining(training);
		return Response.status(Response.Status.CREATED).build();	
	}
		
	@POST
	@Path("/editTraining")
	@Produces(MediaType.TEXT_HTML)
	public Response editTraining(Training training) {
		TrainingDao trainingDao = getTrainings();

		if(trainingDao.checkNameEdit(training.getName(), training.getIdPlace(), training.getIdTraining())) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Sadrzaj sa ovim nazivom vec postoji! ").build();
		}
		trainingDao.editTraining(training);
		return Response.status(Response.Status.NO_CONTENT).build();	
	}
	
	@POST
	@Path("/getTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Training getById(String id) {
		TrainingDao trainingDao = getTrainings();

		return trainingDao.getById(Integer.parseInt(id));
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> delete(String id) {
		TrainingDao trainingDao = getTrainings();

		return trainingDao.delete(Integer.parseInt(id));
		 
	}
}
