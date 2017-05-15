package com.excilys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Validator;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validations.DateValidator;

public class AddComputerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @Autowired
    private ComputerServiceImpl computerService;
    @Autowired
    private CompanyServiceImpl companyService;
    

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
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

        ComputerDto computerdto;

        // request parameters
        String name = request.getParameter("name");
        String introduced = request.getParameter("introduced");
        String disconstinued = request.getParameter("discontinued");
        long idCompany = Long.valueOf(request.getParameter("companyId")).longValue();

        // request parameters validation
        // TODO add name validators
        Validator introducedValidator = DateValidator.isValidDate(introduced);
        Validator disconstinuedValidator = DateValidator.isValidDate(disconstinued);

        if (introducedValidator.isValid() && disconstinuedValidator.isValid()) {
            Validator orderDateValidator = DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced,
                    disconstinued);
            if (orderDateValidator.isValid()) {
                computerdto = new ComputerDto.Builder().name(name).introduced(introduced).disconstinued(disconstinued)
                        .idCompany(idCompany).build();
                Computer computer = new MapperComputer().convertToComputer(computerdto);
                computerService.create(computer);

            } else {

                request.setAttribute("erreur", orderDateValidator.getError());
            }
        } else {
            request.setAttribute("erreurIntroduced", introducedValidator.getError());
            request.setAttribute("erreurDiscontinued", disconstinuedValidator.getError());
        }

        doGet(request, response);

    }

}
