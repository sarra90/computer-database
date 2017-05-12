package com.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.dao.CompanyDao;
import com.excilys.dto.ComputerDto;
import com.excilys.model.Computer;

public class MapperComputer {

    private CompanyDao companyDao;
    
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public Computer convertToComputer(ComputerDto computerDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d");
        System.out.println(computerDto.getIntroduced());
        Computer computer = new Computer.Builder(computerDto.getName()).id(computerDto.getId())
                .introduced((computerDto.getIntroduced() != null)
                        ? LocalDate.parse(computerDto.getIntroduced(), formatter) : null)
                .disconstinued((computerDto.getDisconstinued() != null)
                        ? LocalDate.parse(computerDto.getDisconstinued(), formatter) : null)
                .manufacturer(companyDao.findById(computerDto.getIdCompany())).build();

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
