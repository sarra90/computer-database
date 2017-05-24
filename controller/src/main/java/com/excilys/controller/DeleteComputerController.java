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

        String[] tabSelection = selection.split(",");

        for (int i = 0; i < tabSelection.length; i++) {
            if (isNumber(tabSelection[i])) {
               computerService.delete(Long.parseLong((tabSelection[i])));
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
