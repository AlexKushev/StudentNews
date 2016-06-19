package com.studentsnews.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;

import com.studentsnews.utils.DatabaseUtils;

/**
 * Session Bean implementation class TestDataInserter
 */
@Singleton
public class TestDataInserter {

	public Statement getStatement() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("No Driver");
			e1.printStackTrace();
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/www", "root", "1013");
		} catch (SQLException e) {
			System.out.println("Connection failed");
		}

		if (connection != null) {
			System.out.println("az sym bog");
		} else {
			System.out.println("az sym pedal");
		}
		
		return connection.createStatement();
	}
}
