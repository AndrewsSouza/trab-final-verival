package com.bcopstein;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup() {
        area = new Area(new Ponto(10, 50), new Ponto(60, 10));

    }

    @Test
    public void testaPontoCentral() {
        Ponto p = area.pontoCentral();
        assertEquals(35, p.getX());
        assertEquals(30, p.getY());
    }

    @ParameterizedTest
    @CsvSource({"20,55,1", "65,15,4", "15,5,2", "5,15,8"})
    //falta os testes dos limites
    public void testaClassificaPonto(int x, int y, int classEsp) {
        int rObs = area.codificaPonto(new Ponto(x, y));
        assertEquals(classEsp, rObs);
    }

    @ParameterizedTest
    @CsvSource({"20,35,30,35,0", "55,20,65,20,2", "65,20,75,20,1", "5,20,35,60,2"})
    public void testaClassificaReta(int x1, int y1, int x2, int y2, int classEsp) {
        SituacaoReta classObs = area.classifica(new Reta(new Ponto(x1, y1), new Ponto(x2, y2)));
        assertEquals(classEsp, classObs.ordinal());
    }
}
