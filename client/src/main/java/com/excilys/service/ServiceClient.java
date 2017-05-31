package com.excilys.service;

import java.util.List;

import com.excilys.dto.ComputerDto;
import com.excilys.model.Computer;

public interface ServiceClient {

    
    List<Computer> getAll();
    
    List<Computer> getByName(String name);
    
    Computer getById(Long id);
    
    Computer add(ComputerDto computerDto);
    
    Computer delete(Computer computer);
    
}
