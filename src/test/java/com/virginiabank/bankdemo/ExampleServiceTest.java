package com.virginiabank.bankdemo;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.virginiabank.bankdemo.service.ExampleService;

@SpringBootTest
public class ExampleServiceTest {

    @Autowired
    private ExampleService exampleService;

    @Test
    public void testAddition() {
        int result = exampleService.add(2, 3);
        Assert.assertEquals("2 + 3 should equal 5", 5, result);
    }
}
