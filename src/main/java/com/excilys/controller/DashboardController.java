package com.excilys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

@Controller
public class DashboardController {

    @Autowired
    ComputerService computerService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getAllComputer(@RequestParam(value = "recordsPerPage", required = false) String recordsPerPage,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "search", required = false) String search, Model model) {

        int pages = 1;
        int recordsPage = 50;
        long noOfRecords;
        if (search != null) {
            List<Computer> listOfComputer = computerService.findByName(search);
            noOfRecords = listOfComputer.size();
            model.addAttribute("numberOfComputers", noOfRecords);
            model.addAttribute("list", listOfComputer);
        } else {
            if (recordsPerPage != null) {
                recordsPage = Integer.parseInt(recordsPerPage);
            }
            if (page != null) {
                pages = Integer.parseInt(page);
            }
            noOfRecords = computerService.countComputer();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
            model.addAttribute("list", computerService.findAllPerPage((pages - 1) * recordsPage, recordsPage));
            model.addAttribute("currentPage", pages);
            model.addAttribute("numberOfComputers", noOfRecords);
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", pages);
            model.addAttribute("recordsPerPage", recordsPage);
        }
        return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String deleteComputer(@RequestParam(value = "selection", required = false) String selection) {

        String[] tab = selection.split(",");

        for (int i = 0; i < tab.length; i++) {
            if (isNumber(tab[i])) {
                long id = Long.valueOf(tab[i]).longValue();
                computerService.delete(id);
            }
        }
        return "dashboard";
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
