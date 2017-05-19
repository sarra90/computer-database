package com.excilys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Computer;

public interface ComputerDao extends JpaRepository<Computer, Long>{


}
