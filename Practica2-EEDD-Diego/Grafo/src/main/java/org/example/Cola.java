package org.example;

public class Cola<T> implements Interfaz_lista<T> {
    private Elemento<T> primero;
    private Elemento<T> ultimo;
    public Cola() { // Inicializamos la cola vacía
        primero = null;
        ultimo = null;
    }
    public void add(T valor) {
        Elemento<T> nuevo = new Elemento<>(valor);
        if (primero == null) { // La cola esta vacía luego el elemento es el primero y el último
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo; // Si no esta vacía metemos al final el nuevo elemento
            ultimo = nuevo;
        }
    }
    public T get(int index) {
        Elemento<T> actual = primero;
        int i = 0;
        // Recorremos la pila hasta encontrar el valor y lo devolvemos
        while (actual != null) {
            if (i == index) {
                return actual.valor;
            }
            actual = actual.siguiente;
            i++;
        }
        return null;
    }
    public void del(int index) {
        if (primero == null)
            return; // Si esta vacía no hacemos nada
        if (index == 0) {
            primero = primero.siguiente; // Borramos el primero y hacemos que el siguiente pase a ser el primero
            return;
        }
        Elemento<T> actual = primero;
        int i = 0;
        while (actual.siguiente != null) {
            if (i + 1 == index) {
                actual.siguiente = actual.siguiente.siguiente;
                // Hacemos que el siguiente del elemento anterior pase a ser el siguiente del borrado
                return;
            }
            actual = actual.siguiente;
            i++;
        }
    }
    public void mostrar() {  // Muestra la cola por pantalla
        Elemento<T> actual = primero;
        while (actual != null) {
            System.out.println(actual.valor);
            actual = actual.siguiente;
        }
    }
}