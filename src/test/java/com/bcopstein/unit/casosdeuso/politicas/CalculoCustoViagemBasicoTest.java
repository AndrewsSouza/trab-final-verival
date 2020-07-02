package com.bcopstein.unit.casosdeuso.politicas;

import com.bcopstein.unit.entidades.Bairro;
import com.bcopstein.unit.entidades.Roteiro;
import com.bcopstein.unit.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculoCustoViagemBasicoTest {
    private Collection<Bairro> bairros;
    private CalculoCustoViagemBasico alvo;

    @BeforeEach
    public void setup() {
        this.alvo = new CalculoCustoViagemBasico();
        this.bairros = Arrays.asList(
                Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Independecia", new Ponto(30, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Boa Vista", new Ponto(40, 20), 20, 10, 10.0)
        );
    }

    @Test
    void testaCalculoCustoBasico() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);
        double custoEsperado = 50.0;

        this.alvo.defineRoteiro(rt);

        double custoObservado = this.alvo.calculoCustoBasico();

        assertEquals(custoEsperado, custoObservado);
    }

    @Test
    void testaCustoViagem() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);
        double custoBasicoEsperado = 50.0;
        double custoEsperado = custoBasicoEsperado - this.alvo.descontoPontuacao() - this.alvo.descontoPromocaoSazonal();
        this.alvo.defineRoteiro(rt);

        double custoObservado = this.alvo.custoViagem();

        assertEquals(custoEsperado, custoObservado);
    }
}