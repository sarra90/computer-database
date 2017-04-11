package com.excilys.service.impl;

import java.util.List;

import com.excilys.dao.DAO;
import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

public class ComputerServiceImpl implements ComputerService {

	private DAO<Computer> computerDao = new ComputerDaoImpl();

	@Override
	public Computer findById(Long id) {

		return computerDao.findById(id);
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
	public List<Computer> findAll() {
		return computerDao.findAll();
	}

}
