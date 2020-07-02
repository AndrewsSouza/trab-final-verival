package com.bcopstein.unit.entidades.geometria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

class AreaTest {
    private Area area;

    @BeforeEach
    public void setup() {
        area = new Area(new Ponto(10, 50), new Ponto(60, 10));

    }

    @Test
    void testaPontoCentral() {
        Ponto p = area.pontoCentral();
        assertEquals(35, p.getX());
        assertEquals(30, p.getY());
    }

    @ParameterizedTest
    @CsvSource({"20,55,1", "65,15,4", "15,5,2", "5,15,8"})
    //falta os testes dos limites
    void testaClassificaPonto(int x, int y, int classEsp) {
        int rObs = area.codificaPonto(new Ponto(x, y));
        assertEquals(classEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({"20,35,30,35,0", "55,20,65,20,2", "65,20,75,20,1", "5,20,35,60,2"})
    void testaClassificaReta(int x1, int y1, int x2, int y2, int classEsp) {
        SituacaoReta classObs = area.classifica(new Reta(new Ponto(x1, y1), new Ponto(x2, y2)));
        assertEquals(classEsp, classObs.ordinal());
    }
}
