package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import dto.NewCommentDto;
import model.Comment;
import model.Place;

public class CommentDao {



		private HashMap<Integer,Comment> comments;
		private static final DecimalFormat df = new DecimalFormat("0.00");
		@Context
		ServletContext context;
		@Context
		HttpServletRequest request;
		
		public HashMap<Integer, Comment> getComments() {
			return comments;
		}
		public void setComments(HashMap<Integer, Comment> comments) {
			this.comments = comments;
			
		}
		
		
		public CommentDao(HashMap<Integer, Comment> comments) {
			super();
			this.comments = comments;
		}
		
		public CommentDao() {
			this.setComments(new HashMap<Integer, Comment>());
			loadComments();
		}
		
		
		
		@SuppressWarnings("unchecked")
		private void loadComments() {
			FileWriter fileWriter = null;
			BufferedReader in = null;
			File file = null;
			try {
				file = new File("WebContent/data/comments.txt");
				in = new BufferedReader(new FileReader(file));

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setVisibilityChecker(
						VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
				TypeFactory factory = TypeFactory.defaultInstance();
				MapType type = factory.constructMapType(HashMap.class, Integer.class, Comment.class);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				this.comments = ((HashMap<Integer, Comment>) objectMapper.readValue(file, type));
			} catch (FileNotFoundException fnfe) {
				try {
					file.createNewFile();
					fileWriter = new FileWriter(file);
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
					objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
					String stringPlaces = objectMapper.writeValueAsString(comments);
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
		public void saveComments() {
			
			File f = new File("WebContent/data/comments.txt");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(f);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringPlaces = objectMapper.writeValueAsString(this.comments);
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
		
	
		public Collection<Comment> getValues(){
			loadComments();
			return comments.values();
		}

	
		private int generateId() {
			int ret = 0;
	        for (Comment commentBig : this.getValues())
	        {
	            for (Comment comment : this.getValues())
	            {
	                if (ret == comment.getIdComment())
	                {
	                    ++ret;
	                    break;
	                }
	            }
	        }
	        return ret;
		}
		
		
		public Collection<Comment> updateStatus(int id, String type){
			Comment comment = getById(id);
			if(type.equals("reject")) {
				comment.setDeleted(true);
			} else if(type.equals("accept")) {
				comment.setApproved(true);
			}
			saveComments();
			return  getValues();
			
		}
		
		public void createComment(Comment comment) {
			Comment newComment = new Comment(generateId(),comment.getUsernameCustomer(), comment.getIdPlace(), comment.getComment(),
					comment.getGrade(), false, false );
			
			this.getComments().put(newComment.getIdComment(), newComment);
			System.out.println(newComment.getIdComment() +  newComment.getUsernameCustomer()+newComment.getApproved()+ newComment.getComment());
			saveComments();
		}
		
		public double updateGrade(double grade, int placeId) {
			Collection<Comment> comments = getValues();
			double gradePlace = 0;
			ArrayList<Comment> placeComments = new ArrayList<Comment>();
			for(Comment c: comments) {
				if(c.getIdPlace() == placeId && c.getApproved() && !c.getDeleted()) {
					placeComments.add(c);
				}
			}
			if(!placeComments.isEmpty()) {
				double  sum= 0;
				double size = placeComments.size();
				for(Comment c : placeComments) {
					sum +=c.getGrade();
				}
				gradePlace = Double.parseDouble(df.format(sum/size));
				
			} else {
				gradePlace = grade;
			}
			return gradePlace;
		}
		
		
		public Comment getById(int id) {
			this.loadComments();
			for(Comment c: getValues()) {
				if(c.getIdComment() == id) {
					return c;
				}
			}
			return null;
		}
}
