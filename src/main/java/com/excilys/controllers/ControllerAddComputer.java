package com.excilys.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dtos.ComputerDto;
import com.excilys.exceptions.DiscontinuedDateException;
import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;

public class ControllerAddComputer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
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
		
		String erreur;
		
		ComputerDto computerdto;

		String name = request.getParameter("name");

		String introduced = request.getParameter("introduced");

		String disconstinued = request.getParameter("disconstinued");
		
		

		try {
			validationDate(introduced, disconstinued);
			computerdto = new ComputerDto.Builder()
					 .introduced(introduced)
					 .disconstinued(disconstinued)
					 .build();
		} catch (DiscontinuedDateException e) {
			erreur = e.getMessage();
		}
		
	}

	private void validationDate(String introduced, String disconstinued) throws DiscontinuedDateException {
		
		LocalDate dateintroduced = LocalDate.parse(introduced);
		LocalDate datedisconstinued = LocalDate.parse(disconstinued);
		
		if (datedisconstinued.isBefore(dateintroduced)){
			throw new DiscontinuedDateException();
		}
	}
}




















