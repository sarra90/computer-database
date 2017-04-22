package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.ManagerConnection;
import com.excilys.model.Computer;


public class ComputerDaoImpl implements ComputerDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);
	
	public static final String QUERY_SELECT_ALL_COMPUTER = "SELECT * FROM computer;";
	public static final String QUERY_SELECT_COMPUTER_WHERE_ID = "SELECT * FROM computer WHERE id = ";
	public static final String QUERY_INSERT_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id)";
	public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, WHERE id = ? ";
	public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer";

	public Connection connect = ManagerConnection.getInstance();
	
	@Override
	public List<Computer> findAll() {

		List<Computer> listOfComputers = new ArrayList<Computer>();
		Computer computer = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connect.prepareStatement(QUERY_SELECT_ALL_COMPUTER);
			rs = statement.executeQuery();
			
			int columnCount=rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i=0; i < columnCount; i++) {
				computer = new Computer.Builder(rs.getString("name"))
							.introduced(rs.getDate("introduced").toLocalDate())
							.disconstinued(rs.getDate("discontinued").toLocalDate())
							.manufacturer(new CompanyDaoImpl().findById(rs.getLong("company_id")))
							.build();
				listOfComputers.add(computer);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("findAll method : ",listOfComputers);
		LOGGER.debug("ResultSet",rs );
		return listOfComputers;
	}

	@Override
	public Optional<Computer> findById(Long id) {
		Computer computer = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		String query = QUERY_SELECT_COMPUTER_WHERE_ID + id + ";";
		try {
			statement = connect.prepareStatement(query);
			rs = statement.executeQuery();
			if (rs.next()) {
				computer = new Computer.Builder(rs.getString("name"))
						.introduced(rs.getDate("introduced").toLocalDate())
						.disconstinued(rs.getDate("discontinued").toLocalDate())
						.manufacturer(new CompanyDaoImpl().findById(rs.getLong("company_id")))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(computer);
	}

	@Override
	public Computer create(Computer obj) {

		PreparedStatement statement = null;
		if (!findById(obj.getId()).isPresent()) {
			String query = QUERY_INSERT_COMPUTER + "VALUES(?, ?, ?, ?)";
			try {
				statement = connect.prepareStatement(query);
				statement.setString(1, obj.getName());
				statement.setDate(2, Date.valueOf(obj.getIntroduced()));
				statement.setDate(3, Date.valueOf(obj.getDisconstinued()));
				statement.setLong(4, obj.getManufacturer().getId());
				statement.execute();
			} catch (SQLException e) {
				LOGGER.info("Error : create computer method "+e.getMessage());
			}
		}
		LOGGER.info("create computer method "+obj.toString());
		return obj;
	}

	@Override
	public Computer update(Computer obj) {

		PreparedStatement statement = null;
		if (findById(obj.getId()) != null) {
			try {
				statement = connect.prepareStatement(QUERY_UPDATE_COMPUTER);
				statement.setString(1, obj.getName());
				statement.setDate(2, Date.valueOf(obj.getIntroduced()));
				statement.setDate(3, Date.valueOf(obj.getDisconstinued()));
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