package dao;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import dto.KupacTrainingDto;
import enums.MembershipStatus;
import model.Membership;
import model.TrainingHistory;
import model.User;

public class TrainingHistoryDao {

	private HashMap<String,TrainingHistory> trainingHistory;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
//	private MembershipDao getMemberships() {
//		MembershipDao memberships = (MembershipDao)context.getAttribute("memberships");
//		
//		if (memberships == null) {
//			String contextPath = context.getRealPath("");
//			memberships = new MembershipDao(contextPath);
//			context.setAttribute("memberships", memberships);
//		}
//		return memberships;
//	}
	
	
	public HashMap<String, TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}
	public void setTrainingHistorys(HashMap<String, TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public TrainingHistoryDao(HashMap<String, TrainingHistory> trainingHistory, String filePath) {
		super();
		this.trainingHistory = trainingHistory;
		this.filePath = filePath;
	}
	
	public TrainingHistoryDao(String contextPath) {
		this.setTrainingHistorys(new HashMap<String, TrainingHistory>());
		this.setFilePath(contextPath);
		
		loadTrainingHistory(contextPath);
	}
	
	
	//ucitavanje sportskih objekata iz fajla
	@SuppressWarnings("unchecked")
	private void loadTrainingHistory(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/trainingHistory.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, TrainingHistory.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.trainingHistory = ((HashMap<String, TrainingHistory>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringTrainingHistory = objectMapper.writeValueAsString(trainingHistory);
				fileWriter.write(stringTrainingHistory);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//ucitavanje sportskih objekata u fajl
	public void saveTrainingHistory() {
		
		File f = new File("WebContent/data/trainingHistory.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringTrainingHistory = objectMapper.writeValueAsString(this.trainingHistory);
			fileWriter.write(stringTrainingHistory);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	public void checkInForTraining(TrainingHistoryDto dto) {
//		
//		Date now = new Date();
//		String nowString = Long.toString(now.getTime());
//		
//		TrainingHistory m = new TrainingHistory();
//		m.setId(nowString.substring(nowString.length() - 10));
//		m.setType(TrainingHistoryType.values()[Integer.parseInt(dto.getTrainingHistoryType())]);
//		m.setPaidDate(now);
//		m.setDateValidFrom(now);
//		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(now);
//		
//		if(m.getType() == TrainingHistoryType.MESECNA) {
//			cal.add(Calendar.MONTH, 1);
//			m.setDateValidTo(cal.getTime());
//			m.setNumberOfTerms(30);
//		}
//		
//		if(m.getType() == TrainingHistoryType.GODISNJA) {
//			cal.add(Calendar.YEAR, 1);
//			m.setDateValidTo(cal.getTime());
//			m.setNumberOfTerms(365);
//		}
//		
//		m.setPrice(Double.parseDouble(dto.getPrice()));
//		m.setUsernameCustomer(dto.getUsernameCustomer());
//		m.setTrainingHistoryStatus(TrainingHistoryStatus.AKTIVNA);
//		m.setPlaceId(Integer.parseInt(dto.getPlaceId()));
//		
//		
//		trainingHistorys.put(m.getId(), m);
//		saveTrainingHistorys();
//	}
	
	
	
	public Collection<TrainingHistory> getMyTrainingHistory(String username) {
		ArrayList<TrainingHistory> ret = new ArrayList<>();
		for(TrainingHistory t: getValues() ) {
			if(t.getUsernameCustomer().equals(username)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	
	public Collection<TrainingHistory> getValues(){
		loadTrainingHistory("");
		return trainingHistory.values();
		}
	
	private Collection<Membership> getMemberships(){
		MembershipDao mdao = new MembershipDao("");
		return mdao.getValues();
	}
	
	public void checkInForTraining(TrainingHistory th, User coach) {
		System.out.println("DATUM = ");
		System.out.println(th.getStartDate());
		
		//provera da li ima clanarinu 
		boolean foundActiveMembership = false;
		for(Membership m: getMemberships()) {
			if(m.getMembershipStatus() == MembershipStatus.AKTIVNA && 
				m.getUsernameCustomer().equals(th.getUsernameCustomer()) && 
				m.getPlaceId() == th.getPlaceId()
			)
			{
				foundActiveMembership = true;
				if(m.getNumberOfTerms() > 0)
					m.setNumberOfTerms(m.getNumberOfTerms() - 1);
				else
					return;
			}
		}
		
		//ako nema clanarinu izadji iz funkcije(ne belezi trening)
		if(foundActiveMembership == false) return;
		
		//Date now = new Date();
		//String nowString = Long.toString(now.getTime());
		String nowString = Long.toString(th.getStartDate().getTime());
		th.setStartDate(th.getStartDate());
		th.setCanceled(false);
		System.out.println(coach.getSurname());
		th.setUsernameCoach(coach.getUsername());
		
		trainingHistory.put(nowString, th);
		saveTrainingHistory();
		

	}
	public boolean canICancel(TrainingHistory th) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date()); //danasnji
		
		
		
		Calendar daysBeforeTraining = Calendar.getInstance();
		daysBeforeTraining.setTime(th.getStartDate()); 
		daysBeforeTraining.add(Calendar.DAY_OF_MONTH, -2);
	
		
		if(daysBeforeTraining.getTime().after(today.getTime())) {
			return true; 
		}
		return false;
		
		
	}
}
