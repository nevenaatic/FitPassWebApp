package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.MembershipDao;
import dao.PlaceDao;
import dao.UserDao;
import dto.MembershipDto;
import dto.MembershipViewDto;
import dto.NewPlaceDto;
import model.Membership;
import model.Place;
import model.User;

@Path("/membership")
public class MembershipService {

	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	private MembershipDao getMemberships() {
		MembershipDao memberships = (MembershipDao)context.getAttribute("memberships");
		
		if (memberships == null) {
			String contextPath = context.getRealPath("");
			memberships = new MembershipDao(contextPath);
			context.setAttribute("memberships", memberships);
		}
		return memberships;
	}

	private PlaceDao getPlaces() {
		PlaceDao places = (PlaceDao)context.getAttribute("places");
		
		if (places == null) {
			String contextPath = context.getRealPath("");
			places = new PlaceDao(contextPath);
			context.setAttribute("places", places);
		}
		return places;
	}
	
	@POST
	@Path("/buyMembership")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void buyMembership(MembershipDto membershipDto) {
		MembershipDao membershipDao = getMemberships();
		membershipDao.buyMembership(membershipDto);
	}
	
	
	@GET
	@Path("/myMemberships")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MembershipViewDto> myMemberships( ) {
		MembershipDao membershipDao = getMemberships();
		PlaceDao placeDao = getPlaces();
		User userSession = (User)request.getSession().getAttribute("loginUser");
		Collection<MembershipViewDto> ret= new ArrayList<>();
		
		if(membershipDao.getMyMemberships(userSession.getUsername()).size()!=0) {
			for(Membership m:  membershipDao.getMyMemberships(userSession.getUsername())) {
			ret.add(new MembershipViewDto(m.getId(),m.getType(),m.getPaidDate(),m.getDateValidFrom(), m.getDateValidTo(), m.getPrice(), m.getMembershipStatus(),
					placeDao.getPlaceById(m.getPlaceId()).getName(), m.getPlaceId()));
		}
		} 
		return ret;
		
	}
}
