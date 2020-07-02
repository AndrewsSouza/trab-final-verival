package com.bcopstein.entidades.geometria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetaTest {

    @Test
    void testaTamanho() {
        Ponto p1 = new Ponto(10, 10);
        Ponto p2 = new Ponto(30, 10);
        Reta reta = new Reta(p1, p2);
        double resultado = reta.tamanho();

        assertEquals(20, resultado);
    }
}