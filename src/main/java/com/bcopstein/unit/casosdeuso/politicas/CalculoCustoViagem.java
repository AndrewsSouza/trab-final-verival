package com.bcopstein.unit.casosdeuso.politicas;

import com.bcopstein.unit.entidades.Passageiro;
import com.bcopstein.unit.entidades.Roteiro;

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