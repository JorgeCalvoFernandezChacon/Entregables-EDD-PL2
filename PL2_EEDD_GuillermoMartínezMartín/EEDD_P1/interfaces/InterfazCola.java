package EEDD_P1.interfaces;

public interface InterfazCola<T> {
    void encolar(T dato);
    T desencolar();
    T primero();
    int pos(T dato);
    T get(int posicion);
    void clear();
    String toString();
    int size();
}
