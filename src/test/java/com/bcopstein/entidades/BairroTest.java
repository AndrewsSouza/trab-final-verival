package com.bcopstein.entidades;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class BairroTest {

    @Test
    void testaCriacaoBairroQuadrado() {
        String nomeBairro = "Ipiranga";
        Bairro b = Bairro.novoBairroQuadrado(nomeBairro, new Ponto(100, 100), 50, 20);
        assertEquals(nomeBairro, b.getNome());
        assertEquals(20.0, b.getCustoTransporte(), 0.0);
        assertEquals(new Area(new Ponto(100, 100), new Ponto(150, 50)), b.getArea());
    }

    @Test
    void testaCriacaoBairroRetangular() {
        String nomeBairro = "Bom Fim";
        Bairro b = Bairro.novoBairroRetangular(nomeBairro, new Ponto(10, 40), 20, 10, 10);
        assertEquals(nomeBairro, b.getNome());
        assertEquals(10.0, b.getCustoTransporte(), 0.0);
        assertEquals(new Area(new Ponto(10, 40), new Ponto(30, 30)), b.getArea());
    }

    @Test
    void testaAlterarCustoTransporte() {
        Ponto p = mock(Ponto.class);

        Bairro bairro = Bairro.novoBairroQuadrado("Teste", p, 10, 10);
        double novoValor = 30;
        bairro.alteraCustoTransporte(novoValor);

        assertEquals(novoValor, bairro.getCustoTransporte(), 0.0001);
    }

    @Test
    void testaAlterarCustoTransporteComExcecao() {
        Ponto p = mock(Ponto.class);

        Bairro bairro = Bairro.novoBairroQuadrado("Teste", p, 10, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            bairro.alteraCustoTransporte(-1);
        });
    }
}
