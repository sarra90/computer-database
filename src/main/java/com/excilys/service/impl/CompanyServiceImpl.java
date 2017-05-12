package com.excilys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.impl.CompanyDaoImpl;
import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findById(id);
    }

    @Override
    public Company create(Company obj) {
        return companyDao.create(obj);
    }

}
