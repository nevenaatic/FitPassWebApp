package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
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

import enums.PlaceType;
import enums.Status;
import model.Address;
import model.Place;

public class PlaceDao {

	private HashMap<String,Place> places;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public HashMap<String, Place> getPlaces() {
		return places;
	}
	public void setPlaces(HashMap<String, Place> place) {
		this.places = place;
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public PlaceDao(HashMap<String, Place> places, String filePath) {
		super();
		this.places = places;
		this.filePath = filePath;
	}
	
	public PlaceDao(String contextPath) {
		this.setPlaces(new HashMap<String, Place>());
		this.setFilePath(contextPath);
		
		loadPlaces(contextPath);
	}
	
	
	//ucitavanje sportskih objekata iz fajla
	@SuppressWarnings("unchecked")
	private void loadPlaces(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/places.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, Place.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.places = ((HashMap<String, Place>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringPlaces = objectMapper.writeValueAsString(places);
				fileWriter.write(stringPlaces);
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
	public void savePlaces() {
		
		File f = new File("WebContent/data/places.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringPlaces = objectMapper.writeValueAsString(this.places);
			fileWriter.write(stringPlaces);
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
	
	
	public Collection<Place> getValues() {
		loadPlaces("");
		return places.values();
	}
	public Place getPlaceByName(String name) {
		this.loadPlaces("");
		for (Place place : getValues()) {
			if(place.getName().equals(name)) {
				return place;
			}
		}	
		return null;
	}
	
	public void populateDatabase() {
		
		Address address = new Address("Bulevar oslobodjenja", 2, "Novi Sad", 0, 0, 21000);
		
		Place p1 = new Place(1, "Objekat1", PlaceType.BAZEN, "Opis", Status.OTVORENO, "24/7", address, "firstPhoto.jpg", 5);
		Place p2 = new Place(2, "Objekat2", PlaceType.SPORTSKI_CENTAR, "Opis", Status.ZATVORENO, "24/7", address, "firstPhoto.jpg", 4);

		getPlaces().put("Objekat1", p1);
		getPlaces().put("Objekat2", p2);
		
		savePlaces();
	}
}
