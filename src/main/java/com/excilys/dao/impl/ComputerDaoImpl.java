package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.dao.ComputerDao;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Repository
public class ComputerDaoImpl implements ComputerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

    public static final String QUERY_SELECT_ALL_COMPUTER = "SELECT c.id as id_computer , "
            + "c.name as name_computer , " + "c.introduced as introduced ," + "c.discontinued as discontinued , "
            + "co.id as id_company , " + "co.name as name_company "
            + "FROM computer c LEFT JOIN company co on c.company_id = co.id";

    public static final String QUERY_SELECT_ALL_COMPUTER_PER_PAGE = "SELECT c.id as id_computer , "
            + "c.name as name_computer , " + "c.introduced as introduced ," + "c.discontinued as discontinued , "
            + "co.id as id_company , " + "co.name as name_company "
            + "FROM computer c LEFT JOIN company co on c.company_id = co.id limit ";

    public static final String QUERY_SELECT_COUNT = "SELECT COUNT(*) FROM computer ;";

    public static final String QUERY_SELECT_ALL_BY_NAME = "SELECT c.id as id_computer , " + "c.name as name_computer , "
            + "c.introduced as introduced" + " , c.discontinued as discontinued , " + "co.id as id_company , "
            + "co.name as name_company " + "FROM computer c LEFT JOIN company co on c.company_id = co.id "
            + "WHERE UPPER(c.name) like UPPER(?) ";

    public static final String QUERY_SELECT_COMPUTER_WHERE_ID = "SELECT c.id as id_computer , "
            + "c.name as name_computer , " + "c.introduced as introduced" + " , c.discontinued as discontinued , "
            + "co.id as id_company , " + "co.name as name_company "
            + "FROM computer c LEFT JOIN company co on c.company_id = co.id WHERE c.id = ?";

    public static final String QUERY_INSERT_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id)VALUES(?, ?, ?, ?) ";

    public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ? WHERE id = ? ";

    public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";

    public static final String QUERY_SELECT_ALL_BY_COMPANY = "DELETE FROM computer WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Computer> findAll() {

        List<Computer> listOfComputers = jdbcTemplate.query(QUERY_SELECT_ALL_COMPUTER, new RowMapper<Computer>() {

            @Override
            public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Date introduced = resultSet.getDate("introduced");
                Date discontinued = resultSet.getDate("discontinued");

                Company company = new Company.Builder().id(resultSet.getLong("id_company"))
                        .name(resultSet.getString("name_company")).build();

                Computer computer = new Computer.Builder(resultSet.getString("name_computer"))
                        .id(resultSet.getLong("id_computer"))
                        .introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
                        .build();

                return computer;
            }

        });

        return listOfComputers;

    }

    @Override
    public List<Computer> findAllPerPage(int offset, int numberOfRecords) {

        String query = QUERY_SELECT_ALL_COMPUTER_PER_PAGE + offset + ", " + numberOfRecords;
        List<Computer> listOfComputersPerPage = jdbcTemplate.query(query, new RowMapper<Computer>() {
            @Override
            public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Date introduced = resultSet.getDate("introduced");
                Date discontinued = resultSet.getDate("discontinued");
                Company company = new Company.Builder().id(resultSet.getLong("id_company"))
                        .name(resultSet.getString("name_company")).build();

                Computer computer = new Computer.Builder(resultSet.getString("name_computer"))
                        .id(resultSet.getLong("id_computer"))
                        .introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
                        .build();
                return computer;
            }
        });
        LOGGER.info("findAllPerPage method : ", listOfComputersPerPage.size());
        return listOfComputersPerPage;
    }

    @Override
    public Optional<Computer> findById(long id) {
        Computer computer = jdbcTemplate.queryForObject(QUERY_SELECT_COMPUTER_WHERE_ID, new Object[] { id },
                new RowMapper<Computer>() {

                    @Override
                    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        Date introduced = resultSet.getDate("introduced");
                        Date discontinued = resultSet.getDate("discontinued");
                        Company company = new Company.Builder().id(resultSet.getLong("id_company"))
                                .name(resultSet.getString("name_company")).build();
                        Computer computer = new Computer.Builder(resultSet.getString("name_computer")).id(id)
                                .introduced((introduced != null) ? introduced.toLocalDate() : null)
                                .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
                                .manufacturer(company).build();
                        return computer;
                    }

                });
        return Optional.ofNullable(computer);
    }

    @Override
    public List<Computer> findByName(String name) {

        List<Computer> listComputerFindByName = jdbcTemplate.query(QUERY_SELECT_ALL_BY_NAME,
                new Object[] { name + "%" }, new RowMapper<Computer>() {

                    @Override
                    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        Date introduced = resultSet.getDate("introduced");
                        Date discontinued = resultSet.getDate("discontinued");
                        Company company = new Company.Builder().id(resultSet.getLong("id_company"))
                                .name(resultSet.getString("name_company")).build();

                        Computer computer = new Computer.Builder(resultSet.getString("name_computer"))
                                .introduced((introduced != null) ? introduced.toLocalDate() : null)
                                .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null)
                                .manufacturer(company).build();
                        return computer;
                    }

                });
        LOGGER.info("find by name success ");
        return listComputerFindByName;
    }

    @Override
    public Computer create(Computer obj) {
        
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(QUERY_INSERT_COMPUTER);
                setStatement(ps, obj);
                return ps;
            }
        });

        if (result > 0) {
            LOGGER.info("create computer success ");
        } else {
            LOGGER.info("Error : create computer method ");
        }

        return obj;
    }

    @Override
    public Computer update(Computer obj) {

        int result = jdbcTemplate.update(QUERY_UPDATE_COMPUTER, obj.getIntroduced(), obj.getDisconstinued(),
                obj.getManufacturer());

        if (result == 1) {

            LOGGER.info("update computer success ");
        } else {
            LOGGER.error("Error : update computer method ");
        }

        return obj;
    }

    @Override
    public void delete(Computer obj) {

        int result = jdbcTemplate.update(QUERY_DELETE_COMPUTER, obj.getIntroduced(), obj.getDisconstinued(),
                obj.getManufacturer());

        if (result == 1) {

            LOGGER.info("delete computer success ");
        } else {
            LOGGER.error("Error : delete computer method ");
        }
    }

    @Override
    public long countComputer() {
        long rowCount = jdbcTemplate.queryForObject(QUERY_SELECT_COUNT, Integer.class);

        return rowCount;
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

    private PreparedStatement setStatement(PreparedStatement statement, Computer obj) throws SQLException {

        statement.setString(1, obj.getName());

        if (obj.getIntroduced() != null) {
            statement.setDate(2, Date.valueOf(obj.getIntroduced()));
        } else {
            statement.setNull(2, java.sql.Types.TIMESTAMP);
        }

        if (obj.getDisconstinued() != null) {
            statement.setDate(3, Date.valueOf(obj.getDisconstinued()));
        } else {
            statement.setNull(3, java.sql.Types.TIMESTAMP);
        }

        if (obj.getManufacturer() != null) {
            statement.setLong(4, obj.getManufacturer().getId());
        } else {
            statement.setNull(4, java.sql.Types.BIGINT);
        }
        return statement;
    }
}