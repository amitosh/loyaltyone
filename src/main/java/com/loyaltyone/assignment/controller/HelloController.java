package com.loyaltyone.assignment.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/echo")
    public String handleEcho() {
        return "echoForm";
    }

    @GetMapping("/saveandecho")
    public String handleSaveAndEcho() {
        return "saveAndEchoForm";
    }

}