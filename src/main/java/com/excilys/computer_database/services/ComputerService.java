package com.excilys.computer_database.services;

import java.util.List;
import java.util.Optional;

import com.excilys.computer_database.models.Computer;

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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public  Optional<Computer> findById(Long id);
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public  Computer create(Computer obj);
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public  Computer update(Computer obj);
	
	/**
	 * 
	 * @param obj
	 */
	public  void delete(Computer obj);
}
