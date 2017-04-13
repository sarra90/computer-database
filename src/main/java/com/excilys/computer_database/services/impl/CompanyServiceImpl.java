package com.excilys.computer_database.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.daos.CompanyDao;
import com.excilys.computer_database.daos.impl.CompanyDaoImpl;
import com.excilys.computer_database.daos.impl.ComputerDaoImpl;
import com.excilys.computer_database.models.Company;
import com.excilys.computer_database.services.CompanyService;

public class CompanyServiceImpl implements CompanyService{

	private CompanyDao companyDao = new CompanyDaoImpl();
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

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
