package com.excilys.dao;

import java.util.List;
import java.util.Optional;

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

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Computer> findByName(String name);

	/**
	 * 
	 * @return
	 */
	public long countComputer();
}
