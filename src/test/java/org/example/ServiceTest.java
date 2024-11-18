package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void getNamePositiveNumber() {
        Service service = new Service();
        String result = service.getName(5);
        assertEquals("b", result);
    }
}