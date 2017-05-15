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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.dao.ComputerDao;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.Builder;

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
            + "FROM computer c LEFT JOIN company co on c.company_id = co.id WHERE c.id = ";

    public static final String QUERY_INSERT_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) ";

    public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ? WHERE id = ? ";

    public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";

    public static final String QUERY_SELECT_ALL_BY_COMPANY = "DELETE FROM computer WHERE id = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Computer> findAll() {

        List<Computer> listOfComputers = new ArrayList<Computer>();
        Computer computer = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(QUERY_SELECT_ALL_COMPUTER);
            rs = statement.executeQuery();

            while (rs.next()) {

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                Company company = new Company.Builder().id(rs.getLong("id_company")).name(rs.getString("name_company"))
                        .build();

                computer = new Computer.Builder(rs.getString("name_computer")).id(rs.getLong("id_computer"))
                        .introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
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
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                Company company = new Company.Builder().id(rs.getLong("id_company")).name(rs.getString("name_company"))
                        .build();

                computer = new Computer.Builder(rs.getString("name_computer")).id(rs.getLong("id_computer"))
                        .introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
                        .build();

                listOfComputersPerPage.add(computer);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.info("Error : findAll method " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
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
        Connection conn;
        String query = QUERY_SELECT_COMPUTER_WHERE_ID + id + ";";
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");
                Company company = new Company.Builder().id(rs.getLong("id_company")).name(rs.getString("name_company"))
                        .build();

                computer = new Computer.Builder(rs.getString("name_computer")).id(id)
                        .introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
                        .build();

                LOGGER.info("findById success ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.info("Error : statement not closed" + e.getMessage());
            }
        }
        return Optional.ofNullable(computer);
    }

    @Override
    public List<Computer> findByName(String name) {

        List<Computer> listComputerFindByName = new ArrayList<Computer>();
        Computer computer;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(QUERY_SELECT_ALL_BY_NAME);
            statement.setString(1, name + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                Builder builder = new Computer.Builder(rs.getString("name_computer"));
                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");
                Company company = new Company.Builder().id(rs.getLong("id_company")).name(rs.getString("name_company"))
                        .build();

                computer = builder.introduced((introduced != null) ? introduced.toLocalDate() : null)
                        .disconstinued((discontinued != null) ? discontinued.toLocalDate() : null).manufacturer(company)
                        .build();

                listComputerFindByName.add(computer);
            }
            LOGGER.info("find by name success ");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error : statement close" + e.getMessage());
            }
        }
        return listComputerFindByName;
        /*
         * List<Computer> listComputerFindByName =
         * jdbcTemplate.query(QUERY_SELECT_ALL_BY_NAME, new
         * RowMapper<Computer>(){
         * 
         * @Override public Computer mapRow(ResultSet result, int rowNum) throws
         * SQLException { Computer computer= new
         * Computer.Builder(result.getString(name)) .build(); return null; } });
         * 
         * return listComputerFindByName; /* String sqlSelect =
         * "SELECT * FROM contact"; List<Contact> listContact =
         * jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
         * 
         * public Contact mapRow(ResultSet result, int rowNum) throws
         * SQLException { Contact contact = new Contact();
         * contact.setName(result.getString("name"));
         * contact.setEmail(result.getString("email"));
         * contact.setAddress(result.getString("address"));
         * contact.setPhone(result.getString("telephone"));
         * 
         * return contact; }
         * 
         * });
         ***************
         * String sqlSelect = "SELECT * FROM contact"; List<Contact> listContact
         * = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
         * 
         * public Contact mapRow(ResultSet result, int rowNum) throws
         * SQLException { Contact contact = new Contact();
         * contact.setName(result.getString("name"));
         * contact.setEmail(result.getString("email"));
         * contact.setAddress(result.getString("address"));
         * contact.setPhone(result.getString("telephone"));
         * 
         * return contact; }
         * 
         * });
         */

    }

    @Override
    public Computer create(Computer obj) {

        if (!findById(obj.getId()).isPresent()) {

            String query = QUERY_INSERT_COMPUTER + "VALUES(?, ?, ?, ?)";

            int result = jdbcTemplate.update(query, obj.getName(), obj.getIntroduced(), obj.getDisconstinued(),
                    obj.getManufacturer().getId());

            if (result == 1) {

                LOGGER.info("create computer success ");
            } else {
                LOGGER.error("Error : create computer method ");
            }
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
        long result = 0;
        PreparedStatement statement;
        ResultSet rs;
        Connection conn;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(QUERY_SELECT_COUNT);
            rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
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

}