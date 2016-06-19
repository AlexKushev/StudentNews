package com.studentsnews.utils;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.studentsnews.dao.ArticleDAO;
import com.studentsnews.dao.UserDAO;
import com.studentsnews.enums.ArticleType;
import com.studentsnews.models.Article;
import com.studentsnews.models.User;

@Stateless
public class DatabaseUtils {

	static String text1 = "test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1test1tst1test1test1test1";
	static String text2 = "test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2test2";
	
	private static User[] USERS = { new User("Alex", "Kushev", "alle3x", "123"),
			new User("Sevdalin", "zhelyzkov", "sevito", "1235") };

	private static Article[] ARTICLES = { new Article("Title1", text1, new Date(), ArticleType.TechNews, "Pesho"),
			new Article("Title2", text2, new Date(), ArticleType.UniversityNews, "Gosho") };

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserDAO userDAO;

	@EJB
	private ArticleDAO articleDAO;

	public void addTestDataToDB() throws SQLException {
		deleteData();
		addTestUsers();
		addTestArticles();
	}

	private void deleteData() {
		//em.createQuery("DELETE FROM User").executeUpdate();
		//em.createQuery("DELETE FROM Article").executeUpdate();
	}

	private void addTestUsers() {
		for (User user : USERS) {
			try {
				userDAO.addUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addTestArticles() throws SQLException {
		for (Article article : ARTICLES) {
			articleDAO.addArticle(article);
		}
	}

}
