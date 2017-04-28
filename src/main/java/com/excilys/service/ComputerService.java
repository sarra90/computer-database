package com.excilys.service;

import java.util.List;
import java.util.Optional;

import com.excilys.model.Computer;

/**
 * 
 * @author sarra
 *
 */
public interface ComputerService {

	/**
	 * 
	 * @return
	 */
	public List<Computer> findAll();

	public List<Computer> findAllPerPage(int offset, int numberOfRecords);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Computer> findById(long id);

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Computer create(Computer obj);

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Computer update(Computer obj);

	/**
	 * 
	 * @param obj
	 */
	public void delete(Computer obj);

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
	
	/**
	 * 
	 * @param id
	 */
	public void delete(long id);
}
