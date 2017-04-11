package com.excilys.ui;

import java.util.ArrayList;
import java.util.List;

import com.excilys.builder.Computerbuilder;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.ComputerServiceImpl;

public class MainTest {

	public static void main(String[] args) {

		
		ComputerService  computerService = new ComputerServiceImpl();
		
		Computer computer = computerService.findById(1l);
		
		//System.out.println(computerService.findById(1l).toString());
		
		
		/*List<Computer> listOfComputers = computerService.findAll();
		
		for(Computer computer2 : listOfComputers){
			
			System.out.println(computer2.toString());
			
		}*/
		Company company = new Company(444444l, "macc");
		
		Computer comp = new Computer(new Computerbuilder("mac").setDisconstinued(null).setIntroduced(null));
		comp.setId(2000l);
		comp.setManufacturer(company);
		
		System.out.println(computerService.create(comp));
		
		computer.setName("sara");
		
		System.out.println(computerService.update(computer).toString());
		
		
		
	}

}
