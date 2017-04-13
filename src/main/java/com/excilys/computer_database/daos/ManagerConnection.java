package com.excilys.computer_database.daos;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerConnection {

	private static String url;
	private static String user;
	private static String password;
	private static String driverClass;
	
	private static Connection connect;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("configDB");
		url = bundle.getString("sgbd.url");
	    user = bundle.getString("sgbd.user");
		password = bundle.getString("sgbd.password");
		driverClass = bundle.getString("sgbd.driver");
		
	}
	public static Connection getInstance(){
		if(connect==null){

			try {
				Driver driver = (Driver) Class.forName(driverClass).newInstance();
				DriverManager.registerDriver(driver);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}

			try{
				connect = DriverManager.getConnection(url,user,password);
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}
}