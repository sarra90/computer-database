package com.excilys.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.ComputerDto;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

    @Controller
    public class EditComputerController {

        @Autowired
        private ComputerService computerService;
        
        @Autowired
        private CompanyService companyService;

        @Autowired
        MapperComputer mapperComputer;
        
        @RequestMapping(value = "/editComputer", method = RequestMethod.GET)
        public String editComputer(@RequestParam(value = "id") String id, Model model) {

            model.addAttribute("list", companyService.findAll());
            if (isNumber(id)) {
                long idComputer = Long.valueOf(id);
                Computer computer = computerService.findById(idComputer);
                model.addAttribute("idComputer", idComputer);
                
                    model.addAttribute("name", computer.getName());
                    model.addAttribute("introduced", computer.getIntroduced());
                    model.addAttribute("discontinued", computer.getDisconstinued());
                    if (computer.getCompany() != null) {
                        model.addAttribute("nameCompany", computer.getCompany().getName());
                    }

                
            }
            return "editComputer";
        }
        @ModelAttribute("computerDto")
        public ComputerDto createComputerDto() {
            ComputerDto computerDto = new ComputerDto.Builder().build();
          return computerDto;
        }
        
        @RequestMapping(value = "/editComputer", method = RequestMethod.POST)
        public String addComputer(@Valid @ModelAttribute("computerDto") ComputerDto computerDto, BindingResult bindingResult, Model model) {
            model.addAttribute("newComputerDto", computerDto);
            Computer computer = mapperComputer.convertToComputer(computerDto);
            computerService.create(computer);
            if (bindingResult.hasErrors()) {
                return "redirect:/editcomputer";
            }
            return "redirect:/dashboard";
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
