package com.bcopstein.casosdeuso.repositorios;

import com.bcopstein.entidades.Bairro;

import java.util.List;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);

    List<Bairro> recuperaListaBairros();
}