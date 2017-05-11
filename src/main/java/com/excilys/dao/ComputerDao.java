package com.excilys.dao;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import java.util.List;
import java.util.Optional;

public interface ComputerDao {

    /**
     * find all Computers.
     * 
     * 
     * @return a list of computer.
     */
    List<Computer> findAll();

    /**
     * find all Computers per page.
     * 
     * @param offset.
     * @param numberOfRecords.
     * @return a list of computer.
     */
    List<Computer> findAllPerPage(int offset, int numberOfRecords);

    /**
     * find Computer by id.
     * 
     * @param id.
     * @return Computer
     */
    Optional<Computer> findById(long id);

    /**
     * find computer by name.
     * 
     * @param name.
     * @return list of computer.
     */
    public List<Computer> findByName(String name);

    /**
     * find computer by company.
     * 
     * @param company.
     * @return a list of computer.
     */
    List<Computer> findByCompany(Company company);

    /**
     * create Computer.
     * 
     * @param Computer.
     * @return Computer
     */
    boolean create(Computer computer);

    /**
     * update Computer.
     * 
     * @param Computer.
     * @return Computer.
     */
    boolean update(Computer computer);

    /**
     * delete Computer.
     * 
     * @param Computer.
     */
    void delete(Computer computer);

    /**
     * count all computers.
     * 
     * @return the number of computers.
     */
    long countComputer();

    /**
     * delete computer by id.
     * 
     * @param id.
     */
    void delete(long id);

}
