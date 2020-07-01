package com.bcopstein.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassageiroTest {

    @Test
    public void testaGetPontuacaoMedia() {
        int ptAcumulada = 50;
        int qtdAvaliacoes = 10;
        Passageiro psg = Passageiro.passageiroExistente("cpf", "Npme Teste", ptAcumulada, qtdAvaliacoes);
        double mediaEsperada = 50.0 / 10.0;

        assertEquals(mediaEsperada, psg.getPontuacaoMedia());
    }

    @Test
    public void testaInfoPontuacao() {
        int ptAcumulada = 0;
        int qtdAvaliacoes = 0;
        int novaAvaliacao = 10;

        Passageiro psg = Passageiro.passageiroExistente("cpf", "Npme Teste", ptAcumulada, qtdAvaliacoes);
        psg.infoPontuacao(novaAvaliacao);

        assertEquals(qtdAvaliacoes + 1, psg.getQtdadeAvaliacoes());
        assertEquals(ptAcumulada + novaAvaliacao, psg.getPontuacaoAcumulada());
    }

    @Test
    public void testaInfoPontuacaoComExcecao() {
        int ptAcumulada = 0;
        int qtdAvaliacoes = 0;
        int novaAvaliacao = -1;

        Passageiro psg = Passageiro.passageiroExistente("cpf", "Nome Teste", ptAcumulada, qtdAvaliacoes);

        assertThrows(IllegalArgumentException.class, () -> {
            psg.infoPontuacao(novaAvaliacao);
        });
    }
}