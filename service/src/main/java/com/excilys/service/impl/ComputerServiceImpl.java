package com.excilys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ComputerDao computerDao;

    @Override
    public Computer findById(long id) {

        LOGGER.info("findById methode from computer service layer");
        Computer computer = computerDao.findOne(id);

        return computer;
    }

    
    @Override
    public Computer create(Computer obj) {
        return computerDao.save(obj);
    }

    
    @Override
    public void delete(Computer obj) {
        
        LOGGER.info("delete methode from computer service layer");
        if(obj!=null){
            computerDao.delete(obj);
            LOGGER.debug("Computer deleted "+ obj.toString());
        }
        else{
            LOGGER.debug("objet computer null");
        }

    }

    @Override
    public List<Computer> findAll() {

        LOGGER.info("findAll methode from service layer");
        List<Computer> listOfComputers = computerDao.findAll();
        return listOfComputers;
    }

    @Override
    public Page<Computer> findByName(String name, Pageable pageable) {

        LOGGER.info("findByName methode from computer service layer");
        List<Computer> listOfComputersByName = new ArrayList<Computer>();
        if (name != null) {
            listOfComputersByName = computerDao.findByName(name);
            LOGGER.debug("list Of Computers find By Name " + listOfComputersByName.size());
        } else {
            LOGGER.debug("name null ");
        }
        Page<Computer> page = new PageImpl<>(listOfComputersByName, pageable, listOfComputersByName.size());

        return page;
    }

    @Override
    public long countComputer() {
        
        LOGGER.info("count methode from computer service layer");
        long count = computerDao.count();
        return count;
    }

    
    @Override
    public void delete(Long id) {
        
        LOGGER.info("delete methode from computer service layer");
        if (id != null){
            computerDao.delete(id);
            LOGGER.debug("Computer deleted "+ computerDao.findById(id).toString());
        }
        else{
            LOGGER.debug("id null");
        }
    }

    @Override
    public Page<Computer> findAllPerPages(PageRequest pageRequest) {
        
        LOGGER.info("findAllPerPages methode from computer service layer");

        return computerDao.findAll(pageRequest);
    }

    @Override
    public long countComputerByName(String name) {
        
        LOGGER.info("countComputerByName methode from computer service layer");
        return computerDao.countByName(name);
    }

}
