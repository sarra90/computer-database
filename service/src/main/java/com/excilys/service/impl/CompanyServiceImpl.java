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

public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        
        LOGGER.info("findAll methode from Company service layer");
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long id) {
        
        Company company =null;
        LOGGER.info("findById methode from Company service layer");
        if(id!=null){
            company= companyDao.findOne(id);
            LOGGER.debug("Company founded "+ company.toString());
        }
        else{
            LOGGER.debug("id company null ");
        }
        return company;
    }

    @Override
    public Company create(Company obj) {
        
        LOGGER.info("create methode from Company service layer");
        return companyDao.save(obj);
    }

    
    @Override
    public void delete(Company obj) {
        
        LOGGER.info("delete methode from Company service layer");
        if(obj!=null){
        companyDao.delete(obj);
        LOGGER.debug("Company deleted "+ obj.toString());
        }
        else{
            LOGGER.debug("company null ");
        }
    }

}
