package org.example;

public class Elemento<T> {
    public T valor;
    public Elemento<T> siguiente;
    public Elemento(T valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}