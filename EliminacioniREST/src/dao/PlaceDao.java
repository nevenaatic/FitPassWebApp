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

import dto.NewPlaceDto;
import dto.NewPlaceWithManagerDto;
import enums.PlaceType;
import enums.Status;
import model.Address;
import model.Place;
import model.User;

public class PlaceDao {

	private HashMap<Integer,Place> places;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public HashMap<Integer, Place> getPlaces() {
		return places;
	}
	public void setPlaces(HashMap<Integer, Place> place) {
		this.places = place;
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public PlaceDao(HashMap<Integer, Place> places, String filePath) {
		super();
		this.places = places;
		this.filePath = filePath;
	}
	
	public PlaceDao(String contextPath) {
		this.setPlaces(new HashMap<Integer, Place>());
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
			MapType type = factory.constructMapType(HashMap.class, Integer.class, Place.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.places = ((HashMap<Integer, Place>) objectMapper.readValue(file, type));
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
	
	
	public Place createPlace(NewPlaceDto newPlace) {
		Place place = new Place(generateIdPlace(), newPlace.name, newPlace.type, newPlace.description,newPlace.status, newPlace.workingTime, new Address( newPlace.street,newPlace.number, newPlace.city, newPlace.longitude, newPlace.latitude, newPlace.zipCode), 
				generateLink(newPlace.logo), 1, newPlace.managerId);
		this.places.put(place.getId(), place);
		savePlaces();
		return  getPlaceById(place.getId());
	}
	
	
	private String generateLink(String link) {
		String ret="";
		//C:\fakepath\20180717_155517.jpg
		String path[] = link.split("fakepath");
		ret = path[1].substring(1);
		
		return ret;
	}
	
	private Integer generateIdPlace() {
		int ret = 0;
        for (Place placeBig : this.getValues())
        {
            for (Place place : this.getValues())
            {
                if (ret == place.getId())
                {
                    ++ret;
                    break;
                }
            }
        }
        return ret;
	}
	
	public Collection<Place> getValues() {
		loadPlaces("");
		return places.values();
	}
	public Place getPlaceById(int id) {
		this.loadPlaces("");
		for (Place place : getValues()) {
			if(place.getId()==id) {
				return place;
			}
		}	
		return null;
	}
	
	public Place createPlaceWithManager(NewPlaceWithManagerDto newPlace, User manager) {
		Place place = new Place(generateIdPlace(), newPlace.name, newPlace.type, newPlace.description,newPlace.status, newPlace.workingTime, new Address( newPlace.street,newPlace.number, newPlace.city, newPlace.longitude, newPlace.latitude, newPlace.zipCode), 
				generateLink(newPlace.logo), 1, manager.getUsername());
		this.places.put(place.getId(), place);
		savePlaces();
		return  getPlaceById(place.getId());
	}
	
public void updateGrade(int id, double grade) {
	Place place= getPlaceById(id); 
	place.setGrade(grade);
	savePlaces();
}
}
