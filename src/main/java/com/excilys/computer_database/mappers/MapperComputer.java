package com.excilys.computer_database.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.computer_database.daos.CompanyDao;
import com.excilys.computer_database.daos.impl.CompanyDaoImpl;
import com.excilys.computer_database.dtos.ComputerDto;
import com.excilys.computer_database.models.Computer;

public class MapperComputer {

	public Computer convertToComputer(ComputerDto computerDto) {

		CompanyDao companyDao = new CompanyDaoImpl();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

		Computer computer = new Computer.Builder(computerDto.getName()).build();

		computer.setIntroduced(LocalDate.parse(computerDto.getIntroduced(), formatter));

		computer.setDisconstinued(LocalDate.parse(computerDto.getDisconstinued(), formatter));

		computer.setManufacturer(companyDao.findById(computerDto.getId()));

		return computer;
	}

	public ComputerDto convertToComputerDTO(Computer computer) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

		ComputerDto computerDto = new ComputerDto.Builder().build();

		computerDto.setId(computerDto.getId());

		computerDto.setName(computer.getName());

		computerDto.setIntroduced(computer.getIntroduced().format(formatter));

		computerDto.setDisconstinued(computer.getDisconstinued().format(formatter));

		computerDto.setIdCompany(computer.getManufacturer().getId());

		return computerDto;

	}
}
