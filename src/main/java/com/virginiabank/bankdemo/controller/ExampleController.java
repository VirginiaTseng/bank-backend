package com.virginiabank.bankdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virginiabank.bankdemo.service.ExampleService;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

//    @GetMapping("/add")
//    public int hello(@RequestParam int a, @RequestParam int b) {
//        return exampleService.add(a, b);
//    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }
    
    // 加法 API
    @GetMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return exampleService.add(a, b);
    }

    // 减法 API
    @GetMapping("/subtract")
    public int subtract(@RequestParam int a, @RequestParam int b) {
        return exampleService.subtract(a, b);
    }
}
