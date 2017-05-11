package com.excilys.service;

import com.excilys.model.Company;
import java.util.List;

public interface CompanyService {

    /**
     * find all company.
     * 
     * @return list of company.
     */
    List<Company> findAll();

    /**
     * find company by id.
     * 
     * @param id.
     * @return a company.
     */
    Company findById(Long id);

    /**
     * create company.
     * 
     * @param obj
     *            a company.
     * @return a company.
     */
    Company create(Company obj);

}
