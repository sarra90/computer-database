package com.excilys.computerdatabase.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD:src/main/java/com/excilys/dao/impl/CompanyDaoImpl.java

import com.excilys.dao.CompanyDao;
import com.excilys.dao.ManagerConnection;
import com.excilys.model.Company;

public class CompanyDaoImpl implements CompanyDao {

=======

import com.excilys.computerdatabase.daos.CompanyDao;
import com.excilys.computerdatabase.daos.ManagerConnection;
import com.excilys.computerdatabase.models.Company;

public class CompanyDaoImpl implements CompanyDao {

>>>>>>> origin/develop:src/main/java/com/excilys/computerdatabase/daos/impl/CompanyDaoImpl.java
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);
	public static final String QUERY_SELECT_ALL_COMPANY = "SELECT * FROM company ;";
	public static final String QUERY_SELECT_COMPANY_WHERE_ID = "SELECT * FROM company WHERE id = ";
	public static final String QUERY_INSERT_COMPANY = "INSERT INTO company (name)";
	
	public Connection connect = ManagerConnection.getInstance();
	
	@Override
	public List<Company> findAll() {

		List<Company> listOfCompanys = new ArrayList<Company>();
		Company company = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		
		try {
			statement = connect.prepareStatement(QUERY_SELECT_ALL_COMPANY);
<<<<<<< HEAD:src/main/java/com/excilys/dao/impl/CompanyDaoImpl.java
			
			rs = statement.executeQuery();
			
			int columnCount=rs.getMetaData().getColumnCount();

			while(rs.next()) {
			    for (int i=0; i < columnCount; i++) {
			    	company = new Company(rs.getLong("id"), rs.getString("name"));
			    	listOfCompanys.add(company);
			    }
=======
			rs = statement.executeQuery();
			
			if (rs.next()) {
				company = new Company(rs.getLong("id"), rs.getString("name"));
				listOfCompanys.add(company);
>>>>>>> origin/develop:src/main/java/com/excilys/computerdatabase/daos/impl/CompanyDaoImpl.java
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("list company : "+listOfCompanys);
		return listOfCompanys;
	}

	@Override
	public Company findById(Long id) {
		Company company = null;
		ResultSet rs;
		PreparedStatement statement;

		String query = QUERY_SELECT_COMPANY_WHERE_ID + id + ";";

		try {
			statement = connect.prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next()) {
				company = new Company(rs.getLong("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public Company create(Company obj) {
		PreparedStatement statement = null;
		if (findById(obj.getId()) == null) {
			String query = QUERY_INSERT_COMPANY	+ "VALUES(?)";
			try {
				statement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getName());
				statement.execute();
				ResultSet rs = statement.getGeneratedKeys();
				rs.next();
				obj = findById(rs.getLong(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;	
	}
<<<<<<< HEAD:src/main/java/com/excilys/dao/impl/CompanyDaoImpl.java
	@Override
	public List<String> find(){
		List<String> res = new ArrayList<String>();
		res.add("sara");
		res.add("so");
		res.add("cc");
		res.add("ww");
		return res;
		
	}
=======
>>>>>>> origin/develop:src/main/java/com/excilys/computerdatabase/daos/impl/CompanyDaoImpl.java

}