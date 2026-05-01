package org.example;

public class Elemento<T> {
    protected T valor;
    protected Elemento<T> siguiente;
    // Iniciamos un nuevo elemeno
    public Elemento(T valor) {
        this.valor = valor;
        this.siguiente = null; // Establecemos al siguiente elemento como vacío por defecto
    }
    public T getValor() {
        return valor;
    }
    public Elemento<T> getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Elemento<T> siguiente) {
        this.siguiente = siguiente;
    }
}