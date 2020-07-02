package com.bcopstein.unit.casosdeuso.politicas;

import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemRelampago;
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

class CalculoCustoViagemRelampagoTest {
    private CalculoCustoViagemRelampago alvo;
    private List<Bairro> bairros;

    @BeforeEach
    public void setup() {
        this.alvo = new CalculoCustoViagemRelampago();
        this.bairros = Arrays.asList(
                Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Independecia", new Ponto(30, 40), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 10.0),
                Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 10.0)
        );
    }

    @Test
    void comDescontoDePontuacao() {
        Passageiro psg = mock(Passageiro.class);
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        when(psg.getPontuacaoMedia()).thenReturn(6);
        when(psg.getQtdadeAvaliacoes()).thenReturn(31);

        double custoBasicoEsperado = 40.0;
        double descontoEsperado = custoBasicoEsperado * 0.05;

        this.alvo.defineRoteiro(rt);
        this.alvo.definePassageiro(psg);

        double observado = this.alvo.descontoPontuacao();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void semDescontoDePontuacaoPorFaltaAvaliacoes() {
        Passageiro psg = mock(Passageiro.class);
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        when(psg.getPontuacaoMedia()).thenReturn(6);
        when(psg.getQtdadeAvaliacoes()).thenReturn(30);

        double descontoEsperado = 0;

        this.alvo.defineRoteiro(rt);
        this.alvo.definePassageiro(psg);

        double observado = this.alvo.descontoPontuacao();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void semDescontoDePontuacaoPorMediaBaixa() {
        Passageiro psg = mock(Passageiro.class);
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        when(psg.getPontuacaoMedia()).thenReturn(5);
        when(psg.getQtdadeAvaliacoes()).thenReturn(31);

        double descontoEsperado = 0;

        this.alvo.defineRoteiro(rt);
        this.alvo.definePassageiro(psg);

        double observado = this.alvo.descontoPontuacao();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void comDescontoSazonal() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(bairros);

        double custoBasicoEsperado = 40.0;
        double descontoEsperado = custoBasicoEsperado * 0.05;

        this.alvo.defineRoteiro(rt);

        double observado = this.alvo.descontoPromocaoSazonal();

        assertEquals(descontoEsperado, observado);
    }

    @Test
    void semDescontoSazonal() {
        Roteiro rt = mock(Roteiro.class);
        when(rt.bairrosPercoridos()).thenReturn(Arrays.asList(this.bairros.get(0), this.bairros.get(1), this.bairros.get(2)));

        double descontoEsperado = 0;

        this.alvo.defineRoteiro(rt);

        double observado = this.alvo.descontoPromocaoSazonal();

        assertEquals(descontoEsperado, observado);
    }
}