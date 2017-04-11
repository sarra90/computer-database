package com.excilys.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.tools.ConnectionMySql;

/**
 * class DAO to abstract the access of data base.
 * @author sarra
 * @version 10/04/17
 * @param <T> 
 */
public abstract class DAO<T> {
	
	/**
	 * 
	 */
	public Connection connect = ConnectionMySql.getInstance();
	
	/**
	 * return all computers from data base
	 * @return list of computers
	 */
	public abstract List<T> findAll();
	
	/**
	 * find the object T by id 
	 * @param id 
	 * @return T
	 */
	public abstract T findById(Long id);
	
	/**
	 * create the object T
	 * @param obj
	 * @return T
	 */
	public abstract T create(T obj);
	
	/**
	 * update the object T 
	 * @param obj
	 * @return T
	 */
	public abstract T update(T obj);
	
	/**
	 * delete the object T
	 * @param obj
	 */
	public abstract void delete(T obj);
	

}