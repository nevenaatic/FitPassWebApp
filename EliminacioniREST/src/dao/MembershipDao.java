package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import dto.MembershipDto;
import enums.MembershipStatus;
import enums.MembershipType;
import model.Membership;
import model.Place;
import java.util.Collection;

public class MembershipDao {

	private HashMap<String,Membership> memberships;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public HashMap<String, Membership> getMemberships() {
		return memberships;
	}
	public void setMemberships(HashMap<String, Membership> memberships) {
		this.memberships = memberships;
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public MembershipDao(HashMap<String, Membership> memberships, String filePath) {
		super();
		this.memberships = memberships;
		this.filePath = filePath;
	}
	
	public MembershipDao(String contextPath) {
		this.setMemberships(new HashMap<String, Membership>());
		this.setFilePath(contextPath);
		
		loadMemberships(contextPath);
	}
	
	
	//ucitavanje sportskih objekata iz fajla
	@SuppressWarnings("unchecked")
	private void loadMemberships(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/memberships.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, Membership.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.memberships = ((HashMap<String, Membership>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringMemberships = objectMapper.writeValueAsString(memberships);
				fileWriter.write(stringMemberships);
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
	public void saveMemberships() {
		
		File f = new File("WebContent/data/memberships.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringMemberships = objectMapper.writeValueAsString(this.memberships);
			fileWriter.write(stringMemberships);
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
	
	public void buyMembership(MembershipDto dto) {
		
		Date now = new Date();
		String nowString = Long.toString(now.getTime());
		
		Membership m = new Membership();
		m.setId(nowString.substring(nowString.length() - 10));
		m.setType(MembershipType.values()[Integer.parseInt(dto.getMembershipType())]);
		m.setPaidDate(now);
		m.setDateValidFrom(now);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		
		if(m.getType() == MembershipType.MESECNA) {
			cal.add(Calendar.MONTH, 1);
			m.setDateValidTo(cal.getTime());
			m.setNumberOfTerms(30);
		}
		
		if(m.getType() == MembershipType.GODISNJA) {
			cal.add(Calendar.YEAR, 1);
			m.setDateValidTo(cal.getTime());
			m.setNumberOfTerms(365);
		}
		
		m.setPrice(Double.parseDouble(dto.getPrice()));
		m.setUsernameCustomer(dto.getUsernameCustomer());
		m.setMembershipStatus(MembershipStatus.AKTIVNA);
		m.setPlaceId(Integer.parseInt(dto.getPlaceId()));
		
		
		memberships.put(m.getId(), m);
		saveMemberships();
	}
	
	public Collection<Membership> getMyMemberships(String username) {
		ArrayList<Membership> ret = new ArrayList<>();
		for(Membership m: getValues() ) {
			if(m.getUsernameCustomer().equals(username)) {
				ret.add(m);
			}
		}
		return ret;
	}
	
	
	public Collection<Membership> getValues(){
		loadMemberships("");
		return memberships.values();
		}
}
