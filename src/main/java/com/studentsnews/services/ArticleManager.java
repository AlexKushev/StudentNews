package com.studentsnews.services;

import java.util.Collection;

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
	    public Collection<Article> getAllArticles() {
	    	return articleDAO.getAllArticles();
	    }
	    
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void addArticle(Article article) {
	    	articleDAO.addArticle(article);
	    }
	    
}
