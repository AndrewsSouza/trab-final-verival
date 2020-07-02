package com.bcopstein.unit.casosdeuso.servicos;

import com.bcopstein.unit.casosdeuso.politicas.CalculoCustoViagem;
import com.bcopstein.unit.casosdeuso.politicas.CustoViagem;
import com.bcopstein.unit.casosdeuso.repositorios.RepositorioBairros;
import com.bcopstein.unit.casosdeuso.repositorios.RepositorioPassageiros;
import com.bcopstein.unit.entidades.Bairro;
import com.bcopstein.unit.entidades.Passageiro;
import com.bcopstein.unit.entidades.Roteiro;
import com.bcopstein.unit.entidades.Viagem;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ServicosPassageiro {
    private RepositorioBairros repBairros;
    private RepositorioPassageiros repPassageiros;
    private CustoViagem custoViagem;

    public ServicosPassageiro(RepositorioBairros repBairros, RepositorioPassageiros repPassageiros,
                              CalculoCustoViagem ccv) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = new CustoViagem(ccv);
    }

    public List<String> getListaBairros() {
        return repBairros.recuperaListaBairros()
                .stream()
                .map(Bairro::getNome)
                .collect(Collectors.toList());
    }

    public List<String> getPassageirosCadastrados() {
        return repPassageiros.listaPassageiros()
                .stream()
                .map(Passageiro::getNome)
                .collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem, String bairroDestino) {
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        Bairro bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        Bairro bDestino = repBairros.recuperaPorNome(bairroDestino);
        return new Roteiro(bOrigem, bDestino, todosBairros);
    }

    public Viagem criaViagem(int id, Roteiro roteiro, String cpfPassageiro) {
        LocalDateTime data = LocalDateTime.now();
        Passageiro passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id, data, roteiro, passageiro, valorCobrado);
    }
}