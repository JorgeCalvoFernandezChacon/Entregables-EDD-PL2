package EEDD_P1.lista_doblemente_enlazada;

public class Elemento<T> {
    // Creamos la variable dato que será lo que contenga el elemento.
    private T dato;

    // Creamos también el siguiente elemento de la lista doblemente enlazada.
    private Elemento<T> siguiente;
    // y el anterior elemento
    private Elemento<T> anterior;

    // Creamos el constructor:
    public Elemento(T dato) {
        this.dato = dato;
        // Ponemos siguiente, anterior = null por defecto
        this.siguiente = null;
        this.anterior = null;
    }

    public T getDato() {
        // Devuelve el dato que contiene el elemento.
        return dato;
    }

    public Elemento<T> getSiguiente() {
        return siguiente;
    }

    public Elemento<T> getAnterior() {
        return anterior;
    }

    public void setDato(T dato) {
        // Define el dato del elemento
        this.dato = dato;
    }

    public void setSiguiente(Elemento<T> siguiente) {
        // Define el siguiente elemento
        this.siguiente = siguiente;
    }

    public void setAnterior(Elemento<T> anterior) {
        // Define el elemento anterior
        this.anterior = anterior;
    }
}
