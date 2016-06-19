package com.studentsnews.services;

import java.sql.SQLException;
import java.util.Collection;
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

import com.studentsnews.dao.ArticleDAO;
import com.studentsnews.models.Article;

@Stateless
@Path("article")
public class ArticleManager {
	
	 private static final Response RESPONSE_OK = Response.ok().build();

	    @Inject
	    private ArticleDAO articleDAO;
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Article> getAllArticles() {
	    	List<Article> currentArticles = null;
	    	try {
				currentArticles = articleDAO.getAllArticles();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return currentArticles;
	    }
	    
	    @Path("add")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response addArticle(Article article) {
	    	try {
				if(articleDAO.addArticle(article)) {
					return RESPONSE_OK;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return Response.status(401).build();
	    }
	    
}
