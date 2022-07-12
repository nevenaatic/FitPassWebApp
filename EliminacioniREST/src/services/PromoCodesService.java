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
import dao.PromoCodesDao;
import dto.MembershipViewDto;
import dto.PromoCodeDto;
import model.Membership;
import model.PromoCode;
import model.User;

@Path("/promoCodes")
public class PromoCodesService {

	@Context
	ServletContext context;
	@Context
	HttpServletRequest request;
	
	private PromoCodesDao getPromoCodes() {
		PromoCodesDao promoCodes = (PromoCodesDao)context.getAttribute("promoCodes");
		
		if (promoCodes == null) {
			String contextPath = context.getRealPath("");
			promoCodes = new PromoCodesDao(contextPath);
			context.setAttribute("promoCodes", promoCodes);
		}
		return promoCodes;
	}
	
	@POST
	@Path("/addPromoCode")
	@Produces(MediaType.APPLICATION_JSON)
	public void addPromoCode(PromoCodeDto dto) {
		getPromoCodes().addPromoCode(dto);
		
	}
	
	@POST
	@Path("/checkPromoCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PromoCode> checkPromoCode(String code) {
		return getPromoCodes().checkPromoCode(code);
		
	}
}
