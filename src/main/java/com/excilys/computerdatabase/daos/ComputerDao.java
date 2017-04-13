package com.excilys.computerdatabase.daos;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.models.Computer;

public interface ComputerDao {

	/**
	 * find all Computers
	 * 
	 * @return
	 */
	public List<Computer> findAll();

	/**
	 * find Computer by id
	 * 
	 * @param id
	 * @return Computer
	 */
	public  Optional<Computer> findById(Long id);

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

}
