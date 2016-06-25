package com.studentsnews.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.studentsnews.enums.ArticleType;
import com.studentsnews.models.Article;
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
		if (userContext.getCurrentUser() != null) {
			articleAuthor = userContext.getCurrentUser().getUserName();
		} else {
			articleAuthor = "Unknown";
		}

		ArticleType articleType = article.getArticleType();
		boolean isOk = false;
		st.executeUpdate("insert into article(title, text, author, articleType, isPublished) values(" + "'"
				+ articleTitle + "','" + articleText + "','" + articleAuthor + "','" + articleType + "','" + 0 + "')");
		isOk = true;
		return isOk;
	}

	public List<Article> getAllArticles() throws SQLException {
		List<Article> currentArticles = new LinkedList<Article>();
		Statement statement = td.getStatement();
		ResultSet rs = statement.executeQuery("Select * from article");
		while (rs.next()) {
			Article article = new Article();
			String articleType = rs.getString("articleType");
			article.setArticleType(ArticleType.valueOf(articleType));
			article.setAuthor(rs.getString("author"));
			article.setText(rs.getString("text"));
			article.setTitle(rs.getString("title"));
			article.setId(Integer.parseInt(rs.getString("id")));

			currentArticles.add(article);
		}
		return currentArticles;
	}
	
	public List<Article> getAllUnpublishedArticles() throws SQLException {
		List<Article> currentUnpublishedArticles = new LinkedList<Article>();
		Statement statement = td.getStatement();
		String txtQuery = "Select * from article where article.isPublished = 0";
		ResultSet rs = statement.executeQuery(txtQuery);
		while(rs.next()) {
			Article article = new Article();
			String articleType = rs.getString("articleType");
			article.setArticleType(ArticleType.valueOf(articleType));
			article.setAuthor(rs.getString("author"));
			article.setText(rs.getString("text"));
			article.setTitle(rs.getString("title"));
			article.setId(Integer.parseInt(rs.getString("id")));
			article.setIsPublished(Integer.parseInt(rs.getString("isPublished")));
			
			currentUnpublishedArticles.add(article);

		}

		return currentUnpublishedArticles;
		
	}
	
	public List<Article> getAllPublishArticles() throws SQLException {
		List<Article> currentPublishArticles = new LinkedList<Article>();
		Statement statement = td.getStatement();
		String txtQuery = "Select * from article where article.isPublished = 1";
		ResultSet rs = statement.executeQuery(txtQuery);
		while(rs.next()) {
			Article article = new Article();
			String articleType = rs.getString("articleType");
			article.setArticleType(ArticleType.valueOf(articleType));
			article.setAuthor(rs.getString("author"));
			article.setText(rs.getString("text"));
			article.setTitle(rs.getString("title"));
			article.setId(Integer.parseInt(rs.getString("id")));
			article.setIsPublished(Integer.parseInt(rs.getString("isPublished")));
			
			currentPublishArticles.add(article);
		}
		
		return currentPublishArticles;
	}

	public boolean deleteArticleById(int articleId) throws SQLException {
		String query = "DELETE FROM article WHERE article.id = " + "'" + articleId + "'";
		Statement statement = td.getStatement();
		int executeUpdate = statement.executeUpdate(query);
		if (executeUpdate == 1) {
			return true;
		}
		return false;
		// ResultSet rs = statement.executeQuery(query);
		// rs.close();
	}

}
