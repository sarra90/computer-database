package com.excilys.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ManagerConnection {

    private static Connection connection;

    private static HikariDataSource datasource;

    public static DataSource getDataSource() {
        String configFile = "/db.properties";

        HikariConfig cfg = new HikariConfig(configFile);

        if (datasource == null) {
            cfg.setAutoCommit(false);
            cfg.setMaximumPoolSize(40);
            datasource = new HikariDataSource(cfg);
        }
        return datasource;
    }

    public static Connection getInstance() {

        if (connection == null) {

            try {
                DataSource dataSource = ManagerConnection.getDataSource();
                connection = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}
