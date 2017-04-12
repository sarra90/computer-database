package com.excilys.service.impl;

import java.util.List;

import com.excilys.dao.DAO;
import com.excilys.dao.impl.CompanyDaoImpl;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;

public class CompanyServiceImpl implements CompanyService{

	private DAO<Company> companyDao = new CompanyDaoImpl();
	
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

	@Override
	public Company update(Company obj) {
		return companyDao.update(obj);
	}

	@Override
	public void delete(Company obj) {
		companyDao.delete(obj);		
	}

}
