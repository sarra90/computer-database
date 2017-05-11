package com.excilys.dao;

import com.excilys.model.Company;
import java.util.List;

/**
 * interface represente a company Dao.
 * 
 * @author excilys
 *
 */
public interface CompanyDao {

    /**
     * find all company.
     * 
     * 
     * @return
     */
    List<Company> findAll();

    /**
     * find company by id.
     * 
     * 
     * @param id.
     * @return company
     */
    Company findById(long id);

    /**
     * create company.
     * 
     * @param company.
     * @return company
     */
    Company create(Company company);

    /**
     * delete company.
     * 
     * @param company.
     */
    void delete(Company company);

}
