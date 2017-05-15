package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.dao.CompanyDao;
import com.excilys.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
    public static final String QUERY_SELECT_ALL_COMPANY = "SELECT * FROM company ;";
    public static final String QUERY_SELECT_COMPANY_WHERE_ID = "SELECT * FROM company WHERE id = ";
    public static final String QUERY_INSERT_COMPANY = "INSERT INTO company (name)";

    @Autowired
    private DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> findAll() {

        List<Company> listOfCompanys = new ArrayList<Company>();
        Company company = null;
        Connection conn;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(QUERY_SELECT_ALL_COMPANY);
            rs = statement.executeQuery();

            while (rs.next()) {
                company = new Company(rs.getLong("id"), rs.getString("name"));
                listOfCompanys.add(company);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("list company : " + listOfCompanys.size());
        return listOfCompanys;
    }

    @Override
    public Company findById(long id) {
        Company company = null;
        String query = QUERY_SELECT_COMPANY_WHERE_ID + id + ";";

        company = (Company) jdbcTemplate.queryForObject(query, new Object[] { id },
                new BeanPropertyRowMapper<Company>(Company.class));
        return company;
        /*
         * Person person = (Person) jdbcTemplate.
         * queryForObject("SELECT * FROM trn_person where person_id = ? ", new
         * Object[] { personId }, new BeanPropertyRowMapper(Person.class));
         * 
         * return person;
         * 
         */
    }

    @Override
    public Company create(Company obj) {
        PreparedStatement statement = null;
        Connection conn;
        if (findById(obj.getId()) == null) {
            String query = QUERY_INSERT_COMPANY + "VALUES(?)";
            try {
                conn = dataSource.getConnection();
                statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
    public void delete(Company company) {

        PreparedStatement statement = null;
        Connection conn;
        if (findById(company.getId()) != null) {

            String query = "DELETE FROM company WHERE id = ?";
            try {
                conn = dataSource.getConnection();
                statement = conn.prepareStatement(query);
                statement.setLong(1, company.getId());

                if (statement.execute()) {
                    LOGGER.info("delete Method " + company.toString());
                }

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
    }

}