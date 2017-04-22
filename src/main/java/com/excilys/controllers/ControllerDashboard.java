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

public class ControllerDashboard extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerDashboard.class);
	
	@Override
	public void init() throws ServletException{
		computerService = new ComputerServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Computer> listOfComputers = computerService.findAll();
		
		request.setAttribute("numberOfComputers", (listOfComputers!=null)?listOfComputers.size():0);
		
		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		
		LOGGER.info("numberOfComputers "+listOfComputers.size());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
}
