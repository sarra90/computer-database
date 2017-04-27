package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ManagerConnection;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.Builder;

public class ComputerDaoImpl implements ComputerDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

	public static final String QUERY_SELECT_ALL_COMPUTER = "SELECT * FROM computer;";
	public static final String QUERY_SELECT_ALL_COMPUTER_PER_PAGE = "SELECT c.id as id_computer , c.name as name_computer , c.introduced as introduced"
																	+ " , c.discontinued as discontinued , co.id as id_company , co.name as name_company "
																	+ "FROM computer c LEFT JOIN company co on c.company_id = co.id limit ";
	public static final String QUERY_SELECT_COUNT = "SELECT COUNT(*) FROM computer ;";
	public static final String QUERY_SELECT_ALL_BY_NAME = "SELECT * FROM computer WHERE UPPER(name) like UPPER(?) ";
	public static final String QUERY_SELECT_COMPUTER_WHERE_ID = "SELECT * FROM computer WHERE id = ";
	public static final String QUERY_INSERT_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id)";
	public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ? WHERE id = ? ";
	public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	public static final String QUERY_SELECT_ALL_BY_C = "DELETE FROM computer WHERE id = ?";
	
	public Connection connect = ManagerConnection.getInstance();
	private CompanyDao companyDao = new CompanyDaoImpl();

	@Override
	public List<Computer> findAll() {

		List<Computer> listOfComputers = new ArrayList<Computer>();
		Computer computer = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connect.prepareStatement(QUERY_SELECT_ALL_COMPUTER);
			rs = statement.executeQuery();

			while (rs.next()) {
				Builder builder = new Computer.Builder(rs.getString("name"));
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");

				computer = builder.id(rs.getLong("id"))
						.introduced((introduced != null) ? introduced.toLocalDate() : null)
						.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
						.manufacturer(companyDao.findById(rs.getLong("company_id"))).build();
				listOfComputers.add(computer);
			}

		} catch (SQLException e) {
			LOGGER.info("Error : findAll method " + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		LOGGER.info("findAll method : ", listOfComputers.size());
		LOGGER.debug("ResultSet", rs);
		return listOfComputers;
	}

	@Override
	public List<Computer> findAllPerPage(int offset, int numberOfRecords) {
		String query = QUERY_SELECT_ALL_COMPUTER_PER_PAGE + offset + ", " + numberOfRecords;
		List<Computer> listOfComputersPerPage = new ArrayList<Computer>();
		Computer computer = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connect.prepareStatement(query);
			rs = statement.executeQuery();

			while (rs.next()) {
				Builder builder = new Computer.Builder(rs.getString("name_computer"));
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");
				Company company = new Company.Builder()
						.id(rs.getLong("id_company"))
						.name(rs.getString("name_company"))
						.build();
						
						computer = builder.id(rs.getLong("id_computer"))
						.introduced((introduced != null) ? introduced.toLocalDate() : null)
						.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
						.manufacturer(company)
						.build();
						
				listOfComputersPerPage.add(computer);
			}
			rs.close();
		} catch (SQLException e) {
			LOGGER.info("Error : findAll method " + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		LOGGER.info("findAllPerPage method : ", listOfComputersPerPage.size());
		return listOfComputersPerPage;
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
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");
				computer = new Computer.Builder(rs.getString("name"))
						.introduced((introduced != null) ? introduced.toLocalDate() : null)
						.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
						.manufacturer(new CompanyDaoImpl().findById(rs.getLong("company_id"))).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement not closed" + e.getMessage());
			}
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
				LOGGER.info("Error : create computer method " + e.getMessage());
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.info("Error : statement close" + e.getMessage());
				}
			}
		}
		LOGGER.info("create computer method " + obj.toString());
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

				if(statement.execute()){
					LOGGER.info("update Method " + obj.toString());
				}
				
			} catch (SQLException e) {
				LOGGER.info("Error : Connexion failed" + e.getMessage());
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.info("Error : statement close" + e.getMessage());
				}
			}
		}
		return obj;
	}

	@Override
	public void delete(Computer obj) {

		PreparedStatement statement = null;
		
		if(findById(obj.getId())!=null){
			try {
				statement=connect.prepareStatement(QUERY_DELETE_COMPUTER);
				
				statement.setLong(1, obj.getId());
				
				if(statement.execute()){
					LOGGER.info("delete Method " + obj.toString());
				}
				
			} catch (SQLException e) {
				LOGGER.info("Error : Connexion failed" + e.getMessage());
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.info("Error : statement close" + e.getMessage());
				}
			}
			
		}
	}

	@Override
	public List<Computer> findByName(String name) {
		
		List<Computer> listComputerFindByName = new ArrayList<Computer>();
		Computer computer ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connect.prepareStatement(QUERY_SELECT_ALL_BY_NAME);
			statement.setString(1, name + "%");
			rs= statement.executeQuery();
			while (rs.next()) {
				Builder builder = new Computer.Builder(rs.getString("name"));
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");

				computer = builder.introduced((introduced != null) ? introduced.toLocalDate() : null)
						.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
						.manufacturer(companyDao.findById(rs.getLong("company_id"))).build();
				listComputerFindByName.add(computer);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		return listComputerFindByName;
	}

	@Override
	public long countComputer() {
		long result =0;
		PreparedStatement statement = null ;
		ResultSet rs;
		try {
			statement=connect.prepareStatement(QUERY_SELECT_COUNT);
			rs = statement.executeQuery();
			while(rs.next()){
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public void delete(Long id) {

		Optional<Computer> computer = findById(id);
		
		if(computer.isPresent()){
			delete(computer.get());
		}
	}

	@Override
	public List<Computer> findByCompany(Company company) {

		return null;
	}
}