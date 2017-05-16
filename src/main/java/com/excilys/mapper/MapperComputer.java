package com.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.dao.CompanyDao;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.ComputerDto;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class MapperComputer {


    public Computer convertToComputer(ComputerDto computerDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d");

        CompanyDto companyDto = new CompanyDto.Builder().id(computerDto.getIdCompany()).name(computerDto.getNameCompany())
                .build();
        Company company = new MapperCompany().convertToCompany(companyDto);
        Computer computer = new Computer.Builder(computerDto.getName()).id(computerDto.getId())
                .introduced((computerDto.getIntroduced() != null)
                        ? LocalDate.parse(computerDto.getIntroduced(), formatter) : null)
                .disconstinued((computerDto.getDisconstinued() != null)
                        ? LocalDate.parse(computerDto.getDisconstinued(), formatter) : null)
                .manufacturer(company).build();

        return computer;
    }

    public ComputerDto convertToComputerDTO(Computer computer) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

        ComputerDto computerDto = new ComputerDto.Builder().id(computer.getId()).name(computer.getName())
                .introduced(computer.getIntroduced().format(formatter))
                .disconstinued(computer.getDisconstinued().format(formatter))
                .idCompany(computer.getManufacturer().getId()).build();

        return computerDto;

    }
}
