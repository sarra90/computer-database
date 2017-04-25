package com.excilys.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;

public class ControllerEditComputer extends HttpServlet{
	
	private ComputerService computerService;
	private CompanyService companyService;
	
	@Override
	public void init() throws ServletException {
		
		computerService = new ComputerServiceImpl();
		companyService = new CompanyServiceImpl();
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
}
