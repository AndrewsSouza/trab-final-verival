package com.bcopstein.unit.entidades.geometria;

import java.util.Objects;

public class Reta {
    private Ponto p1;
    private Ponto p2;

    public Reta(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }

    public double tamanho() {
        return Math.sqrt(
                Math.pow( (double) p2.getX() - p1.getX(), 2) +
                        Math.pow( (double) p2.getY() - p1.getY(), 2));
    }

    @Override
    public String toString() {
        return "Reta [p1=" + p1 + ", p2=" + p2 + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reta reta = (Reta) o;
        return p1.equals(reta.p1) &&
                p2.equals(reta.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}