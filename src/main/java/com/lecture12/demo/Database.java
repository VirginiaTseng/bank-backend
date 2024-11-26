package com.lecture12.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet result= null;
	
	private static String url ="jdbc:mysql://localhost:3306/student";
	private static String username = "root";
	private static String password = "put passwordHere";
	
	
	private Database () {
		
	}
	
	 public static Connection getConnection() {
		
			if (connection == null) {
				
				try {
					connection = DriverManager.getConnection(url, username, password);
					System.out.println("success");
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return connection;
		}
	 
	public static void closeConnection() {
		if (connection != null)
			try {
				connection.close();
				connection=null;
			}catch (SQLException e) {
				e.printStackTrace();
			}
				
			
	}
	
	 public static void closePreparedStatement() {
	    	if (statement != null)
	    		
			try {
			 {
					statement.close();
				}
			} catch (SQLException exception) {
				// exception.printStackTrace();
			}

		}
	 
	 public static void closeResultSet() {

			try {
				if (result != null) {
					result.close();
				}
			} catch (SQLException exception) {
				// exception.printStackTrace();
			}
	
	 }
	
}
