package com.excilys.service;

import com.excilys.model.Computer;
import java.util.List;
import java.util.Optional;

/**
 * interface represent a computer service.
 * 
 * @author sarra
 *
 */
public interface ComputerService {

    /**
     * find all computer.
     * 
     * @return a list of computer.
     */
    List<Computer> findAll();

    /**
     * find all computer per page.
     * 
     * @param offset.
     * @param numberOfRecords.
     * @return a list of computer.
     */
    List<Computer> findAllPerPage(int offset, int numberOfRecords);

    /**
     * find computer by id.
     * 
     * @param id.
     * @return a computer.
     */
    Optional<Computer> findById(long id);

    /**
     * create computer.
     * 
     * @param obj.
     * @return a boolean .
     */
    boolean create(Computer obj);

    /**
     * update computer.
     * 
     * @param obj.
     * @return a boolean.
     */
    boolean update(Computer obj);

    /**
     * delete a computer.
     * 
     * @param obj.
     */
    public void delete(Computer obj);

    /**
     * find computer by name.
     * 
     * @param name.
     * @return a list of computer.
     */
    List<Computer> findByName(String name);

    /**
     * count a number of computer.
     * 
     * @return a long the number of computer.
     */
    long countComputer();

    /**
     * delete computer.
     * 
     * @param id.
     */
    void delete(long id);
}
