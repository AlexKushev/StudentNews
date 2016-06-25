package com.studentsnews.services;

import java.net.HttpURLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
	
	@Inject
	private TestDataInserter td;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() throws SQLException {
		List<User> currentUsers = new LinkedList<User>();
		Statement statement = td.getStatement();
		ResultSet rs = statement.executeQuery("Select * from user");
		 while(rs.next()){
			 User user = new User();
			 user.setFirstName(rs.getString("firstName"));
			 user.setLastName(rs.getString("lastName"));
			 user.setUserName(rs.getString("userName"));
			 user.setPassword(rs.getString("password"));
			 user.setId(rs.getInt("id"));
			 user.setAdmin(rs.getInt("isAdmin"));
			 
			 currentUsers.add(user);
	      }
		 return currentUsers;
	}

	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(User newUser) {
		if (newUser.getFirstName().length() > 0 && newUser.getFirstName().length() < 20
				&& newUser.getLastName().length() > 0 && newUser.getLastName().length() < 20) {
			if (newUser.getPassword().length() >= 3) {
				try {
					if (userDAO.addUser(newUser)) {
						return RESPONSE_OK;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return Response.status(401).build();
	}

	@Path("login")
	@POST
	@Consumes("application/json")
	public Response loginUser(User user) throws SQLException {
		boolean isUserValid = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());
		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		User user1 = userDAO.findUserByUserName(user.getUserName(), user.getPassword());
		context.setCurrentUser(user1);
		return RESPONSE_OK;
	}
	
	@Path("current")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		if (context.getCurrentUser() == null) {
			System.out.println(context.getCurrentUser().getFirstName());
			return null;
		}
		return context.getCurrentUser();
	}
	
	@Path("logout")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public void logoutUser() {
		context.setCurrentUser(null);
	}

}
