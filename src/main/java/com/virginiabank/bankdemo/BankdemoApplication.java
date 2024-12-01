
package com.virginiabank.bankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BankdemoApplication
 * 
 * The start main function of FoxBank Backend spring boot.
 * This is the entry point for the Spring Boot application.
 * It contains the main method which uses Spring Boot's SpringApplication.run() method to launch the application.
 * 
 * @author 
 *    virginiatseng
 */
@SpringBootApplication
public class BankdemoApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BankdemoApplication.class, args);
    }

}
