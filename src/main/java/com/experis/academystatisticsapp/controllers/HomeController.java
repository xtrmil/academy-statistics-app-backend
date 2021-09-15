package com.experis.academystatisticsapp.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HomeController {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping("/claims")
    String claims() {
        return "Data: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @RequestMapping("/patient/{id}")
    ModelAndView patient(ModelMap model, HttpServletRequest request, @PathVariable String id) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");

        String client_id = "";
        int state = (int )(Math.random() * 1000000 + 1);
        String resource = "client_id_guid_of_fhir";
        String base_uri = request.getRequestURL().toString();
        base_uri = base_uri.substring(0, base_uri.length() - request.getServletPath().length());
        String redirect_uri = base_uri + "/context/authorized";

        String redirect = String.format("https://login.microsoftonline.com/common/oauth2/authorize?client_id=%s&state=%s&resource=%s&redirect_uri=%s&response_type=code&response_mode=post", client_id, state, resource, redirect_uri);
        return new ModelAndView("redirect:" + redirect, model);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/")
    public String groupOne(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_ADMIN")){
            return "Hello Admins!";
        }else{
            return "Hello nonAdmins";
        }


    }


}
