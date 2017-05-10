package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

	public static final String QUERY_SELECT_ALL_COMPUTER = "SELECT c.id as id_computer , "
																+ "c.name as name_computer , "
																+ "c.introduced as introduced ,"
																+ "c.discontinued as discontinued , "
																+ "co.id as id_company , "
																+ "co.name as name_company "
																+ "FROM computer c LEFT JOIN company co on c.company_id = co.id";

	public static final String QUERY_SELECT_ALL_COMPUTER_PER_PAGE = "SELECT c.id as id_computer , "
																		  + "c.name as name_computer , "
																		  + "c.introduced as introduced ,"
																		  + "c.discontinued as discontinued , "
																		  + "co.id as id_company , "
																		  + "co.name as name_company "
																		  + "FROM computer c LEFT JOIN company co on c.company_id = co.id limit ";

	public static final String QUERY_SELECT_COUNT = "SELECT COUNT(*) FROM computer ;";

	public static final String QUERY_SELECT_ALL_BY_NAME = "SELECT c.id as id_computer , "
																+ "c.name as name_computer , "
																+ "c.introduced as introduced"
																+ " , c.discontinued as discontinued , "
																+ "co.id as id_company , "
																+ "co.name as name_company "
																+ "FROM computer c LEFT JOIN company co on c.company_id = co.id "
																+ "WHERE UPPER(c.name) like UPPER(?) ";

	public static final String QUERY_SELECT_COMPUTER_WHERE_ID = "SELECT c.id as id_computer , "
																	 + "c.name as name_computer , "
																	 + "c.introduced as introduced"
																	 + " , c.discontinued as discontinued , "
																	 + "co.id as id_company , "
																	 + "co.name as name_company "
																	 + "FROM computer c LEFT JOIN company co on c.company_id = co.id WHERE c.id = ";

	public static final String QUERY_INSERT_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id)";

	public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ? WHERE id = ? ";

	public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";

	public static final String QUERY_SELECT_ALL_BY_COMPANY = "DELETE FROM computer WHERE id = ?";

	private Connection connect = ManagerConnection.getInstance();

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

				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");

				Company company = new Company.Builder()
											 .id(rs.getLong("id_company"))
											 .name(rs.getString("name_company"))
											 .build();

				computer = new Computer.Builder(rs.getString("name_computer"))
									   .id(rs.getLong("id_computer"))
									   .introduced((introduced != null) ? introduced.toLocalDate() : null)
									   .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
									   .manufacturer(company)
									   .build();

				listOfComputers.add(computer);
			}

		} catch (SQLException e) {
			LOGGER.info("Error : findAll method " + e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		LOGGER.info("findAll method : ", listOfComputers.size());
		LOGGER.debug("ResultSet", rs);
		LOGGER.debug("find all computers " + listOfComputers);
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

				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");

				Company company = new Company.Builder()
											 .id(rs.getLong("id_company"))
											 .name(rs.getString("name_company"))
											 .build();

				computer = new Computer.Builder(rs.getString("name_computer"))
									   .id(rs.getLong("id_computer"))
									   .introduced((introduced != null) ? introduced.toLocalDate() : null)
									   .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
									   .manufacturer(company)
									   .build();

				listOfComputersPerPage.add(computer);
			}
			rs.close();
		} catch (SQLException e) {
			LOGGER.info("Error : findAll method " + e.getMessage());
		} finally {
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
	public Optional<Computer> findById(long id) {
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
				Company company = new Company.Builder()
											 .id(rs.getLong("id_company"))
											 .name(rs.getString("name_company"))
											 .build();

				computer = new Computer.Builder(rs.getString("name_computer"))
										.id(id)
										.introduced((introduced != null) ? introduced.toLocalDate() : null)
										.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
										.manufacturer(company)
										.build();

				LOGGER.info("findById success ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement not closed" + e.getMessage());
			}
		}*/
		return Optional.ofNullable(computer);
	}

	@Override
	public List<Computer> findByName(String name) {

		List<Computer> listComputerFindByName = new ArrayList<Computer>();
		Computer computer;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connect.prepareStatement(QUERY_SELECT_ALL_BY_NAME);
			statement.setString(1, name + "%");
			rs = statement.executeQuery();
			while (rs.next()) {
				Builder builder = new Computer.Builder(rs.getString("name_computer"));
				Date introduced = rs.getDate("introduced");
				Date discontinued = rs.getDate("discontinued");
				Company company = new Company.Builder().id(rs.getLong("id_company")).name(rs.getString("name_company"))
						.build();

				computer = builder.introduced((introduced != null) ? introduced.toLocalDate() : null)
						.disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
						.manufacturer(company).build();

				listComputerFindByName.add(computer);
			}
			LOGGER.info("find by name success ");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		return listComputerFindByName;
	}

	@Override
	public boolean create(Computer obj) {
		boolean result = false;
		PreparedStatement statement = null;
		if (!findById(obj.getId()).isPresent()) {

			String query = QUERY_INSERT_COMPUTER + "VALUES(?, ?, ?, ?)";

			LocalDate introduced = obj.getIntroduced();

			LocalDate disconstinued = obj .getDisconstinued();

			Company company =  obj.getManufacturer();

			try {
				statement = connect.prepareStatement(query);

				statement.setString(1, obj.getName());

				if(introduced!=null){
				statement.setDate(2,Date.valueOf(introduced));
				}else{
					statement.setNull(2, java.sql.Types.TIMESTAMP);
				}
				if(disconstinued!=null){
				statement.setDate(3,Date.valueOf(disconstinued));
				}else{
					statement.setNull(3, java.sql.Types.TIMESTAMP);
				}
				if(company!=null){
				statement.setLong(4, company.getId());
				}else{
					statement.setNull(4, java.sql.Types.BIGINT);
				}

				result = statement.execute();

				LOGGER.info("create computer success ");
			} catch (SQLException e) {
				LOGGER.info("Error : create computer method " + e.getMessage());
			} finally {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.info("Error : statement close" + e.getMessage());
				}
			}
		}

		return result;
	}

	@Override
	public boolean update(Computer obj) {

		boolean result = false;

		PreparedStatement statement = null;

		if (findById(obj.getId()) != null) {

			LocalDate introduced = obj.getIntroduced();

			LocalDate disconstinued = obj .getDisconstinued();

			Company company =  obj.getManufacturer();
			try {
				statement = connect.prepareStatement(QUERY_UPDATE_COMPUTER);
				statement.setString(1, obj.getName());

				if(introduced!=null){
				statement.setDate(2,Date.valueOf(introduced));
				}else{
					statement.setNull(2, java.sql.Types.TIMESTAMP);
				}
				if(disconstinued!=null){
				statement.setDate(3,Date.valueOf(disconstinued));
				}else{
					statement.setNull(3, java.sql.Types.TIMESTAMP);
				}
				if(company!=null){
				statement.setLong(4, company.getId());
				}else{
					statement.setNull(4, java.sql.Types.BIGINT);
				}
				if(statement.executeUpdate()!=0){

					result=true;
				}

				LOGGER.info("update success ");

			} catch (SQLException e) {
				LOGGER.info("Error : Connexion failed" + e.getMessage());
			} finally {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.info("Error : statement close" + e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public void delete(Computer obj) {

		PreparedStatement statement = null;
		try {
			statement = connect.prepareStatement(QUERY_DELETE_COMPUTER);

			statement.setLong(1, obj.getId());

			statement.executeUpdate();

			LOGGER.info("delete success ");

		} catch (SQLException e) {

			LOGGER.info("Error : Connexion failed" + e.getMessage());

		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}

		}
	}

	@Override
	public long countComputer() {
		long result = 0;
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			statement = connect.prepareStatement(QUERY_SELECT_COUNT);
			rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.info("Error : statement close" + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public void delete(long id) {

		Optional<Computer> computer = findById(id);
		if (computer.isPresent()) {
			delete(computer.get());
		}
	}

	@Override
	public List<Computer> findByCompany(Company company) {

		return null;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}
}
