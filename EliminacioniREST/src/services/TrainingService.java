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

import dao.MembershipDao;
import dao.TrainingDao;
import dao.TrainingHistoryDao;
import dao.UserDao;
import dto.NewTrainingDto;
import dto.TrainingViewDto;
import model.Training;
import model.TrainingHistory;
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
	private UserDao getUsers() {
		UserDao users = (UserDao)context.getAttribute("users");
		if(users == null) {
			users = new UserDao("");
			context.setAttribute( "users",users);
		}
			return users;
	}
	private TrainingHistoryDao getMemberships() {
		TrainingHistoryDao membership = (TrainingHistoryDao)context.getAttribute("trainingHistory");
		if(membership == null) {
			membership = new TrainingHistoryDao("");
			context.setAttribute( "trainingHistory",membership);
		}
			return membership;
	}
	
	@POST
	@Path("/placeTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingViewDto> getTrainingsForPlace(String id) {
		
		TrainingDao trainings = getTrainings();
		ArrayList<TrainingViewDto> ret = new ArrayList<TrainingViewDto>();
		try {
			for( Training t: trainings.getTrainingsForPlace(Integer.parseInt(id))) {
				if(!t.getUsernameCoach().equals("/") || t.getUsernameCoach()!= null) {
				ret.add(new TrainingViewDto(t.getIdTraining(), t.getName(), t.getType(), t.getIdPlace(),
					t.getDuration(), this.getCoachForTraining(t.getUsernameCoach()).getName(), this.getCoachForTraining(t.getUsernameCoach()).getSurname(), this.getCoachForTraining(t.getUsernameCoach()).getUsername(),"", t.getDescription(), t.getImage(), t.getDeleted(), t.getPrice()));
				}
				else {
					ret.add(new TrainingViewDto(t.getIdTraining(), t.getName(), t.getType(), t.getIdPlace(),
							t.getDuration(), "/", "/",null, "", t.getDescription(), t.getImage(), t.getDeleted(), t.getPrice()));
				}
			} 
		} catch (Exception e) {
			
		}
		return  ret;
	
	}
	
	@POST
	@Path("/getCoachesForPlace")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getCoachesForObject(String placeId) {
		UserDao users = getUsers();
		ArrayList<User> ret = new ArrayList<User>() ;
		ArrayList<User> ret2 = new ArrayList<User>() ;
		TrainingDao trainingDao = getTrainings();
		Training training = new Training();
		Collection<Training> placeTrainings = trainingDao.getTrainingsForPlace(Integer.parseInt(placeId));
		
		
		for(Training  t: placeTrainings) {
			if(!t.getUsernameCoach().equals("")|| t.getUsernameCoach()!=null ) {
			User u =users.getUserByUsername(t.getUsernameCoach());
			System.out.println(u.getUsername());
			
				ret.add(u);
			
			}
		}
	
		 ret2.add(ret.get(0)); 
		 for(User u1: ret) {
			 for(User u2: ret2) {
				
				 if(!u1.getUsername().equals(u2.getUsername()) && u1.getUsername()!=null) {
					 ret2.add(u1);
				 }
			 }
		 }
		 
		 
		return ret2;
		}

	
	@POST
	@Path("/getUsersForPlace")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsersForPlace(String placeId) {
		UserDao users = getUsers();
		ArrayList<User> ret = new ArrayList<User>() ;
		ArrayList<User> ret2 = new ArrayList<User>() ;
		TrainingHistoryDao trainingDao = getMemberships();
		TrainingHistory training = new TrainingHistory();
		Collection<TrainingHistory> placeTrainings = trainingDao.getValues();
		
		
		for(TrainingHistory  t: placeTrainings) {
			User u =users.getUserByUsername(t.getUsernameCustomer());
			
			
				ret.add(u);
			
			}
		
	
		 ret2.add(ret.get(0)); 
		 for(User u1: ret) {
			 for(User u2: ret2) {
				 if(!u1.getUsername().equals(u2.getUsername())) {
					 ret2.add(u1);
				 }
			 }
		 }
		 
		 
		return ret2;
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
	
	
	private User getCoachForTraining(String username) {
		User user = new User();
		for(User u: this.getUsers().getValues()) {
			if(u.getUsername().equals(username)) {
				user= u;
				break;
			}
		}
		return user;
	}
}
