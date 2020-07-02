package com.bcopstein.unit.casosdeuso.servicos;

import com.bcopstein.casosdeuso.politicas.CalculoCustoViagem;
import com.bcopstein.casosdeuso.repositorios.RepositorioBairros;
import com.bcopstein.casosdeuso.repositorios.RepositorioPassageiros;
import com.bcopstein.casosdeuso.servicos.ServicosPassageiro;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServicosPassageiroTest {
    private RepositorioBairros repBairros;
    private RepositorioPassageiros repPassageiros;
    private CalculoCustoViagem calculoCustoViagem;
    private ServicosPassageiro alvo;

    @BeforeEach
    void setup() {
        this.repBairros = mock(RepositorioBairros.class);
        this.repPassageiros = mock(RepositorioPassageiros.class);
        this.calculoCustoViagem = mock(CalculoCustoViagem.class);

        this.alvo = new ServicosPassageiro(repBairros, repPassageiros, calculoCustoViagem);
    }

    @Test
    void testaGetListaBairros() {
        Bairro b1 = mock(Bairro.class);
        String b1Name = "Bairro Teste";
        when(b1.getNome()).thenReturn(b1Name);
        when(repBairros.recuperaListaBairros()).thenReturn(Arrays.asList(b1));

        List<String> nomeBairrosObservado = this.alvo.getListaBairros();

        assertTrue(nomeBairrosObservado.contains(b1.getNome()));
    }

    @Test
    void testaGetPassageirosCadastrados() {
        Passageiro p1 = mock(Passageiro.class);
        String p1Name = "Passageiro Teste";
        when(p1.getNome()).thenReturn(p1Name);
        when(repPassageiros.listaPassageiros()).thenReturn(Arrays.asList(p1));

        List<String> nomeBairrosObservado = this.alvo.getPassageirosCadastrados();

        assertTrue(nomeBairrosObservado.contains(p1.getNome()));
    }

    @Test
    void testaCriaRoteiro() {
        Area area1 = mock(Area.class);
        Area area2 = mock(Area.class);
        when(area1.pontoCentral()).thenReturn(new Ponto(15, 30));
        when(area2.pontoCentral()).thenReturn(new Ponto(8, 10));

        Bairro b1 = mock(Bairro.class);
        when(b1.getArea()).thenReturn(area1);
        Bairro b2 = mock(Bairro.class);
        when(b2.getArea()).thenReturn(area2);

        String b1Name = "Bairro Teste 1";
        String b2Name = "Bairro Teste 2";
        List<Bairro> todosBairros = Arrays.asList(b1, b2);

        when(repBairros.recuperaListaBairros()).thenReturn(todosBairros);
        when(repBairros.recuperaPorNome(b1Name)).thenReturn(b1);
        when(repBairros.recuperaPorNome(b2Name)).thenReturn(b2);

        Roteiro roteiroObservado = this.alvo.criaRoteiro(b1Name, b2Name);

        assertEquals(b1, roteiroObservado.getBairroOrigem());
        assertEquals(b2, roteiroObservado.getBairroDestino());
    }

    @Test
    void testaCriaViagem() {
        int id = 1;
        String cpfPassageiro = "00000000001";
        Roteiro roteiro = mock(Roteiro.class);
        Passageiro psg = mock(Passageiro.class);
        double valor = 15;

        Viagem viagemEsperado = new Viagem(id, LocalDateTime.now(), roteiro, psg, valor);

        when(this.repPassageiros.recuperaPorCPF(cpfPassageiro)).thenReturn(psg);
        when(this.calculoCustoViagem.custoViagem()).thenReturn(valor);

        this.alvo = new ServicosPassageiro(repBairros, repPassageiros, calculoCustoViagem);

        Viagem viagemObservado = this.alvo.criaViagem(id, roteiro, cpfPassageiro);

        assertEquals(viagemEsperado.getId(), viagemObservado.getId());
        assertEquals(viagemEsperado.getRoteiro(), viagemObservado.getRoteiro());
        assertEquals(viagemEsperado.getPassageiro(), viagemObservado.getPassageiro());
        assertEquals(viagemEsperado.getValorCobrado(), viagemObservado.getValorCobrado());
    }
}