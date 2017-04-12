package com.excilys.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.builder.Computerbuilder;
import com.excilys.dao.DAO;
import com.excilys.model.Computer;


public class ComputerDaoImpl extends DAO<Computer> {
	
	private static final Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);
	@Override
	public List<Computer> findAll() {

		List<Computer> listOfComputers = new ArrayList<Computer>();
		Computer computer = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = "SELECT * FROM computer WHERE id > 20 ORDER BY id LIMIT 10 ;";
		try {
			statement = connect.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {

				computer = new Computer(new Computerbuilder(rs.getString("name"))
						.setIntroduced(rs.getDate("introduced")).setDisconstinued(rs.getDate("discontinued"))
						.setManufacturer(new CompanyDaoImpl().findById(rs.getLong("company_id"))));
				listOfComputers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("findAll method : ",query);
		logger.debug("ResultSet",rs );
		return listOfComputers;
	}

	@Override
	public Computer findById(Long id) {
		Computer computer = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM computer WHERE id = " + id + ";";
		try {
			statement = connect.prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next()) {

				computer = new Computer(new Computerbuilder(rs.getString("name"))
						.setIntroduced(rs.getDate("introduced")).setDisconstinued(rs.getDate("discontinued"))
						.setManufacturer(new CompanyDaoImpl().findById(rs.getLong("company_id"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

	@Override
	public Computer create(Computer obj) {

		PreparedStatement statement = null;
		if (findById(obj.getId()) == null) {
			String query = "INSERT INTO computer (name, introduced, discontinued, company_id)"
					+ "VALUES(?, ?, ?, ?)";
			try {
				statement = connect.prepareStatement(query);
				statement.setString(1, obj.getName());
				statement.setDate(2, new Date(obj.getIntroduced().getTime()));
				statement.setDate(3, new Date(obj.getDisconstinued().getTime()));
				statement.setLong(4, obj.getManufacturer().getId());
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public Computer update(Computer obj) {

		PreparedStatement statement = null;
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, WHERE id = ? ";
		if (findById(obj.getId()) != null) {
			try {
				statement = connect.prepareStatement(query);

				statement.setString(1, obj.getName());
				statement.setDate(2, new Date(obj.getIntroduced().getTime()));
				statement.setDate(3, new Date(obj.getDisconstinued().getTime()));
				statement.setLong(4, obj.getId());
				
				statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub

	}

}