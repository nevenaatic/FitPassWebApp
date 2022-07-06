package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import model.Training;
import model.User;

public class TrainingDao {

	private HashMap<Integer, Training> trainings;

	public HashMap<Integer, Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(HashMap<Integer, Training> trainings) {
		this.trainings = trainings;
	}

	public TrainingDao(HashMap<Integer, Training> trainings) {
		super();
		this.trainings = trainings;
	}
	
	
	public TrainingDao() {
		this.setTrainings(new HashMap<Integer, Training>());
		loadTrainings();
	}

	//ucitavanje iz fajla
	@SuppressWarnings("unchecked")
	private void loadTrainings() {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/trainings.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, Integer.class, Training.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.trainings = ((HashMap<Integer, Training>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringTrainings = objectMapper.writeValueAsString(trainings);
				fileWriter.write(stringTrainings);
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
	
	public void saveTrainings() {
		
		File f = new File("WebContent/data/trainings.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringTrainings = objectMapper.writeValueAsString(this.trainings);
			fileWriter.write(stringTrainings);
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
	
	public Collection<Training> getValues(){
		loadTrainings();
		return trainings.values();
	}
	
	public Training getById(int id) {
		for(Training t: getValues()) {
			if(t.getIdTraining() == id) {
				return t;
			}
		}
		return null;
		
	}
	
	public Collection<Training> getTrainingsForPlace(int id){
		ArrayList<Training> ret= new ArrayList<Training>();
		for(Training t : getValues()) {
			if(t.getIdPlace() == id) {
				ret.add(t);
			}
		}
		return ret;	
	}
	
	
	
}
