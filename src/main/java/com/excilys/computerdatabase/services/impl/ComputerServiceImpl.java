package com.excilys.computerdatabase.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.daos.ComputerDao;
import com.excilys.computerdatabase.daos.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.models.Computer;
import com.excilys.computerdatabase.services.ComputerService;

public class ComputerServiceImpl implements ComputerService {

	private ComputerDao computerDao = new ComputerDaoImpl();
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Override
	public Optional<Computer> findById(Long id) {
		
		Optional<Computer> computer = computerDao.findById(id);
		
		return computer;
	}

	@Override
	public Computer create(Computer obj) {
		return computerDao.create(obj);
	}

	@Override
	public Computer update(Computer obj) {
		return computerDao.update(obj);
	}

	@Override
	public void delete(Computer obj) {
		computerDao.delete(obj);

	}

	@Override
	public Optional<List<Computer>> findAll() {
		Optional<List<Computer>> listOfComputers = computerDao.findAll();
		return listOfComputers;
	}

}
