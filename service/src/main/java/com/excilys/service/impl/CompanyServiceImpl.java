package com.excilys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.CompanyDao;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        LOGGER.info("findAll methode from service layer");
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long id) {
        LOGGER.info("findById methode from service layer");
        return companyDao.findOne(id);
    }

    @Override
    public Company create(Company obj) {
        LOGGER.info("create methode from service layer");
        return companyDao.save(obj);
    }

}
