package com.excilys.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String QUERY_INSERT_COMPANY = "INSERT INTO company (name) VALUES(?)";
    public static final String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> findAll() {

        List<Company> listOfCompanys = jdbcTemplate.query(QUERY_SELECT_ALL_COMPANY, new RowMapper<Company>() {

            @Override
            public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Company company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
                return company;
            }

        });
        return listOfCompanys;
    }

    @Override
    public Company findById(long id) {
        Company company = null;
        String query = QUERY_SELECT_COMPANY_WHERE_ID + id + ";";

        company = (Company) jdbcTemplate.queryForObject(query, new Object[] { id }, new RowMapper<Company>() {
            @Override
            public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Company company = new Company.Builder().id(resultSet.getLong("id_company"))
                        .name(resultSet.getString("name_company")).build();

                return company;
            }
        });
        return company;
    }

    @Override
    public Company create(Company obj) {

        int result = jdbcTemplate.update(QUERY_INSERT_COMPANY, obj.getName());
        if (result == 1) {
            LOGGER.info("create company success ");
        } else {
            LOGGER.info("Error : create company method ");
        }
        return obj;
    }

    @Override
    public void delete(Company company) {

        int result = jdbcTemplate.update(QUERY_DELETE_COMPANY, company.getId());

        if (result == 1) {
            LOGGER.info("delete company success ");
        } else {
            LOGGER.info("Error : delete company method ");
        }
    }

}