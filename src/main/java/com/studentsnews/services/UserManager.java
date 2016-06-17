package com.studentsnews.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
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

}
