package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import dto.ChangeUserProfileDto;
import dto.NewPlaceWithManagerDto;
import dto.NewUserDto;
import dto.UserRegistrationDto;
import enums.CustomerType;
import enums.Gender;
import enums.Role;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import model.Address;
import model.Customer;
import model.User;

public class UserDao {

	private HashMap<String,User> users;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public HashMap<String, User> getUsers() {
		return users;
	}
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public UserDao(HashMap<String, User> users, String filePath) {
		super();
		this.users = users;
		this.filePath = filePath;
	}
	
	public UserDao(String contextPath) {
		this.setUsers(new HashMap<String, User>());
		this.setFilePath(contextPath);
		
		loadUsers(contextPath);
	}
	
	
	//ucitavanje korisnika iz fajla
	@SuppressWarnings("unchecked")
	private void loadUsers(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/users.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, User.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.users = ((HashMap<String, User>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringUsers = objectMapper.writeValueAsString(users);
				fileWriter.write(stringUsers);
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
	
	//ucitavanje korisnika u fajl
	public void saveUsers() {
		
		File f = new File("WebContent/data/users.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringUsers = objectMapper.writeValueAsString(this.users);
			fileWriter.write(stringUsers);
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
	
	public void registerUser(UserRegistrationDto user) {
		
		getUsers().put(user.username, new User(user.username, user.password, user.name, user.surname, user.gender, user.birthday, Role.KUPAC, false, false,
				new Address(user.street, user.number, user.city, 0, 0, user.zipCode), new ArrayList<Integer>(), new ArrayList<Integer>(), 
				new ArrayList<Integer>(), 0, new Customer(CustomerType.BRONZANI,0,0), -1));
	
		saveUsers();
	}
	
	public User saveNewManager(NewPlaceWithManagerDto user) {
		getUsers().put(user.managerUsername, new User(user.managerUsername, user.managerUsername, user.managerName, user.managerSurname, user.managerGender, user.managerBirthday, Role.MENADZER, false, false,
				new Address(user.managerStreet, user.managerNumber, user.managerCity, 0, 0, user.managerZipCode), new ArrayList<Integer>(), new ArrayList<Integer>(), 
				new ArrayList<Integer>(), 0, new Customer(CustomerType.BRONZANI,0,0), -1));
	
		saveUsers();
		return getUserByUsername(user.managerUsername);
	}
	
	
	
	public Collection<User> getValues() {
		loadUsers("");
		return users.values();
	}
	public User getUserByUsername(String username) {
		this.loadUsers("");
		for (User user : getValues()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}	
		return null;
	}
	
	public Collection<User> getAvailableManagers() {
		ArrayList<User> ret = new ArrayList<User>();
		for (User user : getValues()) {
			if(user.getRole().equals(Role.MENADZER) && !user.getDeleted() && user.getPlace() == -1) {
				ret.add(user);
			}
		}	
		return ret;
	}
	

	public void changeUserProfile(ChangeUserProfileDto user) {
			User userChange = getUserByUsername(user.username);
			userChange.setName(user.name);
			userChange.setSurname(user.surname);
			userChange.setAddress(new Address(user.street,user.number, user.city, 0, 0, user.zipCode));
			userChange.setGender(user.gender);
			userChange.setBirthday(user.birthday);
			saveUsers();
		}
	
	
	//brisanje korisnika
	public void deleteUserById(String username) {
		User user = getUserByUsername(username);
		if(user != null) {
			user.setDeleted(true); //logicko brisanje
			saveUsers();
		}	
	}
	public void updateManagerPlace(User user, int id) {
		User userChange = getUserByUsername(user.getUsername());
		userChange.setPlace(id);
		saveUsers();
	}
	public void saveNewUser(NewUserDto dto) {
		User user = new User(dto.username, dto.username, dto.name, dto.surname, dto.gender, dto.birthday, dto.role, false, false, new Address(dto.street,dto.number, dto.city, 0,0, dto.zipCode), 
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),  0,new Customer(CustomerType.BRONZANI,0,0), -1 );
		getUsers().put(user.getUsername(), user);
		saveUsers();
	}
		
	
}
