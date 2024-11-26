package com.virginiabank.bankdemo;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.virginiabank.bankdemo.service.DependencyService;
import com.virginiabank.bankdemo.service.ExampleService;

public class ExampleServiceWithDependencyTest {

    @Mock
    private DependencyService dependencyService;

    @InjectMocks
    private ExampleService exampleService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this); // 初始化 @Mock 和 @InjectMocks
//    }
    
    @Test
    public void testGetGreeting() {
        MockitoAnnotations.openMocks(this);

        when(dependencyService.getGreeting()).thenReturn("Hello, Mock!");
        String result = exampleService.getGreeting();
        assertEquals("Hello, Mock!", result);
    }
}
