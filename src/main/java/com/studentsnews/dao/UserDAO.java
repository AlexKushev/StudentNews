package com.studentsnews.dao;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.studentsnews.models.User;
import com.studentsnews.services.TestDataInserter;

@Singleton
public class UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private TestDataInserter td;

	public boolean addUser(User user) throws SQLException {
		String userName = user.getUserName();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		boolean isOk = false;
		Statement st = td.getStatement();
		/*try {
		} catch (NoResultException ex) {*/
			//user.setPassword(getHashedPassword(user.getPassword()));
			String password = user.getPassword();
			st.executeUpdate("insert into user(firstName, lastName, userName, password) values(" + "'" + firstName + "','" + lastName + "','" + userName + "','" + password + "')");
			isOk = true;
		/*}*/

		return isOk;
	}

	public List<User> getAllUsers() {
		String txtQuery = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		return query.getResultList();
	}
	
	public User findUserByUserName(String userName) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName = :userName";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		return queryUser(query);
	}

	public boolean validateUserCredentials(String email, String password) {
		String txtQuery = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", email);
		query.setParameter("password", getHashedPassword(password));
		return queryUser(query) != null;
	}

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}