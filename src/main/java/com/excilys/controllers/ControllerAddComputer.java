package com.excilys.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dtos.ComputerDto;
import com.excilys.exceptions.DiscontinuedDateException;
import com.excilys.mappers.MapperComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Validator;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validations.DateValidator;


public class ControllerAddComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAddComputer.class);

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
		request.getRequestDispatcher("/addComputer.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String erreur = "";
		
		ComputerDto computerdto;

		String name = request.getParameter("name");

		String introduced = request.getParameter("introduced");

		String disconstinued = request.getParameter("discontinued");
		
		Long id_company = Long.valueOf(request.getParameter("companyId")).longValue();
		
			request.setAttribute("erreurIntroduced", DateValidator.isValidDate(introduced).getError());
			
			request.setAttribute("erreurDiscontinued", DateValidator.isValidDate(disconstinued).getError());
			
			computerdto = new ComputerDto.Builder()
					.name(name)
					.introduced(introduced)
					.disconstinued(disconstinued)
					.idCompany(id_company)
					.build();
			
			Computer computer = new MapperComputer().convertToComputer(computerdto);
			
			computerService.create(computer);

		
			request.setAttribute("erreur", DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced, disconstinued));
			
		
		doGet(request,response);
		
	}

}




















