package com.excilys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Computer;

public interface ComputerDao extends JpaRepository<Computer, Long>{


	public List<Computer> findByName(String name);
	
	public Page<Computer> findAllPerPage(Pageable  peagble);
}
