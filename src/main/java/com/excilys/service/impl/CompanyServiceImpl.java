package com.excilys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.impl.CompanyDaoImpl;
import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

    @Autowired
    private CompanyDao companyDao;

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
