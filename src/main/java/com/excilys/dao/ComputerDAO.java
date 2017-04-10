package com.excilys.dao;

import com.excilys.model.Computer;

public interface ComputerDAO {
	
	public void add(Computer computer);
	
	public void listAll();
	
	public void delete(Computer computer);
	
	public void update(Computer computer);

}
