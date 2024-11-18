package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        this.service = new Service();
    }

    @Test
    void getNamePositiveNumber() {
        String result = service.getName(5);
        assertEquals("b", result);
    }

    @Test
    void getNameNegativeNumber() {
        String result = service.getName(-1);
        assertEquals("a", result);
    }

    @Test
    void getNameZero() {
        String result = service.getName(0);
        assertEquals("b", result);
    }
}