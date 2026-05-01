package PL2a;

public class Nodo<T> {
    private T dato;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(T dato) {
        this.dato = dato;
        izquierda = null;
        derecha = null;
    }

    public Nodo getDerecha() {
        return derecha;
    }
    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }
    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public T getDato() {
        return dato;
    }
    public void setDato(T dato) {
        this.dato = dato;
    }
}
