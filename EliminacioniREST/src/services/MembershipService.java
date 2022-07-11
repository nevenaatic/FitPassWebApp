package services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.MembershipDao;
import dao.PlaceDao;
import dao.UserDao;
import dto.MembershipDto;
import dto.NewPlaceDto;
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

	@POST
	@Path("/buyMembership")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public void buyMembership(MembershipDto membershipDto) {
		MembershipDao membershipDao = getMemberships();
		membershipDao.buyMembership(membershipDto);
	}
}
