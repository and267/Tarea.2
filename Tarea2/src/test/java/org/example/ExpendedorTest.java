package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpendedorTest {

    private Expendedor expendedor;
    @BeforeEach
    void setUp() {

        expendedor = new Expendedor(3,500,500);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test Una CocaCola")
    public void testComprarUnaBebida() throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {
        System.out.println("comprarUnaCocaCola");
        Moneda pago = new Moneda1000();
        assertNotNull(expendedor.comprarProducto(pago,1));

    }
    @Test
    @DisplayName("Test Tres CocaCola")
    public void testComprarTresBebidas() throws PagoInsuficienteException, PagoIncorrectoException, NoHayProductoException {

        Moneda pago = new Moneda1000();
        assertNotNull(expendedor.comprarProducto(pago,1));
        pago = new Moneda1000();
        assertNotNull(expendedor.comprarProducto(pago,1));
        pago = new Moneda1000();
        assertNotNull(expendedor.comprarProducto(pago,1));

    }

    @Test
    @DisplayName("Test NoHayProductoException")
    public void testComprarCuatroCocaCola(){
        Moneda pago = new Moneda1000();
        Exception exception = assertThrows(NoHayProductoException.class,()->{
            expendedor.comprarProducto(pago,1);
            expendedor.comprarProducto(pago,1);
            expendedor.comprarProducto(pago,1);
            expendedor.comprarProducto(pago,1);

        });
    }

    @Test
    @DisplayName("Test PagoInsuficienteException")
    public void testComprarBebidaSinSuficienteMoneda(){
        Moneda pago = new Moneda100();
        Exception exception = assertThrows(PagoInsuficienteException.class,
                ()->{
                    expendedor.comprarProducto(pago,1);

                });
    }

}