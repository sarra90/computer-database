package com.excilys.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.dao.DAO;
import com.excilys.model.Company;

public class CompanyDaoImpl extends DAO<Company> {

	@Override
	public List<Company> findAll() {

		List<Company> listOfCompanys = new ArrayList<Company>();

		Company company = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM company ;";
		try {
			statement = connect.prepareStatement(query);
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

		String query = "SELECT * FROM company WHERE id = " + id + ";";

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
			String query = "INSERT INTO company (name)"
					+ "VALUES(?)";
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
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub

	}

}