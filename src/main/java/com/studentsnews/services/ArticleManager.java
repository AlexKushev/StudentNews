package com.studentsnews.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
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
	    	return articleDAO.getAllArticles();
	    }

}
