package com.excilys.dao;

import java.util.List;
import java.util.Optional;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public interface ComputerDao {

	/**
	 * find all Computers
	 * 
	 * @return
	 */
	public List<Computer> findAll();

	/**
	 * 
	 * @param offset
	 * @param numberOfRecords
	 * @return
	 */
	public List<Computer> findAllPerPage(int offset, int numberOfRecords);
	/**
	 * find Computer by id
	 * 
	 * @param id
	 * @return Computer
	 */
	public  Optional<Computer> findById(long id);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Computer> findByName(String name);

	/**
	 * 
	 * @param company
	 * @return
	 */
	public List<Computer> findByCompany(Company company);
	
	/**
	 * create Computer
	 * 
	 * @param Computer
	 * @return Computer
	 */
	public Computer create(Computer computer);

	/**
	 * update Computer
	 * 
	 * @param Computer
	 * @return Computer
	 */
	public Computer update(Computer computer);

	/**
	 * delete Computer
	 * 
	 * @param Computer
	 */
	public void delete(Computer computer);

	
	/**
	 * 
	 * @return
	 */
	public long countComputer();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void delete(long id);
}
