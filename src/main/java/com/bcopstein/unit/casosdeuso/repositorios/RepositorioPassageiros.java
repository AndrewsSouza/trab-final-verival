package com.bcopstein.unit.casosdeuso.repositorios;

import com.bcopstein.unit.entidades.Passageiro;

import java.util.List;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();

    Passageiro recuperaPorCPF(String cpf);

    void atualizaPassageiro(Passageiro passageiro);
}