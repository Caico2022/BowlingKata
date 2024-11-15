package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void getNameNegativeNumberTest() {
        Service service = new Service();
        String result = service.getName(-1);

        assertEquals("a", result);
    }

    @Test
    void getNamePositiveNumberTest() {
        Service service = new Service();
        String result = service.getName(1);

        assertEquals("b", result);
    }

    @Test
    void getNameZeroTest() {
        Service service = new Service();
        String result = service.getName(0);

        assertEquals("b", result);
    }



}