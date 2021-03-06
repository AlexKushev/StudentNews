package com.studentsnews.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
		String password = user.getPassword();
		boolean isOk = false;
		String txtQuery = "SELECT * FROM User WHERE user.userName=" + "'" + userName + "'"  +  "AND user.password=" + "'" + password + "'";
		Statement st = td.getStatement();
		ResultSet rs = st.executeQuery(txtQuery);
		if(!rs.next()){
			st.executeUpdate("insert into user(firstName, lastName, userName, password, isAdmin) values(" + "'" + firstName + "','" + lastName + "','" + userName + "','" + password + "','" + 0 + "')");
			isOk = true;
		}
		return isOk;
	}

	public List<User> getAllUsers() {
		String txtQuery = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		return query.getResultList();
	}
	
	public User findUserByUserName(String userName, String password) throws SQLException {
		String txtQuery = "SELECT * FROM User WHERE user.userName=" + "'" + userName + "'"  +  "AND user.password=" + "'" + password + "'";
		Statement statement = td.getStatement();
		ResultSet rs = statement.executeQuery(txtQuery);
		User user = new User();
		while(rs.next()) {
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setAdmin(rs.getInt("isAdmin"));
		}
		return user;
	}
	
	public boolean validateUserCredentials(String userName, String password) throws SQLException {
		String txtQuery = "SELECT * FROM User WHERE user.userName=" + "'" + userName + "'"  +  "AND user.password=" + "'" + password + "'";
		Statement statement = td.getStatement();
		ResultSet rs = statement.executeQuery(txtQuery);
		if (!rs.next() ) {
		    return false;
		}
		return true;
	}

}