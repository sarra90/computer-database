package com.excilys.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.ComputerServiceImpl;

public class ControllerDashboard extends HttpServlet{
	
	private ComputerService computerService;

	private void ini() {
		computerService = new ComputerServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		List<Computer> listOfComputers = computerService.findAll();
		request.setAttribute("numberOfComputers", listOfComputers.size());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
}
