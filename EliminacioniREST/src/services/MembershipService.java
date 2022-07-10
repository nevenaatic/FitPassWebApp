package services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import dao.MembershipDao;

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

}
