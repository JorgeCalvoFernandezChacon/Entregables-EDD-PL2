package org.example;

public class Lista_simple_enla<T extends Comparable<T>> implements Interfaz_lista<T> {
    protected Elemento<T> primero;
    //Inicializa la lista vacía
    public Lista_simple_enla() {
        primero = null;
    }
    @Override
    public void add(T valor){
        Elemento<T> nuevo = new Elemento<>(valor); // Creamos un nuevo elemento con el valor dado
        if (primero == null) {
            primero = nuevo;
            return;
        }
        Elemento<T> actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);  // Recorremos la lista hasta que lleguemos al ultimo elemento y lo colocamos despues
    }
    @Override
    public T get(int index) {
        Elemento<T> actual = primero;
        int i = 0;

        while (actual != null) {
            if (i == index) {
                return actual.getValor();
            }   // Recorremos la lista, si llegamos al índice que buscamos devolvemos el valor y sino aumentamos el contador
            actual = actual.getSiguiente();
            i++;
        }

        return null;  // Por si no existe el indice
    }
    @Override
    public void del(int index) {
        if (primero == null) return;
        if (index == 0) {
            primero = primero.getSiguiente();
            return;
        }
        Elemento<T> actual = primero;
        int i = 0;
        while (actual.getSiguiente() != null) {  // Recorremos la lista buscando el elemento anterior al que queremos borrar
            if (i == index - 1) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;    // Saltamos el elemento que queremos borrar, enganchando el actual con el siguiente del siguiente
            }
            actual = actual.getSiguiente();
            i++;
        }
    }
    @Override
    public void mostrar() {
        Elemento<T> actual = primero;
        while (actual != null) {
            System.out.print(actual.getValor() + " -> ");
            actual = actual.getSiguiente();  // Recorrmos e imprimos los elementos hasta llegar al final
        }
        System.out.println("Final de la lista");
    }
}