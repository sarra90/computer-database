package com.excilys.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.impl.CompanyDaoImpl;
import com.excilys.dtos.ComputerDto;
import com.excilys.model.Computer;

public class MapperComputer {

	public Computer convertToComputer(ComputerDto computerDto) {

		CompanyDao companyDao = new CompanyDaoImpl();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

		Computer computer = new Computer.Builder(computerDto.getName())
				.introduced(LocalDate.parse(computerDto.getIntroduced(), formatter))
				.disconstinued(LocalDate.parse(computerDto.getDisconstinued(), formatter))
				.manufacturer(companyDao.findById(computerDto.getId()))
				.build();

		return computer;
	}

	public ComputerDto convertToComputerDTO(Computer computer) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

		ComputerDto computerDto = new ComputerDto.Builder()
				.id(computer.getId())
				.name(computer.getName())
				.introduced(computer.getIntroduced().format(formatter))
				.disconstinued(computer.getDisconstinued().format(formatter))
				.idCompany(computer.getManufacturer().getId())
				.build();

		return computerDto;

	}
}
