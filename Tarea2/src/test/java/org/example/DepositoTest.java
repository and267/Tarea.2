package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositoTest {
    private Deposito<CocaCola> deposito;

    @BeforeEach
    void setUp() {

        deposito = new Deposito<CocaCola>();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Test RetirarBebida")
    public void testRetirarBebida(){

        CocaCola b = new CocaCola(101);
        deposito.addObj(b);
        assertNotNull(deposito.getObj());

    }
}