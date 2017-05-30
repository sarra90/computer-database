package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.service.UserService;

@Controller
public class AdministrationController {
    
    @Autowired
    UserService userService;
    
    
    @RequestMapping("/listUser")
    public String getAllUser(Model model){
        model.addAttribute("list", userService.findAll());
        return "listUser";
    }
    
    @RequestMapping("/register")
    public String register(Model model){
        
        return "register";
    }

}
