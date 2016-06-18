package com.studentsnews.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.studentsnews.models.Article;

@Singleton
public class ArticleDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void addArticle(Article article) {
		em.persist(article);
	}
	
	 public Collection<Article> getAllArticles() {
	    	String txtQuery = "SELECT a FROM Article a";
	    	TypedQuery<Article> query = em.createQuery(txtQuery, Article.class);
	    	return query.getResultList();
	 }
	
}
