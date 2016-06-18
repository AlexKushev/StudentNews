package com.studentsnews.utils;

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

	private static Article[] ARTICLES = { new Article("Title1", text1, new Date(), ArticleType.TechNews),
			new Article("Title2", text2, new Date(), ArticleType.UniversityNews) };

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserDAO userDAO;

	@EJB
	private ArticleDAO articleDAO;

	public void addTestDataToDB() {
		deleteData();
		addTestUsers();
		addTestArticles();
	}

	private void deleteData() {
		em.createQuery("DELETE FROM User").executeUpdate();
		em.createQuery("DELETE FROM Article").executeUpdate();
	}

	private void addTestUsers() {
		for (User user : USERS) {
			userDAO.addUser(user);
		}
	}

	private void addTestArticles() {
		for (Article article : ARTICLES) {
			articleDAO.addArticle(article);
		}
	}

}
