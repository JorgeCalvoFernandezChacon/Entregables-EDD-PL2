package EEDD_P1.cola;

/// Es igual que el de la lista simplemente enlazada
public class Elemento<T> {
    // Creamos la variable dato que será lo que contenga el elemento.
    private T dato;

    // Creamos tambien el siguiente elemento de la lista simplemente enlazada.
    private Elemento<T> siguiente;

    // Creamos el constructor:

    public Elemento(T dato) {
        this.dato = dato;
        // Ponemos siguiente = null por defecto
        this.siguiente = null;
    }

    public T getDato() {
        // Devuelve el dato que contiene el elemento.
        return dato;
    }

    public Elemento<T> getSiguiente() {
        return siguiente;
    }

    public void setDato(T dato) {
        // Define el dato del elemento
        this.dato = dato;
    }

    public void setSiguiente(Elemento<T> siguiente) {
        // Define el siguiente elemento
        this.siguiente = siguiente;
    }
}
