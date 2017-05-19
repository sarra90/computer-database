package com.excilys.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.impl.ComputerDaoImpl;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

@Service("computerService")
public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

    @Autowired
    private ComputerDao computerDao;

    @Override
    public Computer findById(long id) {

        Computer computer = computerDao.findOne(id);

        return computer;
    }

    @Override
    public Computer create(Computer obj) {
        return computerDao.save(obj);
    }

    @Override
    public void delete(Computer obj) {
        computerDao.delete(obj);

    }

    @Override
    public List<Computer> findAll() {
        List<Computer> listOfComputers = computerDao.findAll();
        return listOfComputers;
    }

    @Override
    public List<Computer> findAllPerPage(int offset, int numberOfRecords) {

        List<Computer> listOfComputersPerPage = computerDao.findAllPerPage(offset, numberOfRecords);
        return listOfComputersPerPage;
    }

    @Override
    public List<Computer> findByName(String name) {
        List<Computer> listOfComputersByName = computerDao.findByName(name);
        return listOfComputersByName;
    }

    @Override
    public long countComputer() {
        long count = computerDao.countComputer();
        return count;
    }

    @Override
    public void delete(long id) {

        computerDao.delete(id);
    }

}
