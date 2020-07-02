package com.bcopstein.casosdeuso.politicas;

import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;

public interface CalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);

    void definePassageiro(Passageiro passageiro);

    public Roteiro getRoteiro();

    public Passageiro getPassageiro();

    double calculoCustoBasico();

    double descontoPontuacao();

    double descontoPromocaoSazonal();

    double custoViagem();
}