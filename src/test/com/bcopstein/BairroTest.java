package com.bcopstein;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class BairroTest {

    @Test
    public void testaCriacaoBairroQuadrado() {
        String nomeBairro = "Ipiranga";
        Bairro b = Bairro.novoBairroQuadrado(nomeBairro, new Ponto(100, 100), 50, 20);
        assertEquals(nomeBairro, b.getNome());
        assertEquals(20.0, b.getCustoTransporte(), 0.0);
        assertEquals(new Area(new Ponto(100, 100), new Ponto(150, 50)), b.getArea());
    }

    @Test
    public void testaCriacaoBairroRetangular() {
        String nomeBairro = "Bom Fim";
        Bairro b = Bairro.novoBairroRetangular(nomeBairro, new Ponto(10, 40), 20, 10, 10);
        assertEquals(nomeBairro, b.getNome());
        assertEquals(10.0, b.getCustoTransporte(), 0.0);
        assertEquals(new Area(new Ponto(10, 40), new Ponto(30, 30)), b.getArea());
    }
}
