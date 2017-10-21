package com.insurancemantra.rest.controller;


import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.StringParameterUnmarshallerBinder;
import org.jboss.resteasy.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.insurancemantra.controller.data.AuthRequest;
import com.insurancemantra.controller.data.AuthResponse;
import com.insurancemantra.controller.data.RestLogicalErrorException;
import com.insurancemantra.controller.data.UserSession;
import com.insurancemantra.interceptor.SecurityInterceptor;
import com.insurancemantra.service.AuthenticationService;

@Path("/auth")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationController {	
	Logger log = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@POST
	@PermitAll
	public Response authenticate(AuthRequest authRequest,
								@HeaderParam("Authorization") String authorization,
								@Context HttpServletRequest servletRequest){
		//String authorization =servletContext.getHeader("Authorization"); -- Alternative
		
		String username=null,password=null;
		if (authorization == null) {
			username = authRequest.getUsername();
			password = authRequest.getPassword();
			//Response.ok().entity(SecurityInterceptor.ACCESS_DENIED).build();
		} else {
			String authorizationValue = null;

			try {
				
				 String[] authorizationParts = authorization.split("\\s+");
				if (authorizationParts[0].trim().equals("Basic")) {
					String authorizationInfo = authorizationParts[1];
					authorizationValue = new String(Base64.decode(authorizationInfo));
					username = authorizationValue.split("\\:")[0];
					password = authorizationValue.split("\\:")[1];
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		UserSession userSession = null;
		String token = null;
//		AuthResponse userResponse = null;
		try {
			userSession = authenticationService.validateUser(username, password);
			log.debug("checked  user table for validation.");
			
			if(userSession==null){
				userSession = authenticationService.validateBrandLogin(username, password);
				log.debug("Checking for username in Brand");
			}
			
			if(userSession== null){
				RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid User ! We didn't find you.");
				log.error(authResponseErr.getMessage());
			}
			token = authenticationService.generateAuthenticationToken(userSession,servletRequest.getRemoteAddr());
			ServletContext servletContext =  servletRequest.getServletContext();

			servletContext.setAttribute(token, userSession);
			//userResponse = new AuthResponse(userSession);

		} catch (RestLogicalErrorException e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(e.getResponseError()).build();

		}		
		return Response.ok()
						.entity(userSession)
						.header(SecurityInterceptor.AUTHORIZATION_PROPERTY, token)
						.build();
	}

}
