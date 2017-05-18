package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.ComputerService;

@Controller
public class DeleteComputerController {
    
    @Autowired
    ComputerService computerService;

    public String deleteComputer(@RequestParam(value = "selection", required = false) String selection) {

        return "dashboard";
    }

}
