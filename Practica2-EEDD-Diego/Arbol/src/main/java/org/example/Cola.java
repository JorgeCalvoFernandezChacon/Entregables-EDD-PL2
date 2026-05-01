package org.example;

public class Cola<T> implements Interfaz_cola<T> {
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
        }
        else {
            ultimo.siguiente = nuevo; // Si no esta vacía metemos al final el nuevo elemento
            ultimo = nuevo;
        }
    }
    public T get(int index) {
        Elemento<T> actual = primero;
        int i = 0;
        // Recorremos la cola hasta encontrar el valor y lo devolvemos
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
        // Caso 1: Borrar el primer elemento
        if (index == 0) {
            primero = primero.siguiente; // El siguiente pasa a ser el primero
            if (primero == null) {
                ultimo = null;
            }
            return;
        }
        // Caso 2: Borrar en medio o al final
        Elemento<T> actual = primero;
        int i = 0;
        while (actual.siguiente != null) {
            if (i + 1 == index) {
                actual.siguiente = actual.siguiente.siguiente; // Saltamos el nodo a borrar
                if (actual.siguiente == null) {
                    ultimo = actual;
                }
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