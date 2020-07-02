package com.bcopstein;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoteiroTest {
    private List<Bairro> bairros;

    @BeforeEach
    public void setup() {
        this.bairros = new ArrayList<>();
        this.bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0));
        this.bairros.add(Bairro.novoBairroRetangular("Independecia", new Ponto(30, 40), 20, 10, 10.0));
        this.bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 10.0));
        this.bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 10.0));
        this.bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40, 20), 20, 10, 10.0));
    }

    @Test
    void testaRota() {
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Reta rotaEsp = new Reta(new Ponto(40, 35), new Ponto(50, 15));
        assertEquals(rotaEsp, roteiro.getRota());
    }

    @Test
    void testaBairrosPercorridos() {
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(1));
        esperado.add(bairros.get(2));
        esperado.add(bairros.get(3));
        esperado.add(bairros.get(4));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }

}
