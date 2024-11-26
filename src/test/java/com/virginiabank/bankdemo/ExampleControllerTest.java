package com.virginiabank.bankdemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.virginiabank.bankdemo.controller.ExampleController;

@WebMvcTest(ExampleController.class)
public class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHello() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk());
        
//        mockMvc.perform(get("/api/hello"))
//        .andExpect(status().isOk())
//        .andExpect(content().string("Hello, World!"));
    }
}
