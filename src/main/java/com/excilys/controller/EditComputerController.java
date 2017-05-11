package com.excilys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class EditComputerController extends HttpServlet {

    private ComputerService computerService;
    private CompanyService companyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputerController.class);

    @Override
    public void init() throws ServletException {

        computerService = new ComputerServiceImpl();
        companyService = new CompanyServiceImpl();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Company> listeCompany = companyService.findAll();
        Long id;
        request.setAttribute("list", listeCompany);

        String idComputer = request.getParameter("id");

        if (isNumber(idComputer)) {
            LOGGER.info("EDIT GET1 id computer " + idComputer);
            id = Long.valueOf(idComputer);
            LOGGER.info("EDIT GET2 id computer " + id);
            Optional<Computer> computer = computerService.findById(id);
            request.setAttribute("idComputer", id);
            if (computer.isPresent()) {
                request.setAttribute("name", computer.get().getName());
                request.setAttribute("introduced", computer.get().getIntroduced());
                request.setAttribute("discontinued", computer.get().getDisconstinued());
                if (computer.get().getManufacturer() != null) {
                    request.setAttribute("nameCompany", computer.get().getManufacturer().getName());
                }

            }
        }
        request.getRequestDispatcher("/editComputer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idcomputer = request.getParameter("id");

        String name = request.getParameter("name");
        String introduced = request.getParameter("introduced");
        String disconstinued = request.getParameter("discontinued");
        Long idCompany = Long.valueOf(request.getParameter("companyId")).longValue();

        Validator introducedValidator = DateValidator.isValidDate(introduced);
        Validator disconstinuedValidator = DateValidator.isValidDate(disconstinued);
        LOGGER.info("EDIT id computer " + idcomputer);
        if (isNumber(idcomputer)) {
            long id = Long.valueOf(idcomputer).longValue();
            LOGGER.info("if is long");
            if (introducedValidator.isValid() && disconstinuedValidator.isValid()) {
                Validator orderDateValidator = DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced,
                        disconstinued);
                LOGGER.info("if introducedValidator disconstinuedValidator ");
                if (orderDateValidator.isValid()) {
                    LOGGER.info("if orderDateValidator");
                    ComputerDto computerDto = new ComputerDto.Builder().id(id).name(name).introduced(introduced)
                            .disconstinued(disconstinued).idCompany(idCompany).build();

                    Computer computer = new MapperComputer().convertToComputer(computerDto);

                    computerService.update(computer);

                } else {
                    // display errors
                    request.setAttribute("erreur", orderDateValidator.getError());
                }
            } else {
                request.setAttribute("erreurIntroduced", introducedValidator.getError());
                request.setAttribute("erreurDiscontinued", disconstinuedValidator.getError());
            }
        }
        doGet(request, response);

    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
