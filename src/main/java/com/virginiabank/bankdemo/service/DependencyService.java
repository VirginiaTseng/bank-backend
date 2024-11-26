package com.virginiabank.bankdemo.service;

import org.springframework.stereotype.Service;

@Service
public class DependencyService {

    public String getGreeting() {
        return "Hello, World!";
    }
}