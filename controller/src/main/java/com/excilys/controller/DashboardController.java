package com.excilys.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.ComputerService;

@Controller
public class DashboardController {

    @Autowired
    ComputerService computerService;

    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getAllComputer(Locale locale,
            @RequestParam(value = "recordsPerPage", required = false) String recordsPerPage,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "search", required = false) String search, Model model) {

        int pages = 1;
        int recordsPage = 50;
        
        long noOfRecords;
        int noOfPages;
        
        if (recordsPerPage != null) {
            recordsPage = Integer.parseInt(recordsPerPage);
        }
        if (page != null) {
            pages = Integer.parseInt(page);
        }
        
        
        if(search != null){
        noOfRecords = computerService.countComputerByName(search);
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
            PageRequest pageRequest = new PageRequest(pages, recordsPage);
         model.addAttribute("list", computerService.findByName(search,pageRequest));
        }
        else{
         noOfRecords = computerService.countComputer();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
            PageRequest pageRequest = new PageRequest(pages, recordsPage);
         model.addAttribute("list", computerService.findAllPerPages(pageRequest));
        }
       
        model.addAttribute("currentPage", pages);
        model.addAttribute("numberOfComputers", noOfRecords);
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("currentPage", pages);
        model.addAttribute("recordsPerPage", recordsPage);
        model.addAttribute("search", search);
        return "dashboard";
    }

   
}
/*      int pages = 1;
        int recordsPage = 50;
        
        long noOfRecords;
        int noOfPages;
        
        if (recordsPerPage != null) {
            recordsPage = Integer.parseInt(recordsPerPage);
        }
        if (page != null) {
            pages = Integer.parseInt(page);
        }
        
        
        if(search != null){
        noOfRecords = computerService.countComputerByName(search);
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
            PageRequest pageRequest = new PageRequest(pages, recordsPage);
         model.addAttribute("list", computerService.findByName(search,pageRequest));
        }
        else{
         noOfRecords = computerService.countComputer();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPage);
            PageRequest pageRequest = new PageRequest(pages, recordsPage);
         model.addAttribute("list", computerService.findAllPerPages(pageRequest));
        }
       
        model.addAttribute("currentPage", pages);
        model.addAttribute("numberOfComputers", noOfRecords);
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("currentPage", pages);
        model.addAttribute("recordsPerPage", recordsPage);
        
 */











