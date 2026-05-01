package org.example;

public interface Interfaz_cola<T> {
    void add(T valor);
    T get(int index);
    void del(int index);
    void mostrar();
}
