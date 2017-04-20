package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.ManagerConnection;
import com.excilys.model.Company;

public class CompanyDaoImpl implements CompanyDao {

	
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
			rs = statement.executeQuery();
			
			if (rs.next()) {
				company = new Company(rs.getLong("id"), rs.getString("name"));
				listOfCompanys.add(company);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
	@Override
	public List<String> find(){
		List<String> res = new ArrayList<String>();
		res.add("sara");
		res.add("so");
		res.add("cc");
		res.add("ww");
		return res;
		
	}

}