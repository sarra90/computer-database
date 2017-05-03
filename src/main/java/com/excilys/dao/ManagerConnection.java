package com.excilys.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ManagerConnection {

	private static Connection connection;
	
	private static HikariDataSource datasource;
	
	public static DataSource getDataSource()
	{
		 String configFile = "/db.properties";
		 
		 HikariConfig cfg = new HikariConfig(configFile);
		 
			if(datasource == null)
			{
		        cfg.setAutoCommit(false);
		        cfg.setMaximumPoolSize(40);
	        datasource = new HikariDataSource(cfg);
			}
			return datasource;
	}
	public static Connection getInstance(){
	
		if(connection==null){
	
			try
				{
						DataSource dataSource = ManagerConnection.getDataSource();
						connection = dataSource.getConnection();
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				}

		return connection;
	}
	
}
/*package com.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.impl.ComputerDaoImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ManagerConnection {

	// private static Connection connection;

	private static HikariDataSource datasource;

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerConnection.class);

	private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		String configFile = "/db.properties";

		HikariConfig cfg = new HikariConfig(configFile);

		if (datasource == null) {
			cfg.setMaximumPoolSize(40);
			datasource = new HikariDataSource(cfg);
		}
		return datasource;
	}

	public static Connection getInstance() {

		try {
			DataSource dataSource = ManagerConnection.getDataSource();
			Connection connection = dataSource.getConnection();
			CONNECTION.set(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CONNECTION.get();
	}

	public static void initTransaction() {
		try {
			CONNECTION.get().setAutoCommit(false);
		} catch (SQLException e) {
			LOGGER.error("Error ManagerConnection : initTransaction : ", e);
		}
	}

	public static void finishTransaction() {
		try {
			CONNECTION.get().setAutoCommit(true);
		} catch (SQLException e) {
			LOGGER.error("Error ManagerConnection : finishTransaction : ", e);
		}
	}

	public static void closeConnection() {
		try {
			CONNECTION.get().close();
			CONNECTION.remove();
		} catch (SQLException e) {
			LOGGER.error("Error ManagerConnection : closeConnection : ", e);
		}
	}

	public static void rollback() {
		try {
			CONNECTION.get().rollback();
		} catch (SQLException e) {
			LOGGER.error("Error ManagerConnection : rollback : ", e);
		}
	}
}*/