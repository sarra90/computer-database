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

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

    @Autowired
    private ComputerDao computerDao;

    @Override
    public Optional<Computer> findById(long id) {

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
