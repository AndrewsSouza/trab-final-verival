package com.bcopstein.unit.casosdeuso.repositorios;

import com.bcopstein.unit.entidades.Bairro;

import java.util.List;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);

    List<Bairro> recuperaListaBairros();
}