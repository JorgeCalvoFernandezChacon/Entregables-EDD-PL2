package org.example;

public class Nodo <T extends Comparable<T>> {
    private T dato;
    private Nodo<T> izquierda;
    private Nodo<T> derecha;
    public Nodo(T dato) {
        this.dato = dato;
        izquierda = null;
        derecha = null;
    }
    public Nodo<T> getDerecha() {
        return derecha;
    }
    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }
    public Nodo<T> getIzquierda() {
        return izquierda;
    }
    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }
    public T getDato() {
        return dato;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }
}
