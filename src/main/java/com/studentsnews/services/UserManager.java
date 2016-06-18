package com.studentsnews.services;

import java.net.HttpURLConnection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.studentsnews.dao.UserDAO;
import com.studentsnews.models.User;

@Stateless
@Path("user")
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserContext context;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User newUser) {
		if (newUser.getFirstName().length() > 0 && newUser.getFirstName().length() < 20
				&& newUser.getLastName().length() > 0 && newUser.getLastName().length() < 20) {
			if (newUser.getPassword().length() >= 8) {
				if (userDAO.addUser(newUser)) {
					return RESPONSE_OK;
				}
			}
		}
		return Response.status(401).build();
	}

	@Path("login")
	@POST
	@Consumes("application/json")
	public Response loginUser(User user) {
		boolean isUserValid = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}

		User curUser = userDAO.findUserByUserName(user.getUserName());
		System.out.println(curUser);
		context.setCurrentUser(curUser);
		return RESPONSE_OK;
	}
	
	@Path("current")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser() {
		if (context.getCurrentUser() == null) {
			System.out.println(context.getCurrentUser().getFirstName());
			return "null";
		}
		return context.getCurrentUser().toString();
	}
	
	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logoutUser() {
		context.setCurrentUser(null);
	}

}
