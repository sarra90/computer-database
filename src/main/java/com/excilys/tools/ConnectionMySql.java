package com.excilys.tools;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {




	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	private static Connection connect;
	
	public static Connection getInstance(){
		if(connect==null){

			try {
				Driver driver = (Driver) Class.forName(DRIVER_CLASS).newInstance();
				DriverManager.registerDriver(driver);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			try{
				connect = DriverManager.getConnection(URL,USER,PASSWORD);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}
}