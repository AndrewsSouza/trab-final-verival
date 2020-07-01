package com.bcopstein.casosDeUso.Repositorios;

import com.bcopstein.entidades.Passageiro;

import java.util.List;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();

    Passageiro recuperaPorCPF(String cpf);

    void atualizaPassageiro(Passageiro passageiro);
}