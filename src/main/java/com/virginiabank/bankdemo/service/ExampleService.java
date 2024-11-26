package com.virginiabank.bankdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
    
    @Autowired
    private DependencyService dependencyService;

    public String getGreeting() {
        return dependencyService.getGreeting();
    }
}
