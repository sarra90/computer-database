package com.excilys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.ComputerDao;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

@Service("computerService")
@Transactional
public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

    @Resource
    private ComputerDao computerDao;

    @Override
    public Computer findById(long id) {
        LOGGER.info("findById methode from service layer");
        Computer computer = computerDao.findOne(id);

        return computer;
    }

    @Override
    public Computer create(Computer obj) {
        LOGGER.info("create methode from service layer");
        return computerDao.save(obj);
    }

    @Override
    public void delete(Computer obj) {
        computerDao.delete(obj);

    }

    @Override
    public List<Computer> findAll() {
        LOGGER.info("findAll methode from service layer");
        List<Computer> listOfComputers = computerDao.findAll();
        return listOfComputers;
    }

    @Override
    public Page<Computer> findByName(String name, Pageable pageable) {
        LOGGER.info("findByName methode from service layer");
        List<Computer> listOfComputersByName = computerDao.findByName(name);
        Page<Computer> pages = new PageImpl<>(listOfComputersByName, pageable, listOfComputersByName.size());
        return pages;
    }

    @Override
    public long countComputer() {
        LOGGER.info("count methode from service layer");
        long count = computerDao.count();
        return count;
    }

    @Override
    public void delete(long id) {
        LOGGER.info("delete methode from service layer");
        computerDao.delete(id);
    }

    @Override
    public Page<Computer> findAllPerPages(PageRequest pageRequest) {
        LOGGER.info("findAllPerPages methode from service layer");
        return computerDao.findAll(pageRequest);
    }

    @Override
    public long countComputerByName(String name) {
        return computerDao.countByName(name);
    }

}
