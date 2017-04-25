package com.excilys.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ManagerConnection {

	private static String url;
	private static String user;
	private static String password;
	private static String driverClass;
	
	private static Connection connection;
	
	private static HikariDataSource datasource;
	
	public static DataSource getDataSource()
	{
		 String configFile = "/db.properties";
		 
		 HikariConfig cfg = new HikariConfig(configFile);
		 
			if(datasource == null)
			{
				/*HikariConfig config = new HikariConfig();
				config.setDataSourceClassName(null);
				config.setDriverClassName("com.mysql.jdbc.Driver");
		        config.setJdbcUrl("jdbc:mysql://localhost/computer-database-db");
		        config.setUsername("root");
		        config.setPassword("");

		        config.setMaximumPoolSize(10);
		        config.setAutoCommit(false);
		        config.addDataSourceProperty("cachePrepStmts", "true");
		        config.addDataSourceProperty("prepStmtCacheSize", "250");
		        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");*/
		        
		        
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