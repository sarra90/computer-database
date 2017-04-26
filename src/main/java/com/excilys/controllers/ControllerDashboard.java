package com.excilys.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.ComputerServiceImpl;

public class ControllerDashboard extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerDashboard.class);

	@Override
	public void init() throws ServletException {
		computerService = new ComputerServiceImpl();
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchName = request.getParameter("search");
		
		
		if(searchName!=null){
			
			List<Computer> listOfComputersByName = computerService.findByName(searchName);
			request.setAttribute("list", listOfComputersByName);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
		else{
			
			int page = 1;
			int recordsPage =50;
			
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if (request.getParameter("recordsPerPage") != null) {
				recordsPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			}
			List<Computer> listOfComputersPerPage = computerService.findAllPerPage((page - 1) * recordsPage,recordsPage);
			
			long noOfRecords = computerService.countComputer();
			
			
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
			
			
			request.setAttribute("list", listOfComputersPerPage);
			
			request.setAttribute("numberOfComputers", noOfRecords);

			request.setAttribute("noOfPages", noOfPages);
			
			request.setAttribute("currentPage", page);

			request.setAttribute("recordsPerPage", recordsPage);
			
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
