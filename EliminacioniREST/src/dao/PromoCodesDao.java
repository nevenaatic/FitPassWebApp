package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import dto.PromoCodeDto;
import model.Place;
import model.PromoCode;

public class PromoCodesDao {

	private HashMap<String, PromoCode> promoCodes;
	private String filePath = "";
	
	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	public HashMap<String, PromoCode> getPromoCodes() {
		return promoCodes;
	}
	public void setPromoCodes(HashMap<String, PromoCode> codes) {
		this.promoCodes = codes;
		
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public PromoCodesDao(HashMap<String, PromoCode> promoCodes, String filePath) {
		super();
		this.promoCodes = promoCodes;
		this.filePath = filePath;
	}
	
	public PromoCodesDao(String contextPath) {
		this.setPromoCodes(new HashMap<String, PromoCode>());
		this.setFilePath(contextPath);
		
		loadPromoCodes(contextPath);
	}
	
	
	//ucitavanje sportskih objekata iz fajla
	@SuppressWarnings("unchecked")
	private void loadPromoCodes(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File("WebContent/data/promoCodes.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, PromoCode.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			this.promoCodes = ((HashMap<String, PromoCode>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringPromoCodes = objectMapper.writeValueAsString(promoCodes);
				fileWriter.write(stringPromoCodes);
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
	public void savePromoCodes() {
		
		File f = new File("WebContent/data/promoCodes.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String stringPromoCodes = objectMapper.writeValueAsString(this.promoCodes);
			fileWriter.write(stringPromoCodes);
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
	public void addPromoCode(PromoCodeDto dto) {
		
		Date validTo = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(validTo);
		c.add(Calendar.DATE, Integer.parseInt(dto.getValidDays()));
		validTo = new Date(c.getTimeInMillis());
		
		PromoCode promoCode = new PromoCode(dto.getId(), validTo, dto.getRemainingUses(), dto.getDiscount());
		promoCodes.put(dto.getId(), promoCode);
		savePromoCodes();
		
	}
	
	public Collection<PromoCode> getValues() {
		loadPromoCodes("");
		return promoCodes.values();
	}
	
	public Collection<PromoCode> checkPromoCode(String code) {

		ArrayList<PromoCode> ret = new ArrayList<PromoCode>();
		for(PromoCode pc: getValues()) {
			if(pc.getId().equals(code) && pc.getRemainingUses() > 0 && pc.getValidUntil().after(new Date()))
			{
				ret.add(pc);
				pc.setRemainingUses(pc.getRemainingUses() - 1);
				savePromoCodes();
			}
		}
//		for (PromoCode pc: promoCodes.values()) {
//			if(pc.getId().equals("123"))
//			{
//				System.out.println(pc.getId());
//				ret.add(new PromoCode("999", new Date(), 90, (float)0.9));
//			}
//		}
//		ret.add(new PromoCode("999", new Date(), 90, (float)0.9));
//		PromoCode test = promoCodes.get("123");
//		ret.add(test);
//		ret.add(promoCodes.get("123"));
		return ret;
		
//		PromoCode pc = new PromoCode("999", new Date(), 90, (float)0.9);
//		promoCodes.put("123", pc);
//		PromoCode test = promoCodes.get("123");
//		ret.add(test);
//		ret.add(promoCodes.get("123"));
//		return ret;
		//return promoCodes.get("123");
	}
	
}
