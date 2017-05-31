package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerController {
    
    @Autowired
    ComputerService computerSevice;
    
    @RequestMapping(value="/getAll" , method=RequestMethod.GET)
    public List<Computer> getAll(){
        return computerSevice.findAll();
    }
    
    @RequestMapping(value="/find/{id}" , method=RequestMethod.GET)
    public Computer findById(@PathVariable Long id){
        return computerSevice.findById(id);
    }
    
    @RequestMapping(value="/getAll/{name}" , method=RequestMethod.GET)
    public List<Computer> getByname(@PathVariable String name){
        return computerSevice.findByName(name);
    }
    
    @RequestMapping(value="/add", method =RequestMethod.POST)
    public ComputerDto add(@RequestBody ComputerDto computerDto){
        Computer computer=MapperComputer.convertToComputer(computerDto);
        computerSevice.create(computer);
        return MapperComputer.convertToComputerDTO(computer);
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ComputerDto delete(ComputerDto computerDto){
        Computer computer = MapperComputer.convertToComputer(computerDto);
        computerSevice.delete(computer);
        return MapperComputer.convertToComputerDTO(computer);
    }
}





