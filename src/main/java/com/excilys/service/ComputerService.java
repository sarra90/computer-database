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
