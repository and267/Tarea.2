package org.example;

abstract class Moneda implements Comparable<Moneda>{

    protected int valor;
    protected int serie;

    public Moneda(){
    }
    public Moneda getSerie(){
        return this;
    }

    public abstract int getValor();

    public int compareTo(Moneda m) {
        return (this.getValor() - m.getValor());
    }
}
