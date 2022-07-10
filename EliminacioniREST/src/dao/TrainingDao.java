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

import dto.NewTrainingDto;
import enums.TrainingType;
import model.Comment;
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
			if(t.getIdPlace() == id && !t.getDeleted()) {
				ret.add(t);
			}
		}
		return ret;	
	}
	
	public void createTraining(NewTrainingDto training) {
		Training newTraining = new Training(generateId(), training.name, training.type, training.idPlace, training.duration, training.coachUsername, training.description, generateLink(training.image),false );
		this.trainings.put(newTraining.getIdTraining(), newTraining);
		saveTrainings();
	
	}
	
	public boolean checkName(String name, int id) {
		boolean ret = false;
		for(Training t : getValues()) {
			if(t.getIdPlace() == id &&  t.getName().toLowerCase().equals(name.toLowerCase())) {
				ret = true;
			}
		}
		return ret;
	}
	public boolean checkNameEdit(String name, int idPlace, int idTraining) {
		boolean ret = false;
		for(Training t : getValues()) {
			if(t.getIdPlace() == idPlace && t.getIdTraining() != idTraining && t.getName().toLowerCase().equals(name.toLowerCase())) {
				ret = true;
			}
		}
		return ret;
	}
	private int generateId() {
		int ret = 0;
        for (Training trainingBig : this.getValues())
        {
            for (Training training : this.getValues())
            {
                if (ret == training.getIdTraining())
                {
                    ++ret;
                    break;
                }
            }
        }
        return ret;
	}
	
	private String generateLink(String link) {
		String ret="";
		//C:\fakepath\20180717_155517.jpg
		String path[] = link.split("fakepath");
		ret = path[1].substring(1);
		
		return ret;
	}

	public void editTraining(Training training) {
		Training trainingChange = getById(training.getIdTraining());
		trainingChange.setName(training.getName());
		trainingChange.setDescription(training.getDescription());
		trainingChange.setDuration(training.getDuration());
		trainingChange.setImage(training.getImage());
		saveTrainings();
		
	}

	public Collection<Training> delete(int parseInt) {
		Training trainingChange = getById(parseInt);
		trainingChange.setDeleted(true);
		System.out.println(trainingChange.getDeleted());
		saveTrainings();
		return getTrainingsForPlace(trainingChange.getIdPlace());
	}
}
