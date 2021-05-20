/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Root
 * відповідає за маршрутизацію login та logout
 */
@Controller
public class MainController {
    
     @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
 
        return "redirect:/login";
    }
   
   
    
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "loginPage";
    }
     @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403(Model model) {
        return "403";
    }

    
}

