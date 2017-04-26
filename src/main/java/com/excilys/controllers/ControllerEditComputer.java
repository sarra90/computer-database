package com.excilys.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dtos.ComputerDto;
import com.excilys.mappers.MapperComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Validator;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validations.DateValidator;

public class ControllerEditComputer extends HttpServlet{
	
	private ComputerService computerService;
	private CompanyService companyService;
	
	@Override
	public void init() throws ServletException {
		
		computerService = new ComputerServiceImpl();
		companyService = new CompanyServiceImpl();
		
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Company> listeCompany = companyService.findAll();
		request.setAttribute("list", listeCompany);
		
		Long id = Long.valueOf(request.getParameter("idComputer"));
		Optional<Computer> computer= computerService.findById(id);
		request.setAttribute("id", id);
		if(computer.isPresent()){
		request.setAttribute("name", computer.get().getName());
		request.setAttribute("introduced", computer.get().getIntroduced());
		request.setAttribute("discontinued", computer.get().getDisconstinued());
		request.setAttribute("nameCompany", computer.get().getManufacturer().getName());
		}
		request.getRequestDispatcher("/editComputer.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("idComputer"));
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String disconstinued = request.getParameter("discontinued");
		Long id_company = Long.valueOf(request.getParameter("companyId")).longValue();
		
		Validator introducedValidator = DateValidator.isValidDate(introduced);
		Validator disconstinuedValidator = DateValidator.isValidDate(disconstinued);
		
		
			if (introducedValidator.isValid()&&disconstinuedValidator.isValid()){
				Validator orderDateValidator = DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced, disconstinued);
				if (orderDateValidator.isValid()){
					ComputerDto computerDto = new ComputerDto.Builder()
							 .id(id)
							 .name(name)
							 .introduced(introduced)
							 .disconstinued(disconstinued)
							 .idCompany(id_company)
							 .build();


						Computer computer = new MapperComputer().convertToComputer(computerDto);
						
						computerService.update(computer);
				} else {
					//display errors
					request.setAttribute("erreur",orderDateValidator.getError());
				}
			} else {
				request.setAttribute("erreurIntroduced", introducedValidator.getError());
				request.setAttribute("erreurDiscontinued", disconstinuedValidator.getError());
			}
	    	doGet(request, response);
		
	}
	
}
