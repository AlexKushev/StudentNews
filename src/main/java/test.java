import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public class test {

	public static void main(String[] args) throws SQLException {
		/*
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver");
			e.printStackTrace();
		}
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/www", "root", "1013");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			return;
		}
		
		if(connection != null ) {
			System.out.println("az sym bog");
		} else {
			System.out.println("az sym pedal");
		}
		
		Statement statement =  connection.createStatement();
		statement.executeUpdate("insert into user(firstName, lastName, userName, password) values('test', 'test', 'test', 'test')");*/
	
		System.out.println("'");
		
	}

}
