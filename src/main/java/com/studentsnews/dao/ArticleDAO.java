package com.studentsnews.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.studentsnews.enums.ArticleType;
import com.studentsnews.models.Article;
import com.studentsnews.models.User;
import com.studentsnews.services.TestDataInserter;
import com.studentsnews.services.UserContext;

@Singleton
public class ArticleDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserContext userContext;
	
	@Inject
	private TestDataInserter td;
	
	public boolean addArticle(Article article) throws SQLException {
		String articleTitle = article.getTitle();
		String articleText = article.getText();
		Statement st = td.getStatement();
		String articleAuthor = "";
		if(userContext.getCurrentUser() != null){
			articleAuthor = userContext.getCurrentUser().getUserName();
		} else {
			articleAuthor = "Unknown";
		}
		
		ArticleType articleType = article.getArticleType();
		boolean isOk = false;
		/*try {
			em.createNamedQuery("article.getByTitle", Article.class).setParameter("title", articleTitle).getSingleResult();
		} catch (NoResultException ex) {*/
		st.executeUpdate("insert into article(title, text, author, articleType) values(" + "'" + articleTitle + "','" + articleText +  "','" + articleAuthor + "','" + articleType + "')");
			isOk = true;
		/*}*/
		return isOk;
	}
	

	 public List<Article> getAllArticles() throws SQLException {
		 List<Article> currentArticles = new LinkedList<Article>();
			Statement statement = td.getStatement();
			ResultSet rs = statement.executeQuery("Select * from article");
			 while(rs.next()){
				 Article article = new Article();
				 String articleType = rs.getString("articleType");
				 article.setArticleType(ArticleType.valueOf(articleType));
				 article.setAuthor(rs.getString("author"));
				 article.setText(rs.getString("text"));
				 article.setTitle(rs.getString("title"));
		        
				 currentArticles.add(article);
		      }
			 return currentArticles;
	 }
	
}
