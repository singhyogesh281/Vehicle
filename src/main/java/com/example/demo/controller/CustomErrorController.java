package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseBody
    public ResponseEntity<String> error() {
        // Customize the error response here
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Custom Not Found Error");
    }

    public String getErrorPath() {
        return PATH;
    }
}

