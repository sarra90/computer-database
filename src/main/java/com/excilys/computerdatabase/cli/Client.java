package com.excilys.computerdatabase.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.models.Company;
import com.excilys.computerdatabase.models.Computer;
import com.excilys.computerdatabase.services.CompanyService;
import com.excilys.computerdatabase.services.ComputerService;
import com.excilys.computerdatabase.services.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.services.impl.ComputerServiceImpl;

public class Client {

	public static void main(String[] args) {

		int menu = 0;
		int sousmenu = 0;
		boolean arret = false;

		ComputerService computerService = new ComputerServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();

		System.out.println("List Computers : write 1");
		System.out.println("List companies : write 2");
		System.out.println("Show computer details : write 3");
		System.out.println("Create a computer : write 4");
		System.out.println("Update a computer : write 5");
		System.out.println("Delete a computer : write 6");
		System.out.println("To leave : write 7");

		Scanner sc = new Scanner(System.in);
		Scanner inputscanner = new Scanner(System.in);
		String input = inputscanner.next();
		menu = Integer.valueOf(input);
		while (!arret) {
		switch (menu) {
		case 1 :{
			System.out.println("List Computers");
			List<Computer> listOfComputers = computerService.findAll();
			for (Computer computer : listOfComputers) {
				System.out.println(computer.toString());
			}
			input = inputscanner.next();
			menu = Integer.valueOf(input);
		}break;
		case 2 :{
			System.out.println("List Company");
			System.out.println(companyService.findAll());
			input = inputscanner.next();
			menu = Integer.valueOf(input);
		}break;

		case 3 :{
			System.out.println("Show computer details");
			System.out.println("	write the id: ");
			input = inputscanner.next();
			System.out.println(companyService.findById(Long.parseLong(input)));
			input = inputscanner.next();
			menu = Integer.valueOf(input);
		}break;
		case 4 :{
			System.out.println("---Create Computer---");
			Computer computer = null ;
			System.out.println("	write the name : ");
			input = inputscanner.next();
			String nom = input;
			System.out.println("	write the date introduced following this format: dd/mm/yyyy");
			input = inputscanner.next();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
			Date dateIntroduced = null;
			try {
				dateIntroduced = simpleDateFormat.parse(input);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("	write the date discontinued following this format: dd/mm/yyyy ");
			input = inputscanner.next();
			Date dateDiscontinued = null;
			try {
				dateDiscontinued = simpleDateFormat.parse(input);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	
		
			System.out.println("	Add a company to the computer ");
			System.out.println("	--> create a new company : write 1 ");
			System.out.println("	--> select a company from the exist list company : write 2 ");
			input = inputscanner.next();
			sousmenu = Integer.valueOf(input);
				switch (sousmenu) {
				case 1 :{
					System.out.println("---Create Company---");
					System.out.println("	write the name : ");
					input = inputscanner.next();
					String name = input;
					Company company= companyService.create(new Company(name));
					
					computer=new Computer.Builder(nom)
								.introduced(dateIntroduced)
								.disconstinued(dateDiscontinued)
								.manufacturer(company)
								.build();
					computerService.create(computer);
					input = inputscanner.next();
					menu = Integer.valueOf(input);
				}break;
				case 2 :{
					System.out.println("---select Company---");
					
				}break;

				default:
					break;
				}
			//
			input = inputscanner.next();
			menu = Integer.valueOf(input);
			
		}break;
		case 5 :{
			System.out.println("---Update a computer---");
			
			input = inputscanner.next();
			String dateDiscontinued = input;
		}break;
		case 6 :{
			System.out.println("---Delete a computer---");
			
			input = inputscanner.next();
			String dateDiscontinued = input;
		}break;
		case 7 : {
			arret = true;
		}break;
		default:
			System.out.println("Make a choice between 1 to 7");
			break;
		}
		
		}
		
	}
}