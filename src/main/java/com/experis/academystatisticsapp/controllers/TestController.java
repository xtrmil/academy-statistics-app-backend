package com.experis.academystatisticsapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class TestController {

    @GetMapping("group1")
    @ResponseBody
    @PreAuthorize("hasRole('Test')")
    public String group1() {
        return "Hello Test Users!";
    }

    @GetMapping("group2")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_group2')")
    public String group2() {
        return "Hello Group 2 Users!";
    }
}