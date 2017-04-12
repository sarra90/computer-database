package com.excilys.computerdatabase.daos;

import java.util.List;

import com.excilys.computerdatabase.models.Computer;

public interface ComputerDao {

	/**
	 * find all Computers
	 * @return
	 */
	public abstract List<Computer> findAll();
	
	/**
	 * find Computer by id 
	 * @param id 
	 * @return Computer
	 */
	public abstract Computer findById(Long id);
	
	/**
	 * create Computer
	 * @param Computer
	 * @return Computer
	 */
	public abstract Computer create(Computer computer);
	
	/**
	 * update Computer
	 * @param Computer
	 * @return Computer
	 */
	public abstract Computer update(Computer computer);
	
	/**
	 * delete Computer
	 * @param Computer
	 */
	public abstract void delete(Computer computer);
	
}
