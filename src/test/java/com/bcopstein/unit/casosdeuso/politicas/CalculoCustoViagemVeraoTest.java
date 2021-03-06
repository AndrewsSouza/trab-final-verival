package com.bcopstein.unit.casosdeuso.politicas;

import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemVerao;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculoCustoViagemVeraoTest {
    private CalculoCustoViagemVerao alvo;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup() {
        this.alvo = new CalculoCustoViagemVerao();
        this.bairros = Arrays.asList(
                Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Independecia", new Ponto(30, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 10.0)
        );
    }

    @Test
    void testaComDescontoDePontuacao() {
        Passageiro psg = mock(Passageiro.class);
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        when(psg.getPontuacaoMedia()).thenReturn(10);

        double custoBasicoEsperado = 30.0;
        double descontoEsperado = custoBasicoEsperado * 0.09;

        this.alvo.defineRoteiro(rt);
        this.alvo.definePassageiro(psg);

        double observado = this.alvo.descontoPontuacao();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void testaSemDescontoDePontuacaoPorMediaBaixa() {
        Passageiro psg = mock(Passageiro.class);
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        when(psg.getPontuacaoMedia()).thenReturn(9);

        double descontoEsperado = 0;

        this.alvo.defineRoteiro(rt);
        this.alvo.definePassageiro(psg);

        double observado = this.alvo.descontoPontuacao();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void testaComDescontoSazonal() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        double custoBasicoEsperado = 30.0;
        double descontoEsperado = custoBasicoEsperado * 0.1;

        this.alvo.defineRoteiro(rt);

        double observado = this.alvo.descontoPromocaoSazonal();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void testaSemDescontoSazonal() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(Arrays.asList(this.bairros.get(0), this.bairros.get(1)));

        double descontoEsperado = 0;

        this.alvo.defineRoteiro(rt);

        double observado = this.alvo.descontoPromocaoSazonal();

        assertEquals(descontoEsperado, observado);
    }
}