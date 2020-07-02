package com.bcopstein.integration;

import com.bcopstein.casosdeuso.politicas.CalculoCustoViagem;
import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemRelampago;
import com.bcopstein.casosdeuso.politicas.CalculoCustoViagemVerao;
import com.bcopstein.casosdeuso.repositorios.RepositorioBairros;
import com.bcopstein.casosdeuso.repositorios.RepositorioPassageiros;
import com.bcopstein.casosdeuso.servicos.ServicosPassageiro;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.interfaces.persistencia.RepositorioBairrosImplMem;
import com.bcopstein.interfaces.persistencia.RepositorioPassageirosImplMem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicosPassageiroIntegrationTest {

    @Test
    void testeIntegracaoCustoBasico(){
        RepositorioBairros repBairros = new RepositorioBairrosImplMem();
        RepositorioPassageiros repPassageiros = new RepositorioPassageirosImplMem();
        CalculoCustoViagem ccv = new CalculoCustoViagemBasico();
        ServicosPassageiro alvo = new ServicosPassageiro(repBairros, repPassageiros, ccv);

        Bairro bOrigem = repBairros.recuperaListaBairros().get(0);
        Bairro bDestino = repBairros.recuperaListaBairros().get(1);
        Roteiro roteiro = alvo.criaRoteiro(bOrigem.getNome(), bDestino.getNome());
        Passageiro passageiro = repPassageiros.listaPassageiros().get(0);

        Viagem viagem = alvo.criaViagem(0, roteiro, passageiro.getCPF());

        List<String> bairros = alvo.getListaBairros();
        List<String> bairrosEsperado = repBairros.recuperaListaBairros().stream().map(Bairro::getNome).collect(Collectors.toList());
        List<String> passageiros = alvo.getPassageirosCadastrados();
        List<String> passageirosEsperado = repPassageiros.listaPassageiros().stream().map(Passageiro::getNome).collect(Collectors.toList());

        assertEquals(bOrigem, roteiro.getBairroOrigem());
        assertEquals(bDestino,roteiro.getBairroDestino());
        assertEquals(passageiro.getCPF(), viagem.getPassageiro().getCPF());
        assertEquals(roteiro, viagem.getRoteiro());
        assertArrayEquals(bairrosEsperado.toArray(), bairros.toArray());
        assertArrayEquals(passageirosEsperado.toArray(), passageiros.toArray());
    }

    @Test
    void testeIntegracaoCustoBRelampago(){
        RepositorioBairros repBairros = new RepositorioBairrosImplMem();
        RepositorioPassageiros repPassageiros = new RepositorioPassageirosImplMem();
        CalculoCustoViagem ccv = new CalculoCustoViagemRelampago();
        ServicosPassageiro alvo = new ServicosPassageiro(repBairros, repPassageiros, ccv);

        Bairro bOrigem = repBairros.recuperaListaBairros().get(0);
        Bairro bDestino = repBairros.recuperaListaBairros().get(1);
        Roteiro roteiro = alvo.criaRoteiro(bOrigem.getNome(), bDestino.getNome());
        Passageiro passageiro = repPassageiros.listaPassageiros().get(0);

        Viagem viagem = alvo.criaViagem(0, roteiro, passageiro.getCPF());

        List<String> bairros = alvo.getListaBairros();
        List<String> bairrosEsperado = repBairros.recuperaListaBairros().stream().map(Bairro::getNome).collect(Collectors.toList());
        List<String> passageiros = alvo.getPassageirosCadastrados();
        List<String> passageirosEsperado = repPassageiros.listaPassageiros().stream().map(Passageiro::getNome).collect(Collectors.toList());

        assertEquals(bOrigem, roteiro.getBairroOrigem());
        assertEquals(bDestino,roteiro.getBairroDestino());
        assertEquals(passageiro.getCPF(), viagem.getPassageiro().getCPF());
        assertEquals(roteiro, viagem.getRoteiro());
        assertArrayEquals(bairrosEsperado.toArray(), bairros.toArray());
        assertArrayEquals(passageirosEsperado.toArray(), passageiros.toArray());
    }

    @Test
    void testeIntegracaoCustoVerao(){
        RepositorioBairros repBairros = new RepositorioBairrosImplMem();
        RepositorioPassageiros repPassageiros = new RepositorioPassageirosImplMem();
        CalculoCustoViagem ccv = new CalculoCustoViagemVerao();
        ServicosPassageiro alvo = new ServicosPassageiro(repBairros, repPassageiros, ccv);

        Bairro bOrigem = repBairros.recuperaListaBairros().get(0);
        Bairro bDestino = repBairros.recuperaListaBairros().get(1);
        Roteiro roteiro = alvo.criaRoteiro(bOrigem.getNome(), bDestino.getNome());
        Passageiro passageiro = repPassageiros.listaPassageiros().get(0);

        Viagem viagem = alvo.criaViagem(0, roteiro, passageiro.getCPF());

        List<String> bairros = alvo.getListaBairros();
        List<String> bairrosEsperado = repBairros.recuperaListaBairros().stream().map(Bairro::getNome).collect(Collectors.toList());
        List<String> passageiros = alvo.getPassageirosCadastrados();
        List<String> passageirosEsperado = repPassageiros.listaPassageiros().stream().map(Passageiro::getNome).collect(Collectors.toList());

        assertEquals(bOrigem, roteiro.getBairroOrigem());
        assertEquals(bDestino,roteiro.getBairroDestino());
        assertEquals(passageiro.getCPF(), viagem.getPassageiro().getCPF());
        assertEquals(roteiro, viagem.getRoteiro());
        assertArrayEquals(bairrosEsperado.toArray(), bairros.toArray());
        assertArrayEquals(passageirosEsperado.toArray(), passageiros.toArray());
    }
}
